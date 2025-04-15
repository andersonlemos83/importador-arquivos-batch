package br.com.dbccompany.importadorarquivosbatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ImportadorArquivosBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImportadorArquivosBatchApplication.class, args);
    }

}