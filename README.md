## Golden Transfers API

Uma API REST para agendamento de transferências financeiras desenvolvida como parte de um desafio técnico.

## Sobre o Projeto

O sistema permite que usuários agendem transferências financeiras entre contas, calculando automaticamente a taxa aplicável com base no prazo da transferência. Caso não haja taxa aplicável para o prazo informado, o sistema rejeita a operação e retorna um alerta.

## Decisões Arquiteturais

### Padrão Strategy
A principal decisão de design foi utilizar o **padrão Strategy** para o cálculo das taxas. Em vez de um grande bloco `if/else`, cada faixa de prazo tem sua própria classe responsável pelo cálculo. Isso torna o código mais fácil de manter e de evoluir — se uma nova faixa de taxa surgir no futuro, basta criar uma nova classe sem mexer nas existentes.

### Camadas da Aplicação
O projeto segue a separação clássica em camadas:
- **Controller** — recebe as requisições e retorna as respostas
- **Service** — orquestra a lógica de negócio
- **Strategy** — contém as regras de cálculo de taxa
- **Repository** — acesso ao banco de dados
- **DTO** — objetos de transferência de dados entre as camadas

### Banco de Dados em Memória
Foi utilizado o **H2** como banco de dados em memória, conforme solicitado. Os dados são perdidos ao reiniciar a aplicação, o que é ideal para o ambiente de desenvolvimento e testes.

## Tabela de Taxas

| Dias até a transferência | Taxa aplicada |
|--------------------------|---------------|
| 0 | R$ 3,00 fixo + 2,5% do valor |
| 1 a 10 | R$ 12,00 fixo |
| 11 a 20 | 8,2% do valor |
| 21 a 30 | 6,9% do valor |
| 31 a 40 | 4,7% do valor |
| 41 a 50 | 1,7% do valor |
| Acima de 50 | ❌ Não permitido |

## Tecnologias Utilizadas

- **Java 11**
- **Spring Boot 2.7.18**
- **Spring Data JPA**
- **Spring Validation**
- **H2 Database**
- **Lombok**
- **Springdoc OpenAPI (Swagger) 1.7.0**
- **JUnit 5**
- **Mockito**
- **Docker**

## Como Rodar o Projeto

### Pré-requisitos
- Java 11 instalado
- Maven instalado
- Docker e Docker Compose instalados (opcional)

### Rodando com Maven

Clone o repositório:
```bash
git clone https://github.com/seu-usuario/golden-transfers-api.git
cd golden-transfers-api
```

Suba a aplicação:
```bash
mvn spring-boot:run
```

### Rodando com Docker

```bash
# Build e sobe o container
docker-compose up --build

# Rodar em background
docker-compose up --build -d

# Parar
docker-compose down
```

### Acesso

Swagger para testar os endpoints:
http://localhost:8080/swagger-ui/index.html

Console do H2 para visualizar o banco:
http://localhost:8080/h2-console
> JDBC URL: `jdbc:h2:mem:golden_db` | Usuário: `gusta` | Senha: *(vazio)*

## Endpoints

| Método | URL | Descrição |
|--------|-----|-----------|
| `POST` | `/api/transfers` | Agenda uma nova transferência |
| `GET` | `/api/transfers` | Lista todos os agendamentos |

### Exemplo de requisição POST

```json
{
  "contaOrigem": "1234567890",
  "contaDestino": "0987654321",
  "valor": 1000.00,
  "dataTransferencia": "2026-06-20"
}
```

### Exemplo de resposta

```json
{
  "id": 1,
  "contaOrigem": "1234567890",
  "contaDestino": "0987654321",
  "valor": 1000.00,
  "taxa": 82.00,
  "dataTransferencia": "2026-06-20",
  "dataAgendamento": "2026-06-05"
}
```

## Testes

Para rodar os testes:
```bash
mvn test
```

Os testes cobrem as regras de cálculo de taxa de cada faixa, a factory que seleciona a estratégia correta e a service com mocks do repositório e da factory.

## Observações

- O Lombok está configurado no projeto, porém devido a um problema de processamento no ambiente local, os getters e setters foram implementados manualmente em algumas classes. Assim que o ambiente estiver corrigido, o código será revertido para as anotações do Lombok.
- O frontend em Vue.js está sendo desenvolvido em repositório separado.
