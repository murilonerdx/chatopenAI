package com.murilonerdx.basico;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChessGame extends JFrame {
    // Constantes para as cores das peças e dos quadrados do tabuleiro
    private static final Color LIGHT_SQUARE_COLOR = new Color(240, 217, 181);
    private static final Color DARK_SQUARE_COLOR = new Color(181, 136, 99);
    private static final Color WHITE_PIECE_COLOR = Color.WHITE;
    private static final Color BLACK_PIECE_COLOR = Color.BLACK;

    // Constantes para as peças
    private static final String KING = "king";
    private static final String QUEEN = "queen";
    private static final String ROOK = "rook";
    private static final String BISHOP = "bishop";
    private static final String KNIGHT = "knight";
    private static final String PAWN = "pawn";

    // Matriz para armazenar as peças do tabuleiro
    private ChessPiece[][] board;

    // Construtor
    public ChessGame() {
        // Inicialize a matriz de peças
        board = new ChessPiece[8][8];

        // Crie o tabuleiro como uma grade de botões
        JPanel boardPanel = new JPanel(new GridLayout(8, 8));
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                // Crie um botão para representar o quadrado
                JButton square = new JButton();
                square.setPreferredSize(new Dimension(80, 80));
                square.setOpaque(true);
                square.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                // Defina a cor do quadrado com base nas coordenadas
                if ((row + col) % 2 == 0) {
                    square.setBackground(LIGHT_SQUARE_COLOR);
                } else {
                    square.setBackground(DARK_SQUARE_COLOR);
                }

                // Adicione um listener de clique para mover as peças
                square.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Obtenha as coordenadas do quadrado clicado
                        int row = (int) square.getClientProperty("row");
                        int col = (int) square.getClientProperty("col");

                        // Verifique se há uma peça selecionada
                        ChessPiece selectedPiece = getSelectedPiece();
                        if (selectedPiece != null) {
                            // Verifique se o movimento é válido e mova a peça se for
                            if (isValidMove(selectedPiece, row, col)) {
                                movePiece(selectedPiece, row, col);
                            }
                        } else if (board[row][col] != null) {
                            // Selecione a peça clicada
                            selectPiece(board[row][col]);
                        }
                    }
                });

                // Armazene as coordenadas do quadrado como propriedades do botão
                square.putClientProperty("row", row);
                square.putClientProperty("col", col);

                // Adicione o botão ao tabuleiro
                boardPanel.add(square);
            }
        }

        // Coloque as peças em suas posições iniciais
        board[0][0] = new ChessPiece(ROOK, WHITE_PIECE_COLOR);
        board[0][1] = new ChessPiece(KNIGHT, WHITE_PIECE_COLOR);
        board[0][2] = new ChessPiece(BISHOP, WHITE_PIECE_COLOR);
        board[0][3] = new ChessPiece(QUEEN, WHITE_PIECE_COLOR);
        board[0][4] = new ChessPiece(KING, WHITE_PIECE_COLOR);
        board[0][5] = new ChessPiece(BISHOP, WHITE_PIECE_COLOR);
        board[0][6] = new ChessPiece(KNIGHT, WHITE_PIECE_COLOR);
        board[0][7] = new ChessPiece(ROOK, WHITE_PIECE_COLOR);
        for (int col = 0; col < 8; col++) {
            board[1][col] = new ChessPiece(PAWN, WHITE_PIECE_COLOR);
        }
        board[7][0] = new ChessPiece(ROOK, BLACK_PIECE_COLOR);
        board[7][1] = new ChessPiece(KNIGHT, BLACK_PIECE_COLOR);
        board[7][2] = new ChessPiece(BISHOP, BLACK_PIECE_COLOR);
        board[7][3] = new ChessPiece(QUEEN, BLACK_PIECE_COLOR);
        board[7][4] = new ChessPiece(KING, BLACK_PIECE_COLOR);
        board[7][5] = new ChessPiece(BISHOP, BLACK_PIECE_COLOR);
        board[7][6] = new ChessPiece(KNIGHT, BLACK_PIECE_COLOR);
        board[7][7] = new ChessPiece(ROOK, BLACK_PIECE_COLOR);
        for (int col = 0; col < 8; col++) {
            board[6][col] = new ChessPiece(PAWN, BLACK_PIECE_COLOR);
        }

// Adicione o tabuleiro à janela
        add(boardPanel);

