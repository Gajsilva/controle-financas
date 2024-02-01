Gerenciador de Finanças Pessoais
Este é um projeto de Gerenciador de Finanças Pessoais desenvolvido em Spring Boot com Thymeleaf para renderização de páginas HTML. O projeto permite adicionar e visualizar transações financeiras.

Funcionalidades
Adição de Transações: Os usuários podem adicionar despesas e receitas, especificando categorias, datas e valores.
Visualização de Transações: Permite a visualização de todas as transações registradas.
Relatórios Financeiros: Geração de relatórios que mostram o resumo das despesas e receitas ao longo do tempo.
Tecnologias Utilizadas
Java
Spring Boot
Thymeleaf
Spring Data JPA
(Adicione outras tecnologias utilizadas)
Configuração do Ambiente de Desenvolvimento
Clone o repositório.
Configure as propriedades do banco de dados no arquivo application.properties.
Execute a aplicação localmente.
Como Usar
Acesse a aplicação através do navegador: http://localhost:8080.
Adicione novas transações, especificando categorias, datas e valores.
Visualize todas as transações registradas.
Gere relatórios financeiros para análise.
Exemplos de Requisições
Adicionar Nova Transação
bash
Copy code
POST /add-transacao
Corpo da requisição (JSON):

json
Copy code
{
  "date": "2024-01-28",
  "category": "Alimentação",
  "description": "Restaurante XYZ",
  "type": "Despesa",
  "amount": 50.00
}
Obter Todas as Transações
bash
Copy code
GET /transacoes
Contribuição
Siga as diretrizes de contribuição se quiser contribuir para este projeto.

Faça o fork do projeto.
Crie uma nova branch com suas alterações: git checkout -b feature/nova-feature.
Faça commit das suas alterações: git commit -m 'Adiciona nova feature'.
Faça o push para a sua branch: git push origin feature/nova-feature.
Abra um Pull Request.
Licença
Este projeto está licenciado sob a Licença MIT - veja o arquivo LICENSE.md para detalhes.
