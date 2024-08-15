package TicTacToe;

public class Design2 {
    public static void main(String[] args){
        /* Check winner algorithm
        code :-
        A player wins the game if it gets the same symbol across row, col or diagonal.

        1) Brute force approach for checking winner
        For every player iterate through all winning strategies like along row, column and diagonal
        so iterating along row will take O(N^2) as we will iterate through whole matrix in both the
        cases for row and column like this
        for(int row = 0; row < N; row++){
            is_winner = true;
            for(int col = col < N; col++){
                if(matrix[row][col] != player.symbol){
                    is_winner = false;
                    break;
                }
            }
        }
        return is_winner;

        for right diagonal
        for(int row = 0; row < N; row++){
            if(matrix[row][row] != player.symbol){
                is_winner = false;
                break;
            }
        }

        for left diagonal
        int col = N-1;
        for(int row = 0; row < N; row++){
            if(matrix[row][col] != player.symbol){
                is_winner = false;
                break;
            }
        }
        and this same algorithm will be applied for columns as well so that will also contribute O(N^2)
        and for diagonal it will take O(N) so overall time complexity would be O(N^2) + O(N^2) + O(2N)
        So for single player it is O(N^2) and for N players it would be N^2 * N = O(N^3).

        2) Optimizing the the algorithm further :-
        What extra work we are doing here ? that we are checking for every player but in actual case,
        the player who will make a move, will be having actual possibility of winning as it creates the
        chances of sequence getting completed.
        So the outer loop that is iterating over all the players is redundant so the overall time complexity
        will be O(N^2) and after each move we need to keep on checking like is there any player that can win.

        3) Final optimization :-
        The further optimization idea is, if a symbol is placed in a cell that comes in the winning sequence
        of a specific row and a column then it's clear that diagonal is not in the picture and if the symbol
        is placed at center cell so row, column and diagonal all will come into picture. So if a symbol is
        placed on (i, j) cell then winning is possible either on ith row or jth column or on a diagonal
        depending upon condition.
        Now if we optimized the overall algorithm, then which parameters will we require ?
        for method checkWinner(Board, Player, Cell) as we will need to fix either row or column or both
        different so first loop for row fixed and varying column

        //for row
        for(int col = 0; col < N; col++){
            if(board[cell.row][col] != player.symbol){
                is_winner = false
            }
        }

        //for col
        for(int row = 0; row < N; row++){
            if(board[row][cell.col] != player.symbol){
                is_winner = false;
                break;
            }
        }

        //for left diagonal, it will be i == j
        for(int row = 0; row < N; row++){
            if(board[row][row] != player.symbol){
                is_winner = false;
                break;
            }
        }

        //for right diagonal, it will be row + col == N-1
        col = N-1
        for(int row = 0; row < N; row++){
            if(board[row][col] != player.symbol){
                is_winner = false;
                break;
            }
            col -= 1;
        }

        so now each of the checking loops are of order of O(N) so overall time complexity is
        O(N).

        Here we don't need to optimize further as even if we iterate on one row, still it takes O(N)
        time complexity that means it is already best and even for the 3 X 3 size matrix, it will only
        cost 27 iterations but,

        4) Final optimization :-
        Can we still optimize ? answer is yes, we can by using some space and after O(N), the best
        is O(1) and this can only be achieved if we use HashMap<>() and if we have information about
        number of symbols across row, col or diagonal then we can decide winner in O(1) time complexity.
        To implement this O(1) checkWinner() algorithm, we need to keep track of symbols across rows,
        columns and diagonals, so we don't need to iterate over matrix.

        Now we have to maintain HashMaps for each row, column and diagonal like this HashMap for Row1,
        HashMap for Row2, HashMap for Row3 and so on for rows and so on for columns so there will be
        2N+1 HashMaps in total. If a symbol is placed on any of the sides then they are not counted in
        corner as well as center cell, so they are not the part of diagonal HashMap, so we can directly
        add it to ith rows HashMap and jth column and for the rest of the combinations there will be
        diagonal and normal HashMap as well. So the normal HashMap will be of type of HashMap<Symbol, Integer>

        This checkWinner() method will be executed after each move.


        * */
    }
}
