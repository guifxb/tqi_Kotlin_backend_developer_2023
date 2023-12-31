﻿# TQI Kotlin Backend developer 2023

Esta API foi criada como parte do processo seletivo TQI Kotlin Backend Developer 2023. Ela consiste em uma solução back-end desenvolvida em Kotlin e Spring Boot, uma plataforma de venda de autoatendimento para o supermercado JuMarket.


## Conteúdo

- [Funcionalidades](#funcionalidades)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Banco de dados](#banco-de-dados)
- [Documentação](#documentação)
- [Testes](#testes)
- [Perfis de execução](#perfis-de-execução)
- [Executando a API](#executando-a-api)
- [License](#license)



## Funcionalidades

A API do JuMarket possui as seguintes funcionalidades:

- Cadastro de categorias de produtos com o nome.
- Cadastro de produtos com informações como nome, unidade de medida, preço unitário, código de barras e estoque.
- Cadastro de clientes.
- Carrinho de vendas que permite adicionar produtos com suas quantidades, calcular o preço por unidade, calcular o preço total por produto (unidade x quantidade) e dar a opção ao cliente de colocar o CPF na nota.
- Finalização de venda com opções de pagamento por crédito, débito, PIX ou dinheiro.
- Relatório de vendas total, por cliente, por período ou por ID de venda.


## Tecnologias Utilizadas
- Linguagem de Programação: Kotlin
- Framework: Spring Boot
- Banco de Dados: PostgreSQL (executado em container Docker)
- Documentação: OpenAPI Swagger
- Testes de Serviço: JUnit
- Perfis: -dev utiliza H2 para testes, -prod usa PostgreSQL com aplicação em container Docker.


## Banco de Dados

A API utiliza o banco de dados PostgreSQL, que é executado através de um container Docker para garantir a portabilidade e facilidade de configuração.

### Diagrama ER

![dbdiagram.png](files%2Fdbdiagram.png)


## Documentação

A API é documentada utilizando o OpenAPI Swagger, o que facilita o entendimento das rotas disponíveis, seus parâmetros e respostas esperadas.

Para ver a documentação, visite: [Swagger UI](http://localhost:8080/swagger-ui.html).


## Testes

A camada de serviços da API é testada usando JUnit para garantir a qualidade e a integridade das funcionalidades implementadas.

![test_ss.png](files%2Ftest_ss.png)

Para testar os endpoints, importe para o PostMan (ou aplicativo semelhante) o arquivo `JuMarket API Tests.postman_collection.json` que está na pasta files. 

## Perfis de Execução

Foram definidos dois perfis de execução:

- **-dev**: Utiliza o banco de dados H2 para testes locais.
- **-prod**: Utiliza o banco de dados PostgreSQL em conjunto com Docker Compose para execução em ambiente de produção.


## Executando a API

1. Clone o repositório.
2. Para executar a API em ambiente de desenvolvimento, utilize o seguinte comando (Com banco H2 em memória):
   `./gradlew bootrun --args='--spring.profiles.active=dev'`
3. Para executar a API em contêiner com PostgreSQL:
   `docker-compose up --build `
4. Com a API rodando, consulte a documentação em:
   [Swagger UI](http://localhost:8080/swagger-ui.html)



### 🛒🛍️ Happy shopping! 🛒🛍️


## License

This project is licensed under the MIT License. Please refer to the <a href="https://github.com/digitalinnovationone/spring-boot-3-rest-api-template/blob/main/LICENSE.md">(LICENSE)</a> file for details.

