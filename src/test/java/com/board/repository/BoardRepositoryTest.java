package com.board.repository;
import com.board.model.Board;
import org.junit.jupiter.api.Test;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BoardRepositoryTest {

    @Test
    void deveSalvarBoardUsandoMock() throws Exception {
        // Mocks
        Connection conn = mock(Connection.class);
        PreparedStatement stmt = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Configurações dos mocks
        when(conn.prepareStatement(anyString(), anyInt())).thenReturn(stmt);
        when(stmt.getGeneratedKeys()).thenReturn(rs);
        when(rs.next()).thenReturn(true);
        when(rs.getLong(1)).thenReturn(42L);

        // BoardRepository precisa ser adaptado para aceitar Connection mockado
        BoardRepository repository = spy(new BoardRepository());
        doReturn(conn).when(repository).getConnection();

        Board board = Board.builder().name("Mock Board").build();
        repository.save(board);

        assertEquals(42L, board.getId());
        verify(stmt).setString(1, "Mock Board");
        verify(stmt).executeUpdate();
    }
}