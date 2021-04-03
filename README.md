# README #

### Aplicação para gerecnciamento de senhas para fila de espera ###

* Version 1.0 beta

### How do I get set up? ###

* Configuration
* Dependencies: PSQL9.3, java1.7, maven

* Database configuration

user: postgres
password: admin

CREATE DATABASE senhas
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'en_US.UTF-8'
       LC_CTYPE = 'en_US.UTF-8'
       CONNECTION LIMIT = -1;
 
CREATE TABLE tasks
(
  id bigserial NOT NULL,
  data character varying(255),
  prioridade character varying(255),
  status character varying(255),
  CONSTRAINT tasks_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tasks
  OWNER TO postgres;
* How to run tests
* Deployment instructions

### Contribution guidelines ###

* Tests
cd /project
mvn jetty:run

localhost:8080/task

* Repo owner or admin 
llpassarelli@gmail.com