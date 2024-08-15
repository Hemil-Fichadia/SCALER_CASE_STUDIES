package TicTacToe.Implementation.models;

import TicTacToe.Implementation.exceptions.BotCountExceededException;
import TicTacToe.Implementation.exceptions.DuplicateSymbolFoundException;
import TicTacToe.Implementation.exceptions.InvalidMoveException;
import TicTacToe.Implementation.exceptions.InvalidPlayerCountException;
import TicTacToe.Implementation.strategies.winningstrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private List<Player> players;
    private Board board;
    private List<Move> moves;
    private Player winner;
    private GameState gameState;
    private int nextPlayerMoveIndex;
    private List<WinningStrategy> winningStrategies;

    private Game(List<Player> players, int dimensions, List<WinningStrategy> winningStrategies) {
        this.players = players;
        this.moves = new ArrayList<>();
        this.gameState = GameState.IN_PROGRESS;
        this.board = new Board(dimensions);
        this.nextPlayerMoveIndex = 0;
        this.winningStrategies = winningStrategies;
    }

    //Game methods
    public void printBoard(){
        board.print();
    }

    private boolean validateMove(Move move){
        Player player = move.getPlayer();
        Cell cell = move.getCell();
        int row = cell.getRow();
        int col = cell.getCol();
        if(row < 0 || row >= board.getDimension() ||
                col < 0 || col >= board.getDimension() ||
                board.getBoard().get(row).get(col).getCellState().equals(CellState.FILLED)){
            return false;
        }
        return true;
    }
    public void makeMove() throws InvalidMoveException {
        Player currentPlayer = players.get(nextPlayerMoveIndex);
        System.out.println("It is "+ currentPlayer.getName() + "'s move");

        //Ask user where they want to make a move
        /* This move is not yet validated, so this is still a dummy move
        * */
        Move move = currentPlayer.makeMove(board);

        //Before finalizing the move, validate first cell is empty or not
        if(!validateMove(move)){
            throw new InvalidMoveException("Invalid move, please try again");
        }
        //If move is valid then execute move on the board
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        //This is the actual move that we are receiving from board
        Cell cell = board.getBoard().get(row).get(col);
        cell.setCellState(CellState.FILLED);
        cell.setPlayer(currentPlayer);
        /* Now we need to add the finalized move in the moves list as we are going to add UNDO
        feature, so the move that we made earlier was just a shadow move to validate, so that doesn't
        mean that earlier move is finalized, so we need to create new move after validations
        * */
        Move finalMove = new Move(currentPlayer, cell);
        moves.add(finalMove);

        nextPlayerMoveIndex += 1;
        nextPlayerMoveIndex %= players.size();

        if(checkWinner(finalMove)){
            winner = currentPlayer;
            gameState = GameState.ENDED;
        }
        else if(moves.size() == board.getDimension() * board.getDimension()){
            gameState = GameState.DRAW;
        }
    }

    private boolean checkWinner(Move move){
        //Check the row, column and diagonal(if applicable)
        for(WinningStrategy winningStrategy : winningStrategies){
            if(winningStrategy.checkWinner(board, move)){
                return true;
            }
        }
        return false;
    }

    //Game class methods
    public List<Player> getPlayers() {
        return players;
    }
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Board getBoard() {
        return board;
    }
    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Move> getMoves() {
        return moves;
    }
    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Player getWinner() {
        return winner;
    }
    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public GameState getGameState() {
        return gameState;
    }
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int nextPlayerMoveIndex() {
        return nextPlayerMoveIndex;
    }
    public void nextPlayerMoveIndex(int nextPlayerMove) {
        this.nextPlayerMoveIndex = nextPlayerMove;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }
    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }
    //GameBuilder returning method
    public static GameBuilder getBuilder(){
        return new GameBuilder();
    }

    public static class GameBuilder{
        /* This class is going to have a builder static class to build validate object before its creation.
        We are not expecting all the parameters from user to be passed while creating new Game, but some required
        attributes that are needed to start the game, so they are List of players, dimensions and
        winningStrategies rest of the things are understood as if we have dimensions, then we can create
        the whole board matrix, while at the start of game, the gameState is going to be IN-PROGRESS, list of
        moves are not expected, board can be built on the basis of dimensions, and winning strategies are
        required as which kind of win is considered per game is decided by the players.
        * */
        private List<Player> players;
        private int dimension;
        private List<WinningStrategy> winningStrategies;

        public GameBuilder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public GameBuilder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public GameBuilder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        // Validations
        private void validateBotCount() throws BotCountExceededException {
            int botCount = 0;
            for(Player player : players){
                if(player.getPlayerType().equals(PlayerType.BOT)){
                    botCount += 1;
                }
            }
            if(botCount > 1){
                throw new BotCountExceededException("At max one bot is allowed");
            }
        }
        private void validatePlayerCount() throws InvalidPlayerCountException {
            if(players.size() != dimension - 1){
                throw new InvalidPlayerCountException("Players count should be "+ (dimension-1));
            }
        }
        public void validateUniquePlayerSymbol() throws DuplicateSymbolFoundException {
            Set<Character> charSet = new HashSet<>();
            int totalPlayers = players.size();
            for(Player player : players){
                charSet.add(player.getSymbol().getAlphabet());
            }
            if(charSet.size() < totalPlayers){
                throw new DuplicateSymbolFoundException("Kindly insert unique symbol");
            }
        }
        private void validate() throws BotCountExceededException, InvalidPlayerCountException, DuplicateSymbolFoundException {
            validatePlayerCount();
            validateBotCount();
            validateUniquePlayerSymbol();
        }
        public Game build() throws BotCountExceededException, InvalidPlayerCountException, DuplicateSymbolFoundException {
            //before building the game we should first validate the game object.
            validate();
            return new Game(players, dimension, winningStrategies);
        }
    }
}
