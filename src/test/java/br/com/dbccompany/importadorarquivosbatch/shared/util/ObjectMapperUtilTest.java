package br.com.dbccompany.importadorarquivosbatch.shared.util;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Vendedor;
import br.com.dbccompany.importadorarquivosbatch.helper.fixture.VendedorFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("java:S5786") // Public required for JUnit test suite
@ExtendWith(SpringExtension.class)
public class ObjectMapperUtilTest {

    @Test
    void givenAnSerializableObjectWhenExecutingTheGenerateJsonMethodThenShouldReturnAnSerializedObject() {
        Vendedor vendedor = VendedorFixture.umVendedorMortenHarket();
        String jsonReturned = ObjectMapperUtil.generateJson(vendedor);
        assertEquals("{\"idLayout\":\"001\",\"cpf\":\"44934360000\",\"nome\":\"Morten Harket\",\"salario\":10000.0}", jsonReturned);
    }

    @Test
    void givenAnNoSerializableObjectWhenExecutingTheGenerateJsonMethodThenShouldReturnAnNonSerializedObject() {
        Object nonSerializableObject = new Object();
        String jsonReturned = ObjectMapperUtil.generateJson(nonSerializableObject);
        assertEquals(nonSerializableObject.toString(), jsonReturned);
    }
}