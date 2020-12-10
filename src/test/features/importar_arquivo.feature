# language: pt

Funcionalidade: Importar Arquivo

  Cenario: 01 - Importar Arquivo Com Sucesso
    Dado que exista o arquivo "sucesso-dbc.dat" no diretorio de entrada
    Quando importar o arquivo
    Entao deveria criar o arquivo "sucesso-dbc.done.dat" no diretorio de saida
    E deveria gravar dentro do arquivo de saida o conteudo consolidado "2ç2ç10çPaulo"
    E deveria excluir o arquivo "sucesso-dbc.dat" do diretorio de entrada