package com.board.repository;

import com.board.model.Board;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



/**
 * Classe responsável por gerenciar a persistência de dados dos boards.
 * Utiliza JDBC para se conectar ao banco de dados MySQL.
 * Esta classe utiliza JDBC para se conectar a um banco de dados MySQL e realizar operações CRUD (Create, Read, Update, Delete).
 * Ela contém métodos para salvar, buscar todos, buscar por ID e excluir boards.
 * Utiliza o padrão de projeto Repository, que separa a lógica de acesso a dados da lógica de negócios,
 * facilitando a manutenção e a testabilidade do código.
 * Além disso, utiliza o padrão Builder para criar instâncias de Board, tornando o código mais legível e fácil de entender.
 */

public class BoardRepository {

    private static final String URL = "jdbc:mysql://localhost:3306/boarddb";
    private static final String USER = "root"; // substitua pelo seu usuário
    private static final String PASSWORD = "123456"; // substitua pela sua senha

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void save(Board board) {
        String sql = "INSERT INTO boards (name) VALUES (?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, board.getName());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                board.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar board", e);
        }
    }

    public List<Board> findAll() {
        List<Board> boards = new ArrayList<>();
        String sql = "SELECT id, name FROM boards";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Board board = Board.builder()
                        .id(rs.getLong("id"))
                        .name(rs.getString("name"))
                        .build();
                boards.add(board);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar boards", e);
        }

        return boards;
    }

    public Board findById(Long id) {
        String sql = "SELECT id, name FROM boards WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Board.builder()
                            .id(rs.getLong("id"))
                            .name(rs.getString("name"))
                            .build();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar board por ID", e);
        }

        return null;
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM boards WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir board", e);
        }
    }
}