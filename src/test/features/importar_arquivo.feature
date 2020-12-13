# language: pt

Funcionalidade: Importar Arquivo

  Esquema do Cenario: 01 - Importar Arquivo Com Sucesso
    Dado que exista o arquivo "<Nome Arquivo Entrada>" no diretorio de entrada
    Quando importar o arquivo
    Entao deveria criar o arquivo "<Nome Arquivo Saida>" no diretorio de saida
    E deveria gravar dentro do arquivo de saida o conteudo consolidado "<Conteudo Arquivo Saida>"
    E deveria excluir o arquivo "<Nome Arquivo Entrada>" do diretorio de entrada
    Exemplos:
      | Cenario                                           | Nome Arquivo Entrada                           | Nome Arquivo Saida                                  | Conteudo Arquivo Saida |
      | Arquivo template DBC                              | sucesso-dbc.dat                                | sucesso-dbc.done.dat                                | 2ç2ç10çPaulo           |
      | Arquivo com apenas 1 vendedor                     | sucesso-apenas-1-vendedor.dat                  | sucesso-apenas-1-vendedor.done.dat                  | 0ç1ççPedro             |
      | Arquivo com apenas 1 cliente                      | sucesso-apenas-1-cliente.dat                   | sucesso-apenas-1-cliente.done.dat                   | 1ç0çç                  |
      | Arquivo com apenas 1 venda                        | sucesso-apenas-1-venda.dat                     | sucesso-apenas-1-venda.done.dat                     | 0ç0ç10çPedro           |
      | Arquivo com 10 vendedores, 5 clientes e 20 vendas | sucesso-10-vendedores-5-clientes-20-vendas.dat | sucesso-10-vendedores-5-clientes-20-vendas.done.dat | 5ç10ç01çLaura Pausini  |

  Esquema do Cenario: 02 - Importar Arquivo Sem Sucesso
    Dado que exista o arquivo "<Nome Arquivo Entrada>" no diretorio de entrada
    Quando importar o arquivo
    Entao deveria criar o arquivo "<Nome Arquivo Entrada>" no diretorio de invalido
    E deveria excluir o arquivo "<Nome Arquivo Entrada>" do diretorio de entrada
    Exemplos:
      | Cenario                                                   | Nome Arquivo Entrada                    |
      | Arquivo com registro com ID invalido                      | falha-id-invalido.dat                   |
      | Arquivo com registro com quantidade de atributos invalido | falha-quantidade-atributos-invalido.dat |
      | Arquivo com registro com tipo atributo invalido           | falha-tipo-atributo-invalido.dat        |