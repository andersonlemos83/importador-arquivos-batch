package br.com.dbccompany.importadorarquivosbatch.cucumber.verificador;

import br.com.dbccompany.importadorarquivosbatch.cucumber.contexto.ImportadorArquivosContexto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Component
@AllArgsConstructor
public class ImportadorArquivosVerificador {

    private final ImportadorArquivosContexto importadorArquivosContexto;

    public void verificarSeExisteArquivoSaida(String nomeArquivoSaida) {
        Boolean existeArquivoSaida = importadorArquivosContexto.existeArquivoSaida(nomeArquivoSaida);
        assertTrue("Não existe arquivo de saída: " + nomeArquivoSaida, existeArquivoSaida);
    }

    public void verificarConteudoArquivoSaida(String nomeArquivoSaida, String conteudoArquivoSaida) {
        String conteudoArquivoSaidaRetornado = importadorArquivosContexto.obterConteudoArquivoSaida(nomeArquivoSaida);
        assertEquals(conteudoArquivoSaidaRetornado, conteudoArquivoSaida);
    }

    public void verificarSeNaoExisteArquivoEntrada(String nomeArquivoEntrada) {
        Boolean existeArquivoEntrada = importadorArquivosContexto.existeArquivoEntrada(nomeArquivoEntrada);
        assertFalse("Existe arquivo de entrada: " + nomeArquivoEntrada, existeArquivoEntrada);
    }

    public void verificarSeExisteArquivoInvalido(String nomeArquivoInvalido) {
        Boolean existeArquivoInvalido = importadorArquivosContexto.existeArquivoInvalido(nomeArquivoInvalido);
        assertTrue("Não existe arquivo inválido: " + nomeArquivoInvalido, existeArquivoInvalido);
    }
}