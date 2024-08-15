package TicTacToe.Implementation.controllers;

import TicTacToe.Implementation.exceptions.BotCountExceededException;
import TicTacToe.Implementation.exceptions.DuplicateSymbolFoundException;
import TicTacToe.Implementation.exceptions.InvalidMoveException;
import TicTacToe.Implementation.exceptions.InvalidPlayerCountException;
import TicTacToe.Implementation.models.Game;
import TicTacToe.Implementation.models.GameState;
import TicTacToe.Implementation.models.Player;
import TicTacToe.Implementation.strategies.winningstrategies.WinningStrategy;

import java.util.List;

public class GameController {
    public Game startGame(int dimension, List<Player> players, List<WinningStrategy> winningStrategy) throws InvalidPlayerCountException, BotCountExceededException, DuplicateSymbolFoundException {
        return Game.getBuilder().setDimension(dimension)
                .setPlayers(players)
                .setWinningStrategies(winningStrategy)
                .build();
    }

    public void makeMove(Game game) throws InvalidMoveException {
        game.makeMove();
    }

    public GameState getGameState(Game game){
        return game.getGameState();
    }

    public void printBoard(Game game){
        game.printBoard();
    }

    public Player getWinner(Game game){
        return game.getWinner();
    }
}
