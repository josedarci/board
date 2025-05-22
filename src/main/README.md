# Board Kanban

Projeto de um sistema simples de boards Kanban em Java, com gerenciamento de boards, colunas e cards via terminal.

## Funcionalidades

- Criar, listar, selecionar e excluir boards
- Adicionar colunas aos boards (tipos: INICIAL, CANCELAMENTO, FINAL, PENDENTE)
- Estrutura pronta para cards e manipulação de colunas

## Tecnologias

- Java 17+
- Gradle
- JUnit 5 (testes)
- Lombok

## Como executar

1. Clone o repositório:
2. git clone https://github.com/josedarci/board.git cd board
3. Compile o projeto:
4. ./gradlew build
5. ./gradlew run
Execute a aplicação:
./gradlew run

Ou rode diretamente a classe principal:

```bash
 java -cp build/classes/java/main com.board.service.Main
```
## Estrutura do Projeto

- `src/main/java/com/board/model` — Entidades (Board, Column, Card)
- `src/main/java/com/board/menu` — Menu interativo no terminal
- `src/main/java/com/board/repository` — Simulação de persistência em memória
- `src/main/java/com/board/service` — Classe principal (`Main.java`)
- `src/test/java` — Testes automatizados

## Contribuição

Pull requests são bem-vindos! Sinta-se à vontade para propor melhorias.

## Licença

Este projeto está sob a licença MIT.