# Segundo Exercicio Avaliação técnica – Desenvolvimento | Navita

## Crie uma Web API REST para o gerenciamento de patrimônios de uma empresa.

Primeiramente, foi definido a tecnologia JAVA + PostgreSQL.

### Tecnologias utilizadas no desenvolvimento:

- Spring Boot
- PostgreSQL
- Postman ([Postman Workspace](https://github.com/joaokage1/SegundoExercicio/blob/master/src/main/resources/workspace_postman/Patrimonios.postman_collection.json) )
- Docker

O postgre teve seu escopo definido em container docker, criado um Dockerfile, que está disponível na raiz do projeto, na pasta docker.
Com o dockerfile pronto foram feitos os seguintes comandos:

- Para construção da imagem:
 > docker build -t

- Criar o container do PostgreSQL:
 > docker network create rede_postgresql
 
 > docker volume create --name postgresql_data
 
 > docker run -i -t -d --name postgresql --net=rede_postgresql -p 5432:5432 -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=12345 --volume postgresql_data:/var/lib/postgresql/data j_vgo/postgresql

- Instalar o pdagmin4:
 > docker volume create --name pgadmin_data
 
 > docker run -i -t -d --name pgadmin4 --net=rede_postgresql -p 5435:80 -e PGADMIN_DEFAULT_EMAIL=admin@email.com -e PGADMIN_DEFAULT_PASSWORD=12345 --volume pgadmin_data:/var/lib/pgadmin dpage/pgadmin4:latest
 
 Com isso:
 ![](https://raw.githubusercontent.com/joaokage1/SegundoExercicio/master/src/main/resources/assets/docker_desktop.PNG)
 
 Com os dois containers rodando, podemos abrir o PG Admin e criar a configuração do server
 
 ![](https://raw.githubusercontent.com/joaokage1/SegundoExercicio/master/src/main/resources/assets/pgAdmin.PNG)
 >General → Name: PostgreSQL Container
Connection → Host name/address: postgresql
Connection → Username: admin
Connection → Password: 12345

#### NOTE: O script da criação das sequences e tabelas está em: [src/main/resources/db/db_script.sql](https://github.com/joaokage1/SegundoExercicio/blob/master/src/main/resources/db/db_script.sql) 

### Dependencias e bibliotecas extras do projeto:

- Lombok
- Stream API
- Lambda

O lombok foi utilizado pela praticidade e pela eficiência em remover códigos verbosos, aqui utilizado para __injeção de dependência via construtor__, __construtores padrão__ e __getters and setters__. 

Durante e após o desenvolvimento foi utilizado o Postman, para testes e aqui estão alguns exemplos:

- Cadastro de usuário:
![](https://raw.githubusercontent.com/joaokage1/SegundoExercicio/master/src/main/resources/assets/cadastro_usuario.PNG)

- Buscar marcas antes de logar:
![](https://raw.githubusercontent.com/joaokage1/SegundoExercicio/master/src/main/resources/assets/buscarMarcas_forbidden.PNG)

- Logando:
![](https://raw.githubusercontent.com/joaokage1/SegundoExercicio/master/src/main/resources/assets/entrar_usuario.PNG)


- Buscar marcas depois de logar:
![](https://raw.githubusercontent.com/joaokage1/SegundoExercicio/master/src/main/resources/assets/buscarMarcas_ok.PNG)

- Buscar patrimonios:
![](https://raw.githubusercontent.com/joaokage1/SegundoExercicio/master/src/main/resources/assets/patrimonios_ok.PNG)

### Com a configuração do Postgres, fazendo clone do projeto e aplicando workspace do postman, também poderá ser feito testes dos endpoints. 
