# xy-inc

## Geral

Aplicação feita para o desafio da Luiza Labs.
Foi utilizado o Java 19 para JDK.
O banco de dados utilizado foi o H2 por praticidade..

## Código

1. Utilizei uma arquitetura em camadas, muito similar ao MVC, para essa solução.
2. Para a camada de persistência utilizei a interface PointOfInterestRepository herdando a JpaRepository do próprio Spring.
3. Criei um serviço para tratar as operações necessárias para o desafio. Para o calculo da distância máxima, fiz uma consulta de todos os POIs e utilizei o filter do stream para filtrar apenas as que atendem o requisito.
4. O Controller expõe as APIs e faz o tratamento para requests e responses utilizando o JSON, como solicitado.
5. A classe ControllerErrorHandler é um controller advice que lida com todos as exceções que podem acontecer no service para garantir robustez e transparência para o usuário.

## Testes

1. Nos testes, criei uma classe de testes unitários para o serviço e criei também testes de integração para os endpoints expostos.
2. Para executar os testes, basta executar cada classe individualmente. Utilizando IDEs torna mais fácil a execução.

## Aplicação

1. Para subir a aplicação basta rodar a classe XyincApplication, que é responsável pela execução do SpringBoot.