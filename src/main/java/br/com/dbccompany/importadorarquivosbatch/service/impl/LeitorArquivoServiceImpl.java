package br.com.dbccompany.importadorarquivosbatch.service.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.Arquivo;
import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosLeitura;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;
import br.com.dbccompany.importadorarquivosbatch.repository.LeitorArquivoRepository;
import br.com.dbccompany.importadorarquivosbatch.service.LeitorArquivoService;
import br.com.dbccompany.importadorarquivosbatch.service.parse.ArquivoParse;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.dbccompany.importadorarquivosbatch.shared.util.ObjectMapperUtil.generateJson;

@Log4j2
@Service
@AllArgsConstructor
public class LeitorArquivoServiceImpl implements LeitorArquivoService {

    private final LeitorArquivoRepository leitorArquivoRepository;
    private final ArquivoParse arquivoParse;

    @Override
    public DadosLeitura lerArquivoNaoImportado() {
        log.debug("Entrando em LeitorArquivoServiceImpl");
        final Arquivo arquivo = leitorArquivoRepository.lerArquivoNaoImportado();
        final List<Registro> registros = arquivoParse.parse(arquivo);
        final DadosLeitura dadosLeitura = gerarDadosLeitura(arquivo, registros);
        log.debug("Saindo de LeitorArquivoServiceImpl: {}", generateJson(dadosLeitura));
        return dadosLeitura;
    }

    private DadosLeitura gerarDadosLeitura(Arquivo arquivo, List<Registro> registros) {
        return DadosLeitura.builder()
                .arquivoPath(arquivo.getArquivoPath())
                .registros(registros)
                .build();
    }
}