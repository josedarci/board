package com.board.menu;

import com.board.model.Board;
import com.board.model.Column;
import com.board.model.ColumnType;
import com.board.repository.BoardRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class BoardMenu {

    private final Scanner scanner ;
    private final BoardRepository boardRepository ;

    public BoardMenu(Scanner scanner, BoardRepository boardRepository) {
        this.scanner = scanner;
        this.boardRepository = boardRepository;
    }
    public void start() {
        while (true) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Criar novo board");
            System.out.println("2. Listar boards");
            System.out.println("3. Selecionar board por ID");
            System.out.println("4. Excluir board");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1" -> criarBoard();
                case "2" -> listarBoards();
                case "3" -> selecionarBoard();
                case "4" -> excluirBoard();
                case "0" -> {
                    System.out.println("Encerrando...");
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    void criarBoard() {
        System.out.print("Nome do board: ");
        String nome = scanner.nextLine();

        Board board = new Board();
        board.setName(nome);
        boardRepository.save(board);

        System.out.println("✅ Board criado com ID: " + board.getId());
    }

    private void adicionarColunaAoBoard() {
        System.out.print("Digite o ID do board onde deseja adicionar a coluna: ");
        Long boardId = Long.parseLong(scanner.nextLine());

        Board board = boardRepository.findById(boardId);
        if (board == null) {
            System.out.println("❌ Board não encontrado.");
            return;
        }

        System.out.print("Digite o nome da nova coluna: ");
        String nomeColuna = scanner.nextLine();

        System.out.print("Digite o tipo da coluna (INICIAL, CANCELAMENTO, FINAL, PENDENTE): ");
        String tipoTexto = scanner.nextLine().toUpperCase();

        ColumnType tipo;
        try {
            tipo = ColumnType.valueOf(tipoTexto);
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Tipo inválido. Use: INICIAL, CANCELAMENTO, FINAL ou PENDENTE.");
            return;
        }

        Column novaColuna = Column.builder()
                .id(System.currentTimeMillis()) // ID gerado simples
                .name(nomeColuna)
                .type(tipo)
                .build();

        board.getColumns().add(novaColuna);

        // Ordenar colunas por tipo (INICIAL, CANCELAMENTO, FINAL, PENDENTE)
        board.getColumns().sort(Comparator.comparing(c -> c.getType().ordinal()));

        boardRepository.save(board); // Simulando persistência
        System.out.println("✅ Coluna adicionada com sucesso!");
    }


    private void listarBoards() {
        List<Board> boards = boardRepository.findAll();
        if (boards.isEmpty()) {
            System.out.println("Nenhum board cadastrado.");
            return;
        }
        boards.forEach(board ->
                System.out.println("ID: " + board.getId() + " | Nome: " + board.getName()));
    }

    private void selecionarBoard() {
        System.out.print("Digite o ID do board: ");
        Long id = Long.parseLong(scanner.nextLine());

        Board board = boardRepository.findById(id);
        if (board == null) {
            System.out.println("❌ Board não encontrado.");
        } else {
            System.out.println("🔍 Board selecionado: " + board.getName());
            // Em breve: ações sobre colunas e cards
        }
    }

    private void excluirBoard() {
        System.out.print("Digite o ID do board a excluir: ");
        Long id = Long.parseLong(scanner.nextLine());

        boardRepository.deleteById(id);
        System.out.println("🗑️ Board excluído com sucesso.");
    }
}
