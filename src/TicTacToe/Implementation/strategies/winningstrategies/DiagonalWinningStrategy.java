package TicTacToe.Implementation.strategies.winningstrategies;

import TicTacToe.Implementation.models.Board;
import TicTacToe.Implementation.models.Move;
import TicTacToe.Implementation.models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class DiagonalWinningStrategy implements WinningStrategy{
    private Map<Symbol, Integer> leftDiagonalMap = new HashMap<>();
    private Map<Symbol, Integer> rightDiagonalMap = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();
        //This is for left diagonal
        if(row == col){
            if(!leftDiagonalMap.containsKey(symbol)){
                leftDiagonalMap.put(symbol, 0);
            }
            leftDiagonalMap.put(symbol, leftDiagonalMap.get(symbol)+1);
            return leftDiagonalMap.get(symbol) == board.getDimension();
        }
        //This is for right diagonal
        if(row + col == board.getDimension() - 1){
            if(!rightDiagonalMap.containsKey(symbol)){
                rightDiagonalMap.put(symbol, 0);
            }
            rightDiagonalMap.put(symbol, rightDiagonalMap.get(symbol)+1);
            return rightDiagonalMap.get(symbol) == board.getDimension();
        }
        return false;
    }
}
