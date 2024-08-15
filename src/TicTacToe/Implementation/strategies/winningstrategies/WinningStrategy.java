package TicTacToe.Implementation.strategies.winningstrategies;

import TicTacToe.Implementation.models.Board;
import TicTacToe.Implementation.models.Move;

public interface WinningStrategy {
    boolean checkWinner(Board board, Move move);
}
