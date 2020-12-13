[![Build Status](https://travis-ci.org/andersonlemos83/importador-arquivos-batch.svg?branch=main)](https://travis-ci.org/andersonlemos83/importador-arquivos-batch) [![codecov](https://codecov.io/gh/andersonlemos83/importador-arquivos-batch/branch/main/graph/badge.svg?token=0X9WNC9TOY)](https://codecov.io/gh/andersonlemos83/importador-arquivos-batch)

# Sobre o projeto Importação Arquivo Batch

Este projeto foi concebido como parte de uma avaliação técnica para tentativa de ingresso na empresa DBC Company.

**1. Domínio**

Consiste em um sistema de análise de dados que realiza leitura de arquivos ".dat" contidos no diretório de entrada, realiza o processamento dos dados de acordo com o layout de cada registro e, por fim, realiza a gravação de um relatório consolidado no diretório de saída.

**2. Features implementadas**
- Leitura de arquivos ".dat";
- Processamento dos dados de acordo com o layout de cada registro;
- Gravação de relatório consolidado. 

**3. Features bônus implementadas**
- Movimentação de arquivos de entrada inválidos para um diretório específico.  

**4. Informações técnicas**
- Linguagem Java 8
- Ecossistema do Spring Boot 2.3.6
- Testes com JUnit, Mockto e Cucumber
- Conteinerização da aplicação com Docker
- Integração Contínua com Travis CI
- Análise de cobertura de testes com Codecov

**5. Sobre os testes**

Com o intuito de organizar melhor os testes do projeto, foram agrupados os testes em três grandes suites:
- CucumberTest: Esta suite agrupa todos os testes aceitação e integração baseados em features BDD. Ela atingiu **90%** de linha cobertas segundo coverage. 
- UnitTests: Esta suite agrupa todos os testes de unidade do projeto. Ela atingiu **93%** de linha cobertas segundo coverage.
- AllTests: Esta suite agrupa todos os testes implementados. Consiste na união do CucumberTest com UnitTests. Ela atingiu **97%** de linha cobertas segundo coverage.

**6. Pré-requisitos**


**10. That's all folks!**

Caro avaliador, obrigado pela oportunidade.