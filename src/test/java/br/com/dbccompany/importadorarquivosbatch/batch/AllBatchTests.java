package br.com.dbccompany.importadorarquivosbatch.batch;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SelectClasses({
        ImportadorArquivosItemReaderTest.class,
        ImportadorArquivosItemProcessorTest.class,
        ImportadorArquivosItemWriterTest.class
})
@SuppressWarnings("squid:S2187")
@SuiteDisplayName("Suite that gathers all unit tests of the Batch package")
public class AllBatchTests {

}