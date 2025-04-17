[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=andersonlemos83_importador-arquivos-batch&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=andersonlemos83_importador-arquivos-batch)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=andersonlemos83_importador-arquivos-batch&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=andersonlemos83_importador-arquivos-batch)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=andersonlemos83_importador-arquivos-batch&metric=coverage)](https://sonarcloud.io/summary/new_code?id=andersonlemos83_importador-arquivos-batch)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=andersonlemos83_importador-arquivos-batch&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=andersonlemos83_importador-arquivos-batch)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=andersonlemos83_importador-arquivos-batch&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=andersonlemos83_importador-arquivos-batch)

![GitHub Created At](https://img.shields.io/github/created-at/andersonlemos83/importador-arquivos-batch)
![Build Status](https://github.com/andersonlemos83/importador-arquivos-batch/actions/workflows/github-ci.yaml/badge.svg)
![Docker Image Version](https://img.shields.io/docker/v/andersonlemos83/api-importador-arquivos-batch?sort=semver&label=api-importador-arquivos-batch)

# Sobre o projeto Importação Arquivo Batch

Este projeto foi concebido no final do ano de 2020 como parte de uma avaliação técnica de backend para tentativa de 
ingresso na empresa DBC Company.

OBS: Atualizado em 2025

**1. Domínio**

Consiste em um sistema de análise de dados que realiza leitura de arquivos ".dat" contidos no diretório de entrada, 
realiza o processamento dos dados de acordo com o layout de cada registro e, 
por fim, realiza a gravação de um relatório consolidado no diretório de saída.

Exemplo:

- IN: arquivo-exemplo.dat:
  ```
   001ç1234567891234çPedroç50000
   001ç3245678865434çPauloç40000.99
   002ç2345675434544345çJose da SilvaçRural
   002ç2345675433444345çEduardo PereiraçRural
   003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro
   003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo
  ```

Onde:  
001-Vendedor: idLayout **ç** cpf **ç** nome **ç** salario  
002-Cliente: idLayout **ç** cnpj **ç** nome **ç** areaNegocio  
003-Venda: idLayout **ç** idVenda **ç** \[id **-** quantidade **-** preco, id **-** quantidade **-** preco\] **ç** nomeVendedor  

- OUT: arquivo-exemplo.done.dat
  ```
   2ç2ç10çPaulo
  ```

Onde: quantidadeClientes **ç** quantidadeVendedores **ç** idVendaMaisCara **ç** nomePiorVendedor

**2. Features implementadas**
- Leitura de arquivos ".dat";
- Processamento dos dados de acordo com o layout de cada registro;
- Gravação de relatório consolidado. 

**3. Features bônus implementadas**
- Movimentação de arquivos de entrada inválidos para um diretório específico.  

**4. Informações técnicas**

- Linguagem Java 21 LTS
- Ecossistema do Spring Boot 3.4.3 (Batch, Log4j2, Actuator, Test, entre outros)
- Testes com JUnit 5, Mockito e Cucumber
- Banco de dados com H2
- Containerização com Docker
- Logging e Monitoramento com Log4j2 e Spring Boot Actuator
- Controle de código Boilerplate com Lombok
- Gerenciamento de dependências com o Maven
- Controle de Versão com Git
- Integração Contínua com GitHub Actions
- Análise de cobertura de testes com SonarQube

**5. Sobre os testes**

Com o intuito de organizar melhor os testes do projeto, foram agrupados os testes em três grandes suites:

- CucumberTest: Esta suite agrupa todos os testes aceitação e integração baseados em features BDD.
- UnitTests: Esta suite agrupa todos os testes de unidade do projeto.
- AllTests: Esta suite agrupa todos os testes implementados. Consiste na união do CucumberTest com UnitTests.

**6. Primeiros Passos**
- Baixar todas as dependências do projeto:
  ```
    mvn dependency:resolve -U
  ```
- Executar o build do projeto:
  ```
    mvn -U -B clean install -Dmaven.test.skip=true
  ```
- Executar o build do projeto executando todos os testes:
  ```
    mvn -U -B clean install
  ```
- Executar construção da imagem docker:
  ```
    mvn -U -B package -Pdocker-build -Dmaven.test.skip=true
  ```

**7. Começando com a Aplicação**
1. Configurar o compose [api-importador-arquivos-batch.yml](./script/docker/api-importador-arquivos-batch.yml):
  ```
    Altere "/c/Users/DEV" por um caminho válido (HOME_PATH)
  ```

2. Criar os diretórios DATA:
  ```
   - %HOME_PATH%/data/in
   - %HOME_PATH%/data/out
   - %HOME_PATH%/data/invalid
  ```

3. Configurar os arquivos no diretório de entrada:
  ```
   Copiar os arquivos de testes localizados em /src/test/resources/data para %HOME_PATH%/data/in
  ```

4. Subir uma instância da api-importador-arquivos-batch:
  ```
    docker-compose -f .\script\docker\api-importador-arquivos-batch.yml up -d
  ```

5. Analisar resultados do processamento da api-importador-arquivos-batch:
  ```
    - %HOME_PATH%/data/out
    - %HOME_PATH%/data/invalid
  ```
        
**8. That's all folks!**

Caro avaliador, obrigado pela oportunidade.