// Configure a janela
        setTitle("Chess Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

// Exiba a janela
        setVisible(true);

        // Configure o evento de clique no tabuleiro
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JButton square = (JButton) boardPanel.getComponent(row * 8 + col);
                int finalRow = row;
                int finalCol = col;
                square.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Obtenha a peça selecionada
                        ChessPiece selectedPiece = getSelectedPiece();

                        // Obtenha a peça na posição clicada
                        ChessPiece clickedPiece = board[finalRow][finalCol];

                        // Se uma peça já estiver selecionada, tente mover a peça
                        if (selectedPiece != null) {
                            // Verifique se o movimento é válido
                            if (isValidMove(selectedPiece, finalRow, finalCol)) {
                                // Mova a peça
                                movePiece(selectedPiece, finalRow, finalCol);

                                // Deselecione a peça
                                selectedPiece.setSelected(false);
                                selectedPiece = null;
                            }
                        } else if (clickedPiece != null) {
                            // Selecione a peça clicada
                            selectPiece(clickedPiece);
                        }
                    }
                });
            }
        }
    }

    private ChessPiece selectedPiece;

    private ChessPiece getSelectedPiece() {
        return selectedPiece;
    }

    private void selectPiece(ChessPiece piece) {
        selectedPiece = piece;
        piece.setSelected(true);
    }

    private boolean isValidMove(ChessPiece piece, int row, int col) {
        // Obtenha a posição atual da peça
        int currentRow = (int) piece.getClientProperty("row");
        int currentCol = (int) piece.getClientProperty("col");

        // Verifique o tipo da peça
        switch (piece.getType()) {
            case PAWN:
                // Verifique se o movimento é válido para um peão
                if (piece.getColor() == Color.WHITE) {
                    // Verifique se o peão está na sua posição inicial e pode andar duas casas para a frente
                    if (currentRow == 6 && row == 4 && currentCol == col && board[5][col] == null) {
                        return true;
                    }
                    // Verifique se o peão está se movendo para a frente e não há peça bloqueando o caminho
                    if (row == currentRow - 1 && currentCol == col && board[row][col] == null) {
                        return true;
                    }
                    // Verifique se o peão está capturando uma peça em diagonal
                    if (row == currentRow - 1 && (col == currentCol - 1 || col == currentCol + 1) && board[row][col] != null) {
                        return true;
                    }
                } else {
                    // Verifique se o peão está na sua posição inicial e pode andar duas casas para a frente
                    if (currentRow == 1 && row == 3 && currentCol == col && board[2][col] == null) {
                        return true;
                    }
                    // Verifique se o peão está se movendo para a frente e não há peça bloqueando o caminho
                    if (row == currentRow + 1 && currentCol == col && board[row][col] == null) {
                        return true;
                    }
                    // Verifique se o peão está capturando uma peça em diagonal
                    if (row == currentRow + 1 && (col == currentCol - 1 || col == currentCol + 1) && board[row][col] != null) {
                        return true;
                    }
                }
                break;
            case ROOK:
                // Verifique se o movimento é válido para uma torre
                if (currentCol == col) {
                    int minRow = Math.min(currentRow, row);
                    int maxRow = Math.max(currentRow, row);
                    for (int r = minRow + 1; r < maxRow; r++) {
                        if (board[r][col] != null) {
                            return false;
                        }
                    }
                } else {
                    int minCol = Math.min(currentCol, col);
                    int maxCol = Math.max(currentCol, col);
                    for (int c = minCol + 1; c < maxCol; c++) {
                        if (board[row][c] != null) {
                            return false;
                        }
                    }
                }
                return true;
        }

        return true;
    }


    private void movePiece(ChessPiece piece, int row, int col) {
        // Remova a peça da posição atual
        int currentRow = (int) piece.getClientProperty("row");
        int currentCol = (int) piece.getClientProperty("col");
        board[currentRow][currentCol] = null;

        // Coloque a peça na nova posição
        board[row][col] = piece;

        // Atualize o tabuleiro
        updateBoard();
    }

    private void updateBoard() {
        // Obtenha o painel do tabuleiro
        JPanel boardPanel = (JPanel) getContentPane().getComponent(0);

        // Atualize os botões do tabuleiro com as peças
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                ChessPiece piece = board[row][col];
                JButton square = (JButton) boardPanel.getComponent(row * 8 + col);
                if (piece != null) {
                    // Defina a cor e o texto do botão com base na peça
                    square.setForeground(piece.getColor());
                    square.setText(piece.getType());

                    // Destaque o botão se a peça estiver selecionada
                    if (piece.isSelected()) {
                        square.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                    } else {
                        square.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    }
                } else {
                    // Limpe o botão se não houver peça
                    square.setForeground(null);
                    square.setText(null);
                    square.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                }
            }
        }
    }

    public static void main(String[] args) {
        // Crie uma nova instância do jogo de xadrez
        new ChessGame();
    }
}