package br.com.dbccompany.importadorarquivosbatch.domain.dados;

import lombok.*;

import java.io.Serializable;
import java.nio.file.Path;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DadosProcessamento implements Serializable {

    private Path arquivoPath;
    private Long quantidadeClientes;
    private Long quantidadeVendedores;
    private String idVendaMaisCara;
    private String nomePiorVendedor;

}