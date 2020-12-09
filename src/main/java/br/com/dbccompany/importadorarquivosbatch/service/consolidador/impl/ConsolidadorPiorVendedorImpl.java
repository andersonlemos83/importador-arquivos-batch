package br.com.dbccompany.importadorarquivosbatch.service.consolidador.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.Venda;
import br.com.dbccompany.importadorarquivosbatch.service.consolidador.ConsolidadorPiorVendedor;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;

import static java.util.Comparator.naturalOrder;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Component
public class ConsolidadorPiorVendedorImpl implements ConsolidadorPiorVendedor {

    @Override
    public String consolidar(List<Registro> registros) {
        final List<Venda> vendas = filtrarVendas(registros);
        final List<Map.Entry<String, Double>> vendedoresOrdenados = gerarListaDeVendedoresOrdenadosPeloPiorDesempenho(vendas);
        return obterNomeDoPiorVendedor(vendedoresOrdenados);
    }

    private List<Venda> filtrarVendas(List<Registro> registros) {
        return registros.stream()
                .filter(Registro::ehVenda)
                .map(registro -> (Venda) registro)
                .collect(toList());
    }

    private List<Map.Entry<String, Double>> gerarListaDeVendedoresOrdenadosPeloPiorDesempenho(List<Venda> vendas) {
        return vendas.stream()
                .collect(gerarMapVendedoresIhTotalEmVendas())
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(naturalOrder()))
                .collect(toList());
    }

    private Collector<Venda, ?, LinkedHashMap<String, Double>> gerarMapVendedoresIhTotalEmVendas() {
        return toMap(Venda::getNomeVendedor, Venda::obterTotal, (totalAtual, totalNovo) -> totalAtual + totalNovo, LinkedHashMap::new);
    }

    private String obterNomeDoPiorVendedor(List<Map.Entry<String, Double>> vendedoresOrdenados) {
        return vendedoresOrdenados.stream()
                .findFirst()
                .map(Map.Entry::getKey)
                .orElse(null);
    }
}