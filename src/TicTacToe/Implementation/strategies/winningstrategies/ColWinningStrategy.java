package TicTacToe.Implementation.strategies.winningstrategies;

import TicTacToe.Implementation.models.Board;
import TicTacToe.Implementation.models.Move;
import TicTacToe.Implementation.models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class ColWinningStrategy implements WinningStrategy{
    private Map<Integer, Map<Symbol, Integer>> colCounts = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        int col = move.getCell().getCol();

        if(!colCounts.containsKey(col)){
            colCounts.put(col, new HashMap<>());
        }

        Symbol symbol = move.getPlayer().getSymbol();

        Map<Symbol, Integer> colMap = colCounts.get(col);
        if(!colMap.containsKey(symbol)){
            colMap.put(symbol, 0);
        }
        colMap.put(symbol, colMap.get(symbol)+1);

        return colMap.get(symbol) == board.getDimension();
    }
}
