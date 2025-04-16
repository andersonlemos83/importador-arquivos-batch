package br.com.dbccompany.importadorarquivosbatch.service.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.Arquivo;
import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosLeitura;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;
import br.com.dbccompany.importadorarquivosbatch.repository.LeitorArquivoRepository;
import br.com.dbccompany.importadorarquivosbatch.service.LeitorArquivoService;
import br.com.dbccompany.importadorarquivosbatch.service.parse.ArquivoParse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LeitorArquivoServiceImpl implements LeitorArquivoService {

    private final LeitorArquivoRepository leitorArquivoRepository;
    private final ArquivoParse arquivoParse;

    @Override
    public DadosLeitura lerArquivoNaoImportado() {
        final Arquivo arquivo = leitorArquivoRepository.lerArquivoNaoImportado();
        final List<Registro> registros = arquivoParse.parse(arquivo);
        return DadosLeitura.builder()
                .arquivoPath(arquivo.getArquivoPath())
                .registros(registros)
                .build();
    }
}