package br.com.dbccompany.importadorarquivosbatch.domain.registro;

import lombok.*;

import java.io.Serializable;

import static java.lang.Boolean.TRUE;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Cliente implements Registro, Serializable {

    private String id;
    private String cnpj;
    private String nome;
    private String areaNegocio;

    @Override
    public Boolean ehCliente() {
        return TRUE;
    }
}