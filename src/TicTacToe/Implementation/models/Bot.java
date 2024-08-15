package TicTacToe.Implementation.models;

import TicTacToe.Implementation.factory.BotPlayingStrategyFactory;
import TicTacToe.Implementation.strategies.botplayingstrategies.BotPlayingStrategy;

public class Bot extends Player {
    /* By default, Bot will inherit all the properties of Player class, so it will also inherit makeMove()
    method and if we don't override the makeMove() method, it will use its parent makeMove() method, so we
    need to override makeMove() method in Bot class.
    * */
    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public Bot(String name, Symbol symbol, BotDifficultyLevel botDifficultyLevel) {
        super(name, symbol, PlayerType.BOT);
        this.botDifficultyLevel = botDifficultyLevel;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategy(botDifficultyLevel);
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }

    @Override
    public Move makeMove(Board board){
        Move move = botPlayingStrategy.makeMove(board);
        move.setPlayer(this);
        return move;
    }
}
