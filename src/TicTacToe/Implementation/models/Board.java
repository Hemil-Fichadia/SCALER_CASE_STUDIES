package TicTacToe.Implementation.models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int dimension;
    private List<List<Cell>> board;

    public Board(int dimension){
        this.dimension = dimension;

        //Here board got initialized
        board = new ArrayList<>();

        for(int row = 0; row < dimension; row++){
            //Here each row arraylist is added
            board.add(new ArrayList<>());

            for(int col = 0; col < dimension; col++){
                /* As this is not an actual matrix, we have to manually add values of row and col and
                each of the row arraylist will be having each cell of Cell type which contains values of
                row and column and CellState is set to EMPTY.
                */
                board.get(row).add(new Cell(row, col));
            }
        }
    }
    public void print(){
        System.out.println("Game board with dimensions : " + dimension + " X " + dimension);
        for(int row = 0; row < dimension; row++){
            for(int col = 0; col < dimension; col++){
                Cell cell = board.get(row).get(col);
                if(cell.getCellState().equals(CellState.FILLED)){
                    System.out.print("| " + cell.getPlayer().getSymbol().getAlphabet());
                }
                else{
                    System.out.print("| - ");
                }
            }
            System.out.print("|");
            System.out.println();
        }
    }
    public int getDimension() {
        return dimension;
    }
    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public List<List<Cell>> getBoard() {
        return board;
    }
    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }
}
