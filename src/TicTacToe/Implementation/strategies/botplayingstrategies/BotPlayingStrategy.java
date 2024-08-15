package TicTacToe.Implementation.strategies.botplayingstrategies;

import TicTacToe.Implementation.models.Board;
import TicTacToe.Implementation.models.Move;

public interface BotPlayingStrategy {
    Move makeMove(Board board);
}
