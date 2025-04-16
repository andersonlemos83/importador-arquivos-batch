package br.com.dbccompany.importadorarquivosbatch.service.parse.registro.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Vendedor;
import br.com.dbccompany.importadorarquivosbatch.service.parse.registro.RegistroParse;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.RegistroComLayoutInvalidoException;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.RegistroComTipoDadoInvalidoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static br.com.dbccompany.importadorarquivosbatch.helper.fixture.RegistroFixture.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SuppressWarnings("java:S5786") // Public required for JUnit test suite
@ExtendWith(SpringExtension.class)
public class VendedorParseTest {

    private RegistroParse registroParse;

    @BeforeEach
    public void inicializarContexto() {
        registroParse = new VendedorParse();
    }

    @Test
    public void aoFazerParseDadoQueRegistroSejaValidoDeveriaRetornarOhVendedorEsperado() {
        final Vendedor vendedor = (Vendedor) registroParse.parse(umaRegistroArrayPedro());
        assertEquals("001", vendedor.getId());
        assertEquals("1234567891234", vendedor.getCpf());
        assertEquals("Pedro", vendedor.getNome());
        assertEquals(Double.valueOf("50000"), vendedor.getSalario());
    }

    @Test
    public void aoFazerParseDadoQueRegistroTenhaQuantidadeAtributosInvalidoDeveriaRetornarLancarUmaRegistroComLayoutInvalidoException() {
        String[] registro = umaRegistroArrayPedroComQuantidadeInvalida();
        RegistroComLayoutInvalidoException thrown = assertThrows(RegistroComLayoutInvalidoException.class, () -> registroParse.parse(registro));
        assertEquals("O arquivo possui um registro, [001, 1234567891234, Pedro], incompatível com o layout Vendedor.", thrown.getMessage());
    }

    @Test
    public void aoFazerParseDadoQueRegistroTenhaDadosInvalidosDeveriaRetornarLancarUmaRegistroComTipoDadoInvalidoException() {
        String[] registro = umaRegistroArrayPedroComDadosInvalidos();
        RegistroComTipoDadoInvalidoException thrown = assertThrows(RegistroComTipoDadoInvalidoException.class, () -> registroParse.parse(registro));
        assertEquals("O arquivo possui um registro, [001, 1234567891234, Pedro, Inválido], com dados incompatíveis com o layout Vendedor.", thrown.getMessage());
    }
}