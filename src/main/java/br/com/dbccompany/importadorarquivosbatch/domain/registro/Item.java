package br.com.dbccompany.importadorarquivosbatch.domain.registro;

import lombok.*;

import java.io.Serializable;

import static java.math.BigDecimal.ZERO;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Item implements Serializable {

    private String id;
    private Integer quantidade;
    private Double preco;

    public Double obterTotal() {
        if (quantidade == null || preco == null) {
            return ZERO.doubleValue();
        }
        return quantidade * preco;
    }
}