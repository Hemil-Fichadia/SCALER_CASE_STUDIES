package TicTacToe;

public class Design {
    public static void main(String[] args){
        /* We will design the TicTacToe game from scratch
        Step - 1 :
        Before jumping to any of the conclusion or idea about the task, first take full overview of the
        system and either you know the system or you don't, if you know the system then just confirm it
        once with the interviewer like is this the same game where we draw a 3 X 3 matrix and there are
        two players both have different symbol and whom so ever gets the pattern in either diagonal or
        column or row is considered as winner, and in case it you are not aware of the system then do
        ask it to interviewer.

        Step - 2 :
        Ask some clarification question like are we building a simple entity design or an end to end
        implementation of the game, then do we need to persist data or not means does the data needs to be
        stored anywhere ? Here we don't need.

        Step - 3 :
        In requirement gathering, we just need to suggest important 5 to 7 features for the system and
        clarify all the edge/corner cases, and when we gather requirements, gather it in a very general
        manner, don't go too specific for it, and if we think for TicTacToe, more than 3 X 3 doesn't seem
        correct but if we think in terms of generalization, then there can be 4 X 4 or N X N board so
        in this way we are making our system extensible as we are not restricting to 3 X 3 board.

        1) We will design the game for N x N as we can put n value as 3 and play as normal TicTacToe.
        In 3 x 3 board, 2 players can play, so on that assumption n-1 players can play the game.

        2) Each player can choose their own symbol at start of the game and once a symbol is allocated to
        a player, it cannot be taken by any other player.

        3) If any player wants to play alone then they need to have a bot player.

        4) How many bots are allowed per game ? only 1 here

        5) Bots can have multiple difficulty levels

        6) Who will make the first move

        7) How to decide the winner : If there is a sequence of n continuous similar symbol across any of
        the row, column or diagonal, then the first player to achieve it is considered as winner.

        8) Game ends as soon as a winner is decided or no place to make a move

        9) Undo feature : Any player can push this button any number of time until there exist a previous
        move.
        -----------------------------------------------------------------------------------------------
        #*) How to implement UNDO feature
        We need to keep a track of moves that players made at what location, and this Move will be an
        entity which will store player and location.

        1) First approach for undoing last move :-
        Store it like player1 made a move on (0, 0), player2 made a move on (1, 2), player1 made a move
        on (1, 1) and to undo it remove last move from the list and make that cell empty, now here in
        TicTacToe it is quite easy to undo a move but in a game like chess, it's quite difficult to keep
        track of the moves as it is and if undone, then it also have impact on move of other player as well.

        2) Second approach for undoing the last move :-
        Storing all the moves did the half job but removing the last move still takes some more efforts
        to undo in rest of the games, so in the path for getting the optimal solution to undo using List<Moves>,
        we can also do one thing using the same list, if we are undoing one step then we are simply considering
        n-1 steps, so just iterate and do all actions till n-1 steps. So instead of removing nth move,
        execute first n-1 moves from scratch.

        3) Third approach for undoing the last move :-
        Earlier we were executing n-1 moves to undo one move from board and that still cost us O(N) time
        complexity and this is not even bothering us in case of a game, but it is not an optimal
        solution to be considered in case of games with millions of moves, so it will exponentially boost
        the time complexity.
        The better approach is to store the state of board after each move, so in this way are literally
        storing the board state after each move, this helps us to keep the track and that also helps us
        to get the move undone to the second last move.
        Here we will store boards itself after each move, we will use List<Board> where at each index whole
        board with game progress will be stored.

        Pros :-
        1) Easy to implement
        2) Good Time complexity

        Cons :-
        1) It uses space complexity of O(N),
        but we are not going to store 10 million or 20 million moves, we will store around 50 to 100 moves
        and this doesn't really impact the system overall.

        To create class diagram you need to figure out all the
        1) Nouns :- All the model classes
        2) Visualization :- How user will interact with system, what will be the first interaction like
        if it is BookMyShow platform then user will book a ticket here, so user first needs to select the
        city then select the movie and then select the theater then select the seat accordingly, and second
        thing is physical structure like what is each component going to perform and how it will be done.

        If we visualize TicTacToe, then a board, symbol, players, each cell where a move can be made so
        these all are part of game so game is outer class which will contain all these, so game class
        will encapsulate all these

        --------------------------------------CLASS DIAGRAM-----------------------------------------

        class Game {                            class Board{                class Cell{
            Board board;                           dimension                    row
            List<Player> players;                  List<List<Cell>>             col
            WinnerPlayer winner;                }                               ENUM cellState
            GameState state;                                                    Player player
            nextPlayerMoveIndex                                             }
        }

        class Player {      ------Extends----->     class BOT{                   class Symbol {
            name                                        DifficultyLevel             char alphabet
            Symbol                                  }                               image
            id                                                                  }
            PlayerType
        }

        class Move {
            Player player;
            Cell cell;
        }

        ENUM GameState {           ENUM CellState {        ENUM PlayerType {        ENUM DifficultyLevel {
            IN-PROGRESS,                EMPTY,                  HUMAN,                  EASY,
            ENDED,                      FILLED                  BOT                     MEDIUM,
            DRAW                    }                       }                           HARD
        }                                                                           }

        Design patterns to be used :-
        1) Builder design pattern to validate the player before creating its object
        2) Factory design pattern to create player object based on input parameter from user.
        3) Strategy design pattern Bot playing strategy as it can be easy, medium and hard so multiple
        ways to achieve something.
        4) Strategy design pattern Winner can be decided based on row column or diagonal.



        */
    }
}
