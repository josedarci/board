package com.board.service;

import com.board.menu.BoardMenu;
import com.board.repository.BoardRepository;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BoardRepository boardRepository = new BoardRepository();
        new BoardMenu(scanner, boardRepository).start();
    }
}