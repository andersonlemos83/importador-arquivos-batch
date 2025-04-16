package br.com.dbccompany.importadorarquivosbatch.cucumber;

import br.com.dbccompany.importadorarquivosbatch.ImportadorArquivosBatchApplication;
import br.com.dbccompany.importadorarquivosbatch.config.ImportadorArquivosConfig;
import br.com.dbccompany.importadorarquivosbatch.cucumber.stepdefs.StepDefs;
import br.com.dbccompany.importadorarquivosbatch.helper.util.ObjectMapperHelper;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.*;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

@ActiveProfiles("test")
@CucumberContextConfiguration
@SpringBootTest(classes = ImportadorArquivosBatchApplication.class)
@ContextConfiguration(classes = {ImportadorArquivosBatchApplication.class, ImportadorArquivosConfig.class})
public class SpringContextStepDefs extends StepDefs {

    @DefaultParameterTransformer
    @DefaultDataTableEntryTransformer
    @DefaultDataTableCellTransformer
    public Object defaultTransformer(Object value, Type type) {
        final ObjectMapper objectMapper = ObjectMapperHelper.getInstance().copy();
        final Object handledValue = handleEmptyValues(value);
        final JavaType javaType = objectMapper.constructType(type);
        return objectMapper.convertValue(handledValue, javaType);
    }

    private Object handleEmptyValues(Object value) {
        if (value instanceof Map) {
            Map handledMap = new LinkedHashMap();
            for (Object o : ((Map) value).entrySet()) {
                Map.Entry entry = (Map.Entry) o;
                Object handledValue = handleEmptyValue(entry.getValue());
                entry.setValue(handledValue);
                handledMap.put(entry.getKey(), entry.getValue());
            }
            return handledMap;
        }

        return handleEmptyValue(value);
    }

    private Object handleEmptyValue(Object o) {
        if (o instanceof String && "<empty>".equalsIgnoreCase((String) o)) {
            return "";
        }
        return o;
    }

    @Before
    @Override
    public void inicializarContexto() {
        super.inicializarContexto();
    }

    @After
    @Override
    public void finalizarContexto() {
        super.finalizarContexto();
    }
}