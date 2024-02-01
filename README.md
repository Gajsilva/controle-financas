# Gerenciador de Finanças Pessoais

Este é um projeto de **Gerenciador de Finanças Pessoais** desenvolvido em **Spring Boot** com **Thymeleaf** para renderização de páginas HTML. O projeto permite adicionar e visualizar transações financeiras.

## Funcionalidades

- **Adição de Transações:** Os usuários podem adicionar despesas e receitas, especificando categorias, datas e valores.
- **Visualização de Transações:** Permite a visualização de todas as transações registradas.
- **Relatórios Financeiros:** Geração de relatórios que mostram o resumo das despesas e receitas ao longo do tempo.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Thymeleaf
- Spring Data JPA
- (Adicione outras tecnologias utilizadas)

## Configuração do Ambiente de Desenvolvimento

1. Clone o repositório.
2. Configure as propriedades do banco de dados no arquivo `application.properties`.
3. Execute a aplicação localmente.

## Como Usar

1. Acesse a aplicação através do navegador: [http://localhost:8080](http://localhost:8080).
2. Adicione novas transações, especificando categorias, datas e valores.
3. Visualize todas as transações registradas.
4. Gere relatórios financeiros para análise.

## Exemplos de Requisições

### Adicionar Nova Transação

```http
POST /add-transacao
{
  "date": "2024-01-28",
  "categoria": "Alimentação",
  "descricao": "Restaurante XYZ",
  "tipo": "Despesa",
  "valorTotal": 50.00
}

