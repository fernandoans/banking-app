# Demo do Spring Boot com JPA e FlyWay

Este é um projeto de aprendizado para a integração do JPA e FlyWay com o Spring Boot.

![FlyWaySpring.png](FlyWaySpring.png)

## Classe Record (Java 16)

1. Java Record é uma classe especial que auxilia no encapsulamento relacionado a dados sem a necessidade para "boilerplate" (são seções de código que é repetido em muitos lugares com um mínimo de variação, a solução era mover isso para uma Classe abstrata).
2. Classe Record é usado como solução DTO (Data Transfer Object) em aplicações Spring Boot como uma solução eficiente para o encapsulamento de transferência de dados entre as camadas.
3. Records são conscisos, imutáveis e automaticamente implementam métodos construtores, getter(), equals(), hashCode() e toString() que são essenciais para DTO. 

## Flyway

Neste projeto utilizamos o Flyway como uma solução para controle de mudanças das modificações em tabelas e dados.
Não deixaremos o JPA controlar as mudanças automáticas em tabelas, pois o Flyway é uma solução mais estável além de gerar logs dessas mudanças e possibilidade de retorno de estado.

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

Com o contêiner ativo, criar o banco de dados através dos seguintes passos:

1. Entrar no contêiner:
```
$ docker exec -it meu-mysql bash
```
2. Acessar o mysql:
```
bash-4.4# mysql -uroot -p
```
3. Ao digitar a senha root, criar o banco de dados e sair do contêiner:
```
mysql> create database banking_app;
mysql> exit
bash-4.4# exit
```
