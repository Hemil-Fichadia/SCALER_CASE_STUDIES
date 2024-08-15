package TicTacToe.Implementation.models;

import java.util.Scanner;

public class Player {
    private String name;
    private Symbol symbol;
    private PlayerType playerType;
    private static Scanner scanner;

    public Player(String name, Symbol symbol, PlayerType playerType){
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
        scanner = new Scanner(System.in);
    }

    public Move makeMove(Board board){
        System.out.println("Please enter coordinates to make move");
        //Ask row coordinate
        System.out.print("Enter row : ");
        int row = scanner.nextInt();
        //Ask col coordinate
        System.out.print("Enter col : ");
        int col = scanner.nextInt();
        return new Move(this, new Cell(row, col));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
}
