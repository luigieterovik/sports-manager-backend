# 🏀 Sports Manager API

**Sports Manager** é uma API RESTful desenvolvida em **Java com Spring Boot**, com foco na gestão de **reservas de quadras esportivas**. O sistema permite que empresas cadastrem quadras e que usuários realizem reservas com cálculo automático de preços baseado no **dia e horário selecionado**.

---

## 📌 Funcionalidades

- Registro e login de usuários com autenticação segura
- Cadastro e listagem de quadras por empresas
- Definição de preços por período, dia da semana e horário
- Reservas realizadas por usuários autenticados
- Cálculo automático do valor da reserva
- Operações completas de CRUD nas entidades principais
- Arquitetura MVC e estrutura escalável para contextos reais

---

## 🚀 Tecnologias utilizadas

- **Java 21**
- **Spring Boot**
- **PostgreSQL**
- **Docker**
- **Spring Security**
- **JPA/Hibernate**

---

## 📂 Estrutura do Projeto
```
src/
├── controller/ # Controllers das rotas da API
│ ├── CourtController
│ ├── PriceController
│ ├── ReservationController
│ └── UserController
├── dto/ # Data Transfer Objects para entrada/saída de dados
├── model/ # Entidades do sistema (JPA Models)
│ ├── User
│ ├── Court
│ ├── Reservation
│ └── Prices
├── repository/ # Interfaces dos repositórios (JPA)
├── service/ # Lógica de negócio
├── infra/ # Segurança, autenticação e configuração de CORS
└── application.properties
```

---

## 🔐 Autenticação

- O sistema utiliza **Spring Security com JWT** para autenticação de usuários.
- As rotas sensíveis exigem um token de autenticação no header `Authorization`.

---

## 📚 Endpoints

### 🧍 Usuário (`/users`)
- `POST /register` – Registro de novo usuário
- `POST /login` – Login e retorno de token JWT

### 🏟️ Quadras (`/courts`)
- `GET /findAll` – Lista todas as quadras

### 💲 Preços (`/prices`)
- `GET /findAll` – Lista todos os preços configurados

### 📅 Reservas (`/reservation`)
- `POST /` – Cria uma nova reserva
- `GET /` – Lista todas as reservas (admin)
- `GET /{id}` – Consulta uma reserva por ID
- `GET /listByUser` – Lista reservas do usuário autenticado
- `DELETE /{id}` – Cancela uma reserva

---

## ⚙️ Executando o projeto com Docker

Certifique-se de ter o Docker instalado. Para subir o projeto:
```
docker-compose up --build
```
Isso irá criar containers para o backend e banco de dados PostgreSQL.

---

## 📌 Observações

- Algumas entidades como `Court`, `Reservation` e `Prices` possuem **relacionamentos complexos** (1:N, N:N) modelados com **JPA/Hibernate**.
- As validações e formatações dos dados são feitas por meio de **DTOs** e **anotações** do Spring.
- A configuração de segurança e CORS está localizada no pacote `infra`.
