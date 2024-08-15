package TicTacToe.Implementation.strategies.botplayingstrategies;

import TicTacToe.Implementation.models.Board;
import TicTacToe.Implementation.models.Cell;
import TicTacToe.Implementation.models.CellState;
import TicTacToe.Implementation.models.Move;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Move makeMove(Board board) {
        /* In this easy bot playing strategy, bot iterates on board and makes move on the first
        empty cell it comes across
        * */
        for(List<Cell> cells : board.getBoard()){
            for(Cell cell : cells){
                if(cell.getCellState().equals(CellState.EMPTY)){
                    return new Move(null, cell);
                }
            }
        }
        return null;
    }
}
