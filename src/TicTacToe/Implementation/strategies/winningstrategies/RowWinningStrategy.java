package TicTacToe.Implementation.strategies.winningstrategies;

import TicTacToe.Implementation.models.Board;
import TicTacToe.Implementation.models.Move;
import TicTacToe.Implementation.models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy{
    private Map<Integer, Map<Symbol, Integer>> rowCounts = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        //When a move is being made for the first time in a row
        if(!rowCounts.containsKey(row)){
            rowCounts.put(row, new HashMap<>());
        }
        //Get the initialized map for that row to store symbol's count
        Map<Symbol, Integer> rowMap = rowCounts.get(row);
        //Get the symbol of the player
        Symbol symbol = move.getPlayer().getSymbol();
        //If  a symbol is coming for the first time, then initialize it with 1
        if(!rowMap.containsKey(symbol)){
            rowMap.put(symbol, 0);
        }
        rowMap.put(symbol, rowMap.get(symbol)+1);
        //Check if the move is a winning move
        return rowMap.get(symbol) == board.getDimension();
    }
}
