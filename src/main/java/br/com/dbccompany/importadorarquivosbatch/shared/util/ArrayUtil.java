package br.com.dbccompany.importadorarquivosbatch.shared.util;

public final class ArrayUtil {

    private ArrayUtil() {
    }

    public static String obterString(String[] array, int indice) {
        try {
            return array[indice];
        } catch (ArrayIndexOutOfBoundsException excecao) {
            return null;
        }
    }
}