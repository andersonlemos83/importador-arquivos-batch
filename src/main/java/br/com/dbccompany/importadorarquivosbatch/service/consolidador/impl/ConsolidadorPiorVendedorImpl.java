package br.com.dbccompany.importadorarquivosbatch.service.consolidador.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.Venda;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.Vendedor;
import br.com.dbccompany.importadorarquivosbatch.service.consolidador.ConsolidadorPiorVendedor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;

import static java.util.Comparator.comparingDouble;
import static java.util.Comparator.naturalOrder;
import static java.util.stream.Collectors.*;

@Component
public class ConsolidadorPiorVendedorImpl implements ConsolidadorPiorVendedor {

    @Override
    public String consolidar(List<Registro> registros) {
        final List<Vendedor> vendedores = filtrarVendedores(registros);
        final List<Venda> vendas = filtrarVendas(registros);
        final List<Vendedor> vendedoresQueNaoVenderam = obterVendedoresQueNaoVenderam(vendedores, vendas);
        final List<String> nomesVendedoresOrdenados = obterNomesVendedoresOrdenadosPeloPiorDesempenho(vendedoresQueNaoVenderam, vendas);
        return obterPiorVendedor(nomesVendedoresOrdenados);
    }

    private List<Vendedor> filtrarVendedores(List<Registro> registros) {
        return registros.stream()
                .filter(Registro::ehVendedor)
                .map(registro -> (Vendedor) registro)
                .collect(toList());
    }

    private List<Venda> filtrarVendas(List<Registro> registros) {
        return registros.stream()
                .filter(Registro::ehVenda)
                .map(registro -> (Venda) registro)
                .collect(toList());
    }

    private List<String> obterNomesVendedoresOrdenadosPeloPiorDesempenho(List<Vendedor> vendedoresQueNaoVenderam, List<Venda> vendas) {
        List<String> nomesVendedoresOrdenados = new ArrayList<>();
        nomesVendedoresOrdenados.addAll(obterNomesVendedoresQueNaoVenderamOrdenadosPeloPiorDesempenho(vendedoresQueNaoVenderam));
        nomesVendedoresOrdenados.addAll(obterNomesVendedoresQueVenderamOrdenadosPeloPiorDesempenho(vendas));
        return nomesVendedoresOrdenados;
    }

    private List<Vendedor> obterVendedoresQueNaoVenderam(List<Vendedor> vendedores, List<Venda> vendas) {
        return vendedores.stream()
                .filter(vendedor -> this.verificarQuemNaoVendeu(vendas, vendedor))
                .collect(toList());
    }

    private boolean verificarQuemNaoVendeu(List<Venda> vendas, Vendedor vendedor) {
        return vendas.stream()
                .filter(venda -> venda.getNomeVendedor().equalsIgnoreCase(vendedor.getNome()))
                .collect(toList())
                .isEmpty();
    }

    private List<String> obterNomesVendedoresQueNaoVenderamOrdenadosPeloPiorDesempenho(List<Vendedor> vendedoresQueNaoVenderam) {
        return vendedoresQueNaoVenderam.stream()
                .sorted(comparingDouble(Vendedor::getSalario).reversed())
                .map(Vendedor::getNome)
                .collect(toList());
    }

    private List<String> obterNomesVendedoresQueVenderamOrdenadosPeloPiorDesempenho(List<Venda> vendas) {
        return vendas.stream()
                .collect(gerarMapVendedoresIhTotalEmVendas())
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(naturalOrder()))
                .map(Map.Entry::getKey)
                .collect(toList());
    }

    private Collector<Venda, ?, Map<String, Double>> gerarMapVendedoresIhTotalEmVendas() {
        return groupingBy(Venda::getNomeVendedor, summingDouble(Venda::obterTotal));
    }

    private String obterPiorVendedor(List<String> nomesVendedoresOrdenados) {
        return nomesVendedoresOrdenados.stream().findFirst().orElse("");
    }
}