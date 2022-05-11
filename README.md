# Gestão de Funcionarios (OAuth2 Resource Server)

Projeto backend de uma API REST que representa um microsserviço responsável pela gestão de funcionários. Este projeto é utilizado no módulo de segurança com Spring Security e OAuth2, servindo como Resource Server para a atividade.

## Tecnologias utilizadas:

Algumas das principais tecnologias utilizadas neste projeto:

1. Java 11;
2. Spring Boot 2.6.7;
3. Spring Data JPA com Hibernate;
4. Bean Validation;
5. Banco em memoria H2;
6. jUnit e Spring Testing;

## Ambiente de desenvolvimento

Para configurar e rodar a aplicação em ambiente local basta seguir os passos:

1. Clonar o repositório ou fazer seu download:

```shell
git clone git@github.com:zup-academy/oauth2-resourceserver-gestao-funcionarios.git
```

2. Importar o projeto na IDE IntelliJ;

3. Iniciar a aplicação via IDE ou linha de comando:

```shell
./mvnw spring-boot:run
``` 

## Consumindo a API REST da aplicação

Aqui demonstramos através de alguns exemplos como você pode consumir a API REST exposta pela aplicação. Estamos utilizando o comando `cURL` como cliente HTTP mas você pode usar qualquer outro de sua preferência, como POSTman ou Insomnia. 

Dado que a aplicação esteja rodando, basta executar os comandos abaixo para exercitar os endpoints públicos da aplicação.

### Criando novo funcionário

Caso precise gerar CPFs únicos para exercitar este endpoint, basta gera-los [neste site](https://www.geradordecpf.org/).

```shell
curl --request POST \
  --url http://localhost:8080/oauth2-resourceserver-gestao-funcionarios/api/funcionarios \
  --header 'Content-Type: application/json' \
  --data '{
	"nome": "Jordi Silva",
	"cpf": "340.310.970-42",
	"cargo": "GERENTE",
	"salario": 15981.55
}'
```

### Detalhando funcionario existente
```shell
curl --request GET \
  --url http://localhost:8080/oauth2-resourceserver-gestao-funcionarios/api/funcionarios/1
```

### Listando todos os funcionarios
```shell
curl --request GET \
  --url http://localhost:8080/oauth2-resourceserver-gestao-funcionarios/api/funcionarios
```

### Removendo um funcionario existente
```shell
curl --request DELETE \
  --url http://localhost:8080/oauth2-resourceserver-gestao-funcionarios/api/funcionarios/1
```

## Duvidas e suporte

Basta entrar em contato com a equipe da Zup Edu responsável pelo Bootcamp no horário comercial.