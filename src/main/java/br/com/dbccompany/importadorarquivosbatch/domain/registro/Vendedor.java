package br.com.dbccompany.importadorarquivosbatch.domain.registro;

import lombok.*;

import java.io.Serializable;

import static java.lang.Boolean.TRUE;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Vendedor implements Registro, Serializable {

    private String id;
    private String cpf;
    private String nome;
    private Double salario;

    @Override
    public Boolean ehVendedor() {
        return TRUE;
    }
}