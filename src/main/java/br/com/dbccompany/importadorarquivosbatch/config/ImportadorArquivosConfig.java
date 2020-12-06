package br.com.dbccompany.importadorarquivosbatch.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Properties;

import static br.com.dbccompany.importadorarquivosbatch.shared.util.ValidadorUtil.verificarSeArquivoOuDiretorioExiste;

@Configuration
@Component
public class ImportadorArquivosConfig {

    @Value("${importador-arquivos.data.in}")
    private String diretorioEntrada;

    @Value("${importador-arquivos.data.out}")
    private String diretorioSaida;

    @PostConstruct
    public void init() {
        verificarSeArquivoOuDiretorioExiste(diretorioEntrada);
        verificarSeArquivoOuDiretorioExiste(diretorioSaida);
    }

    @Bean
    public Properties importacaoArquivosProperties() {
        Properties properties = new Properties();
        properties.setProperty("diretorioEntrada", diretorioEntrada);
        properties.setProperty("diretorioSaida", diretorioSaida);
        return properties;
    }
}