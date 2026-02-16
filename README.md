# 🏷️ Projeto Cupom Tenda

Projeto **Spring Boot** para cadastro de cupons, com documentação automática via **Swagger** e suporte para execução local ou via **Docker**.

---

## ⚙️ Tecnologias

![Java](https://img.shields.io/badge/Java-21-blue)
![Maven](https://img.shields.io/badge/Maven-3.9.9-blue)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.x-brightgreen)
![Docker](https://img.shields.io/badge/Docker-Compose-blue)
![H2 Database](https://img.shields.io/badge/H2-Database-lightgrey)

- Java 21  
- Maven 3.9.9  
- Spring Boot 4.x  
- Springdoc OpenAPI (Swagger)  
- Banco de dados H2 (em memória)  
- Docker / Docker Compose  

---

## 🚀 Rodar localmente

Certifique-se de ter **Java 21** e **Maven 3.9.9** instalados.

1. Instale as dependências do projeto:

```bash
mvn install
```

```bash
mvn spring-boot:run
```

A aplicação estará disponível em:
[localhost 🔗](http://localhost:8080)

## 🐳 Rodar com Docker

1. Na raiz do projeto, execute:

```bash
docker-compose up --build
```
## 📖 Acessar Swagger

[swagger 🔗](http://localhost:8080/swagger-ui/index.html)

