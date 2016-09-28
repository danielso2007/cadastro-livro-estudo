# Cadastro de Livros

`versão: 2.3.1` (Spring MVC e Angular-ui-router)

# Preparando o projeto web

Na pasta `/src/main/webapp/designer`, digite:
1. `npm install`
2. `gulp`

# Rodando a aplicação

`mvn jetty:run`

Acesse o link: http://localhost:8080/app/index.html#/

# Teste

### Para rodar o teste, execute:
`mvn test -P test`
> O profile default não executa teste automaticamente. Isso foi feito para agilizar o build do projeto. `mvn test` sozinho irá skipped devido a configuração no surefire.

# Yeoman

yo angular

https://github.com/yeoman/generator-angular

(npm install -g  bower yo generator-karma generator-angular)

# Template

https://startbootstrap.com/template-overviews/sb-admin-2/

# Bootstrap

http://www.w3schools.com/bootstrap/bootstrap_ref_css_helpers.asp

# Spring MVC

http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html

# Spring Repository

http://docs.spring.io/spring-data/commons/docs/current/reference/html/

http://docs.spring.io/spring-data/data-commons/docs/1.12.3.RELEASE/reference/html/

http://projects.spring.io/spring-data/

# Spring com QueryDsl

http://www.querydsl.com/static/querydsl/4.1.3/reference/html_single/

https://spring.io/blog/2011/04/26/advanced-spring-data-jpa-specifications-and-querydsl/

http://www.querydsl.com/

# Dozer

http://dozer.sourceforge.net/documentation/gettingstarted.html

> Os dados vindos do front-end são transformado em TransferObject para depois, ao persistir, transformado em entity.
> Isso é para manter livre o object que representa a tela, do object o que representa entidade. O Object transferObject por ter qualquer atributo que a entidade não tenha, sem prejudicar a persistência.

#### Integração com Spring

http://dozer.sourceforge.net/documentation/springintegration.html

# Algumas configurações do Hibernate 5

https://docs.jboss.org/hibernate/orm/5.0/manual/en-US/html/ch03.html

# Migração de Banco de dados com Flyway

https://flywaydb.org/

> Não foi implementado totalmente porque não uso um banco de dados físico. Estou usando o hsqldb em memória.

# Teste com dbUnit

http://dbunit.sourceforge.net/
