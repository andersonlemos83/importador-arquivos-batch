package br.com.dbccompany.importadorarquivosbatch.cucumber.stepdefs;

import br.com.dbccompany.importadorarquivosbatch.ImportadorArquivosBatchApplication;
import br.com.dbccompany.importadorarquivosbatch.config.ImportadorArquivosConfigTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.nio.file.Path;

@WebAppConfiguration
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {ImportadorArquivosBatchApplication.class, ImportadorArquivosConfigTest.class})
public class StepDefs {

    protected static String nomeArquivoSaida;

}