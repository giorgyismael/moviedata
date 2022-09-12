# Movie Data
## Desafio | Desenvolver APIRest
Projeto consiste em desenvolver uma APIrest simples para ser capcaz de realizar a ingest�o de arquivo CSV com cabe�alho pr�-definido e fornecer o resultado de racking atrav�s de uma rota para consulta.

## Caracteristicas
- Importa arquivo CSV, automaticamente, de pasta local do aplicativo.
- Fornece uma rota, via verbo GET, de modo a obter racking contendo produtor com maior intervalo entre dois pr�mios,al�m do que obtive o mais r�pido.
- Possui teste de integra��o que garanta que o ranking fornecido est� de acordo com os dados inicialmente fornecidos.
- Possui teste unit�rio que garante a gera��o adequada do racking.
- Utiliza o banco H2 embedded em mem�ria.
- Possui controle de migrations com flyway
- Utiliza Swagger para documenta�� e acesso aos endpoints.
- Utiliza cache nas consultas para otimiza��o da importa��o de dados.
- Tem caracteristivas de uma APIRest com n�vel 2 de maturidade de Richardson
- Utiliza assembly para montar o pacote para uso final, n�o sendo necess�rio rodar via IDE de desenvolvimento.
- Empacota a aplica��o para ser utilizada em qualquer ambiente windowns, fornecendo a possibilidade de manupular de forma externa os arquivos de configura��o.

## Caracteristicas n�o Implementadas
- Nenhuma autentica��o est� ativa.
- N�o foi ativado CORS para seguran�a da aplica��o.
- N�o foi implementado mecanismos de sustemtabilidade da APIRest, com isso n�o possui limitadores de acesso e requisi��o.
- N�o se trata de uma APIRestFull, pois n�o foi implementado os 4 niveis de maturidade de Richardson. 
- N�o foi implementado HATEOAS.
- N�o foi implementado a dokeriza�o da aplica��o.
- N�o foi utilizado o gitFlow para implementar a CI e CD da aplica��o.

## Pr�-Requisitos
- [Java] - vers�o JDK -> 1.8.x
- [Maven] - vers�o 3.3.9

## Plugins
Abaixo a rela��o de plugins utilizados para executar empacotamento do projeto.

| Plugin | README |
| ------ | ------ |
| SpringBootMaven | https://docs.spring.io/spring-boot/docs/current/maven-plugin/reference/htmlsingle/ |
| MavenAssembly | https://github.com/apache/maven-assembly-plugin#readme |
| MavenCompile | https://github.com/apache/maven-compiler-plugin/blob/master/README.md |

## Build
Projeto � composto por testes de integra��o e teste de unidade, al�m de contar com empacotamento, onde o artefato final pode ser executado em qualquer ambiente windowns, sem a necessidade de uma IDE de desenvolvimento.

### Build Completo
```sh
mvn clean install
```

### Build somente com Teste de Integra��o
```sh
mvn clean install -DskipUnitTests
```

## Instala��o
Est� aplica��o pode tanto ser executada via IDE de desenvolvimento (IntelliJ, Spring Tool Suite, VSCode e, outros...), como  ser instalada em ambiente windowns.

####  Intala��o IDE
- Baixe pacote do repositorio  em ambiente local.
- Importe o projeto para sua IDE de desenvolvimento
- Altere a configura��o do atributo `environment-type: DEV`, no arquivo de configura��es `application.yml`
- Verifique se o arquivo para importa��o CSV est� no local correto `pathProject::resources/csv`, cabe salientar que � poss�vel alterar este arqiuvo e seus dados, entretanto, seu cabe�alho de parmanecer inalterado.
- Rode o projeto atrav�s da classe main `com.movies.data.moviesdata.MoviesdataApplication`

####  Intala��o Local
- Baixe pacote do repositorio  em ambiente local.
- Execute o comando da se��o `BUILD -> Build Completo`
- Copie o artefado gerado na pasta �pathProject::moviesdata\target\moviesdata-0.0.1-SNAPSHOT.zip� e cole em local de seu desejo
- Descompacte o artefato
- - Verifique se o arquivo para importa��o CSV est� no local correto `pathProject::MovieData/csv`, cabe salientar que � poss�vel alterar este arqiuvo e seus dados, entretanto, seu cabe�alho de parmanecer inalterado.
- Retire o su-fixo `.template` dos arquivos `application.yml` e `application.yml` no caminho `pathProject::MovieData`
- Altere a configura��o do atributo `environment-type: PROD`, no arquivo de configura��es `application.yml`
- Retire o su-fixo `.template` dos arquivos `startup_client.bat` no caminho `pathProject::MovieData`
- Execute o arquivo `startup_client.bat`

####  Documenta��o
- Acesse `http://localhost:8080/v3/api-docs/` para ter acesso � documenta��o.
- Acesso `http://localhost:8080/swagger-ui/index.html` para ser poss�vel importar os endPoints em alguma aplica��o de intera��o com APIRest. 
