package br.com.dbccompany.importadorarquivosbatch.domain.dados;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;
import lombok.*;

import java.nio.file.Path;
import java.util.List;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DadosLeitura {

    private Path arquivoPath;
    private List<Registro> registros;

}