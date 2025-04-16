package br.com.dbccompany.importadorarquivosbatch.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
@Configuration
public class ImportadorArquivosConfiguration {

    @Value("${importador-arquivos.data.in}")
    private String diretorioEntrada;

    @Value("${importador-arquivos.data.out}")
    private String diretorioSaida;

    @Value("${importador-arquivos.data.invalid}")
    private String diretorioInvalido;

    @Bean
    public Properties importacaoArquivosProperties() {
        Properties properties = new Properties();
        properties.setProperty("diretorioEntrada", diretorioEntrada);
        properties.setProperty("diretorioSaida", diretorioSaida);
        properties.setProperty("diretorioInvalido", diretorioInvalido);
        return properties;
    }
}