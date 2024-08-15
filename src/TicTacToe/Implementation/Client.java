package TicTacToe.Implementation;

import TicTacToe.Implementation.controllers.GameController;
import TicTacToe.Implementation.exceptions.BotCountExceededException;
import TicTacToe.Implementation.exceptions.DuplicateSymbolFoundException;
import TicTacToe.Implementation.exceptions.InvalidMoveException;
import TicTacToe.Implementation.exceptions.InvalidPlayerCountException;
import TicTacToe.Implementation.models.*;
import TicTacToe.Implementation.strategies.winningstrategies.ColWinningStrategy;
import TicTacToe.Implementation.strategies.winningstrategies.DiagonalWinningStrategy;
import TicTacToe.Implementation.strategies.winningstrategies.RowWinningStrategy;
import TicTacToe.Implementation.strategies.winningstrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) throws InvalidPlayerCountException, BotCountExceededException, InvalidMoveException, DuplicateSymbolFoundException {
        System.out.println("TicTacToe Game");

        int dimension = 3;
        List<Player> players = new ArrayList<>();
//        players.add(new Player("Hemil", new Symbol('X'), PlayerType.HUMAN));
//        players.add(new Player("Deepak", new Symbol('O'), PlayerType.HUMAN));
        players.add(new Player("Hemil", new Symbol('X'), PlayerType.HUMAN));
        players.add(new Bot("SCALER", new Symbol('O'), BotDifficultyLevel.EASY));

        List<WinningStrategy> winningStrategies = List.of(
                new RowWinningStrategy(),
                new ColWinningStrategy(),
                new DiagonalWinningStrategy()
        );

        GameController gameController = new GameController();

        Game game = gameController.startGame(dimension, players, winningStrategies);

        while(game.getGameState().equals(GameState.IN_PROGRESS)){
            //1 Print the board
            //2. Ask user to where they want to make the move
            gameController.printBoard(game);

            gameController.makeMove(game);
        }

        if(game.getGameState().equals(GameState.ENDED)){
            gameController.printBoard(game);
            System.out.println(gameController.getWinner(game).getName() + " has won the game");
        }
        else{
            System.out.println("Game is drawn");
        }
    }
}
