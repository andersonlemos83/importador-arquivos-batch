package br.com.dbccompany.importadorarquivosbatch.domain.registro;

import lombok.*;

import java.io.Serializable;
import java.util.List;

import static java.lang.Boolean.TRUE;
import static java.math.BigDecimal.ZERO;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Venda implements Registro, Serializable {

    private String idLayout;
    private String idVenda;
    private List<Item> itens;
    private String nomeVendedor;

    @Override
    public Boolean ehVenda() {
        return TRUE;
    }

    public Double obterTotal() {
        return itens.stream().map(Item::obterTotal).reduce(ZERO.doubleValue(), Double::sum);
    }
}