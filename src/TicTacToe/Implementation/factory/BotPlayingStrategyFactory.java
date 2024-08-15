package TicTacToe.Implementation.factory;

import TicTacToe.Implementation.models.BotDifficultyLevel;
import TicTacToe.Implementation.strategies.botplayingstrategies.BotPlayingStrategy;
import TicTacToe.Implementation.strategies.botplayingstrategies.EasyBotPlayingStrategy;
import TicTacToe.Implementation.strategies.botplayingstrategies.HardBotPlayingStrategy;
import TicTacToe.Implementation.strategies.botplayingstrategies.MediumBotPlayingStrategy;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategy(BotDifficultyLevel difficultyLevel){
        if(difficultyLevel.equals(BotDifficultyLevel.EASY)){
            return new EasyBotPlayingStrategy();
        }
        else if(difficultyLevel.equals(BotDifficultyLevel.MEDIUM)){
            return new MediumBotPlayingStrategy();
        }
        else if(difficultyLevel.equals(BotDifficultyLevel.HARD)){
            return new HardBotPlayingStrategy();
        }
        return null;
    }
}
