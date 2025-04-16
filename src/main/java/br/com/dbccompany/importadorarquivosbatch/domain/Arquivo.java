package br.com.dbccompany.importadorarquivosbatch.domain;

import lombok.*;

import java.nio.file.Path;
import java.util.List;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Arquivo {

    private Path arquivoPath;
    private List<String[]> registrosArray;

}