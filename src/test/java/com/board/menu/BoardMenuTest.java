
package com.board.menu;

import com.board.model.Board;
import com.board.repository.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.mockito.Mockito.*;

class BoardMenuTest {

    private BoardRepository boardRepository;
    private BoardMenu boardMenu;

@BeforeEach
void setUp() {
    boardRepository = mock(BoardRepository.class);
    Scanner scanner = new Scanner(new ByteArrayInputStream("Meu Board\n".getBytes()));
    boardMenu = new BoardMenu(scanner, boardRepository);
}
    @Test
    void deveCriarBoardChamandoSaveNoRepositorio() {
        doAnswer(invocation -> {
            Board b = invocation.getArgument(0);
            b.setId(123L); // Simula ID gerado
            return null;
        }).when(boardRepository).save(any(Board.class));

        boardMenu.criarBoard();

        verify(boardRepository, times(1)).save(any(Board.class));
    }
}