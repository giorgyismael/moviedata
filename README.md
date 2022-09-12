# Movie Data
## Desafio | Desenvolver APIRest
Projeto consiste em desenvolver uma APIrest simples para ser capcaz de realizar a ingestão de arquivo CSV com cabeçalho pré-definido e fornecer o resultado de racking através de uma rota para consulta.

## Caracteristicas
- Importa arquivo CSV, automaticamente, de pasta local do aplicativo.
- Fornece uma rota, via verbo GET, de modo a obter racking contendo produtor com maior intervalo entre dois prêmios,além do que obtive o mais rápido.
- Possui teste de integração que garanta que o ranking fornecido está de acordo com os dados inicialmente fornecidos.
- Possui teste unitário que garante a geração adequada do racking.
- Utiliza o banco H2 embedded em memória.
- Possui controle de migrations com flyway
- Utiliza Swagger para documentaçã e acesso aos endpoints.
- Utiliza cache nas consultas para otimização da importação de dados.
- Tem caracteristivas de uma APIRest com nível 2 de maturidade de Richardson
- Utiliza assembly para montar o pacote para uso final, não sendo necessário rodar via IDE de desenvolvimento.
- Empacota a aplicação para ser utilizada em qualquer ambiente windowns, fornecendo a possibilidade de manupular de forma externa os arquivos de configuração.

## Caracteristicas não Implementadas
- Nenhuma autenticação está ativa.
- Não foi ativado CORS para segurança da aplicação.
- Não foi implementado mecanismos de sustemtabilidade da APIRest, com isso não possui limitadores de acesso e requisição.
- Não se trata de uma APIRestFull, pois não foi implementado os 4 niveis de maturidade de Richardson. 
- Não foi implementado HATEOAS.
- Não foi implementado a dokerizaão da aplicação.
- Não foi utilizado o gitFlow para implementar a CI e CD da aplicação.

## Pré-Requisitos
- [Java] - versão JDK -> 1.8.x
- [Maven] - versão 3.3.9

## Plugins
Abaixo a relação de plugins utilizados para executar empacotamento do projeto.

| Plugin | README |
| ------ | ------ |
| SpringBootMaven | https://docs.spring.io/spring-boot/docs/current/maven-plugin/reference/htmlsingle/ |
| MavenAssembly | https://github.com/apache/maven-assembly-plugin#readme |
| MavenCompile | https://github.com/apache/maven-compiler-plugin/blob/master/README.md |

## Build
Projeto é composto por testes de integração e teste de unidade, além de contar com empacotamento, onde o artefato final pode ser executado em qualquer ambiente windowns, sem a necessidade de uma IDE de desenvolvimento.

### Build Completo
```sh
mvn clean install
```

### Build somente com Teste de Integração
```sh
mvn clean install -DskipUnitTests
```

## Instalação
Está aplicação pode tanto ser executada via IDE de desenvolvimento (IntelliJ, Spring Tool Suite, VSCode e, outros...), como  ser instalada em ambiente windowns.

####  Intalação IDE
- Baixe pacote do repositorio  em ambiente local.
- Importe o projeto para sua IDE de desenvolvimento
- Altere a configuração do atributo `environment-type: DEV`, no arquivo de configurações `application.yml`
- Verifique se o arquivo para importação CSV está no local correto `pathProject::resources/csv`, cabe salientar que é possível alterar este arqiuvo e seus dados, entretanto, seu cabeçalho de parmanecer inalterado.
- Rode o projeto através da classe main `com.movies.data.moviesdata.MoviesdataApplication`

####  Intalação Local
- Baixe pacote do repositorio  em ambiente local.
- Execute o comando da seção `BUILD -> Build Completo`
- Copie o artefado gerado na pasta ´pathProject::moviesdata\target\moviesdata-0.0.1-SNAPSHOT.zip´ e cole em local de seu desejo
- Descompacte o artefato
- - Verifique se o arquivo para importação CSV está no local correto `pathProject::MovieData/csv`, cabe salientar que é possível alterar este arqiuvo e seus dados, entretanto, seu cabeçalho de parmanecer inalterado.
- Retire o su-fixo `.template` dos arquivos `application.yml` e `application.yml` no caminho `pathProject::MovieData`
- Altere a configuração do atributo `environment-type: PROD`, no arquivo de configurações `application.yml`
- Retire o su-fixo `.template` dos arquivos `startup_client.bat` no caminho `pathProject::MovieData`
- Execute o arquivo `startup_client.bat`

####  Documentação
- Acesse `http://localhost:8080/v3/api-docs/` para ter acesso à documentação.
- Acesso `http://localhost:8080/swagger-ui/index.html` para ser possível importar os endPoints em alguma aplicação de interação com APIRest. 
