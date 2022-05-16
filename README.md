# Gestão de Funcionarios (OAuth2 Resource Server)

Projeto backend de uma API REST que representa um microsserviço responsável pela gestão de funcionários. Este projeto é utilizado no módulo de segurança com Spring Security e OAuth2, servindo como Resource Server para a atividade.

### Atenção:
> Branch com Spring Security OAuth2 Resource Server configurado e testes de integração **corrigidos** para enviar Access Token em cada requisiçao.

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

4. Iniciar o Keycloack via Docker-Compose:

```shell
docker-compose -f docker-compose-keycloak.yml up -d
```

5. Acesse o Keycloack usando login `admin` e senha `admin`: http://localhost:18080/auth/admin/;
6. Crie o Realm `gestao-funcionarios`;
7. No Realm criado:
    - 7.1. crie os Users: `rafael.ponte` e `jordi.silva`;
    - 7.2. crie os Scopes: `funcionarios:read` e `funcionarios:write`;
    - 7.3. crie o Client: `gestao-funcionarios-client`;
        - configure _Access Type_ como `confidential`;
        - configure o _Standard Flow Enabled_ como `ON`;
        - configure o _Direct Access Grants_ Enabled como `ON`;
        - adicione os Scopes ao Client criado como escopos opcionais;

## Consumindo a API REST da aplicação

Aqui demonstramos através de alguns exemplos como você pode consumir a API REST exposta pela aplicação. Estamos utilizando o comando `cURL` como cliente HTTP mas você pode usar qualquer outro de sua preferência, como POSTman ou Insomnia. 

Dado que a aplicação esteja rodando, basta executar os comandos abaixo para exercitar os endpoints públicos da aplicação.

### Atenção:
> Lembre-se de passar o `access_token` no cabeçalho HTTP de cada requisição;

### Criando novo funcionário

Caso precise gerar CPFs únicos para exercitar este endpoint, basta gera-los [neste site](https://www.geradordecpf.org/).

```shell
curl --request POST \
  --url http://localhost:8080/oauth2-resourceserver-gestao-funcionarios/api/funcionarios \
  --header 'Authorization: Bearer <access_token>' \
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
  --url http://localhost:8080/oauth2-resourceserver-gestao-funcionarios/api/funcionarios/1 \
  --header 'Authorization: Bearer <access_token>'
```

### Listando todos os funcionarios
```shell
curl --request GET \
  --url http://localhost:8080/oauth2-resourceserver-gestao-funcionarios/api/funcionarios \
  --header 'Authorization: Bearer <access_token>'
```

### Removendo um funcionario existente
```shell
curl --request DELETE \
  --url http://localhost:8080/oauth2-resourceserver-gestao-funcionarios/api/funcionarios/1 \
  --header 'Authorization: Bearer <access_token>'
```

## Duvidas e suporte

Basta entrar em contato com a equipe da Zup Edu responsável pelo Bootcamp no horário comercial.