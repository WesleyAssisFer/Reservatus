# 📅 Reservatus — Sistema de Reserva de Salas

**Reservatus** é um sistema desenvolvido em **Java** com **Spring Boot** para gerenciar reservas de salas e laboratórios dentro de uma instituição.

O projeto foi criado para substituir o antigo controle manual em papel, trazendo organização, histórico digital e validação inteligente de conflitos.

---

## 🚀 Funcionalidades

### ✔️ Cadastro e Gestão
* **Agendamentos:** Cadastro completo envolvendo Professor, Sala e Horário.
* **Validação:** O sistema garante a integridade das reservas.

### ✔️ Validação Inteligente (Regras de Negócio)
O sistema impede automaticamente conflitos lógicos:
* ❌ Duas reservas na mesma **sala** no mesmo horário.
* ❌ O mesmo **professor** reservando locais diferentes no mesmo horário.

### ✔️ API REST Completa
* Criar agendamentos.
* Listar todos os registros.
* Filtragem por datas.
* Retorno de mensagens de erro personalizadas (`HTTP 400`) para o cliente.

### ✔️ Arquitetura em Camadas


[Image of Spring Boot architecture layered pattern diagram]

* **Controller:** Recebe as requisições HTTP.
* **Service:** Contém a lógica de negócio e validações.
* **Repository:** Camada de persistência e acesso ao banco.
* **DTOs:** Objetos para transferência de dados (entrada e saída).

---

## 🛠️ Tecnologias Utilizadas

* **Java 17+**
* **Spring Boot 3+**
* **Spring Web**
* **Spring Data JPA**
* **Banco de Dados:** H2 (Testes) / MySQL ou PostgreSQL (Produção)
* **Lombok**
* **Maven**

---

## 📌 Endpoints Principais

### ➤ Criar Agendamento
`POST /agendamento/salvar`

Cria um novo agendamento. Retorna erro `400 Bad Request` com mensagem explicativa caso exista conflito.

**Exemplo de Corpo (JSON):**
```json
{
  "professor": 1,
  "sala": 2,
  "horario": "2025-01-20T14:00",
  "descricao": "Aula de reforço"
}
```

### ➤ Listar Agendamentos
GET /agendamento

Retorna a lista completa de todos os agendamentos cadastrados.

## 🧠 Lógica de Validação
A segurança contra conflitos é feita na camada de Service.

No Service:
if (existeConflitoDeHorario(professor, sala, horario)) {
    throw new RuntimeException("Já existe um agendamento nesse horário.");
}

No Controller (Tratamento de Exceção):
catch (RuntimeException e) {
    return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(e.getMessage());
}
Isso garante que a resposta para o usuário seja clara e direta.

## 🗂️ Estrutura do Projeto
```text
src/
└── main/
    ├── java/com/grupoBL8/Reservatus/
    │   ├── Agendamento/
    │   │   ├── Controller/
    │   │   ├── Service/
    │   │   ├── Repository/
    │   │   ├── Model/
    │   │   └── AgendamentoDTO.java
    │   └── ...
    └── resources/
        ├── application.properties
        └── schema.sql (opcional para versionamento)
```

## 🏛️ Banco de Dados
O projeto suporta duas estratégias:

Com schema.sql: Controle total sobre tipos, tamanhos e versões da estrutura.

Sem schema: O Hibernate/Spring cria as tabelas automaticamente (ideal para dev/testes com H2).

## 🎨 Futuras Melhorias (Roadmap)
- Interface Web com calendário visual.

- Sistema de Login por cargo (Professor, Coordenador, Admin).

- Dashboard com métricas de ocupação semanal.

- Cadastro de Salas e Professores (CRUD) pelo próprio sistema.

- Notificação automática de confirmação (E-mail).

## 👨‍💻 Autor
Projeto desenvolvido como solução real para o colégio onde trabalho, visando modernizar e substituir o controle manual de reservas.

