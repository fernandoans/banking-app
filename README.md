# Demo do Spring Boot com JPA e FlyWay

Este é um projeto de aprendizado para a integração do JPA e FlyWay com o Spring Boot.

## Execução

Primeiro é necessário construir um Contêiner com o Docker para o MySQL:
```
$ docker run --name meu-mysql -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 -d mysql:8.1.0
```

A versão 8.1.0 é a versão recomendada pela Flyway, testar com: SELECT version();

Parar com:
```
$ docker stop meu-mysql
```

Nas próximas vezes, iniciar com: 
```
$ docker start meu-mysql
```

E criar o banco de dados:
```
create database banking_app;
```
