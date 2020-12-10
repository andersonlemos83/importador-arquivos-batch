# language: pt

Funcionalidade: Importar Arquivo

  Esquema do Cenario: 01 - Importar Arquivo Com Sucesso
    Dado que exista o arquivo "<Nome Arquivo Entrada>" no diretorio de entrada
    Quando importar o arquivo
    Entao deveria criar o arquivo "<Nome Arquivo Saida>" no diretorio de saida
    E deveria gravar dentro do arquivo de saida o conteudo consolidado "<Conteudo Arquivo Saida>"
    E deveria excluir o arquivo "<Nome Arquivo Entrada>" do diretorio de entrada
    Exemplos:
      | Cenario                       | Nome Arquivo Entrada          | Nome Arquivo Saida                 | Conteudo Arquivo Saida |
      | Template DBC                  | sucesso-dbc.dat               | sucesso-dbc.done.dat               | 2ç2ç10çPaulo           |
      | Arquivo com apenas 1 vendedor | sucesso-apenas-1-vendedor.dat | sucesso-apenas-1-vendedor.done.dat | 0ç1çç                  |
      | Arquivo com apenas 1 cliente  | sucesso-apenas-1-cliente.dat  | sucesso-apenas-1-cliente.done.dat  | 1ç0çç                  |