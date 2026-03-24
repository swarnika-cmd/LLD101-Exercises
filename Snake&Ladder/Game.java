import models.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;

public class Game {
    private Board board;
    private Dice dice;
    private Queue<Player> players;
    private Map<String, Integer> playerPositions;

    public Game(Board board, Dice dice, Queue<Player> players) {
        this.board = board;
        this.dice = dice;
        this.players = new LinkedList<>(players);
        this.playerPositions = new HashMap<>();
        
        for (Player player : players) {
            playerPositions.put(player.getId(), 0);
        }
    }

    public void play() {
        while (true) {
            Player currentPlayer = players.poll();
            int currentPosition = playerPositions.get(currentPlayer.getId());
            int roll = dice.roll();

            int nextPosition = currentPosition + roll;

            if (nextPosition > board.getSize()) {
                System.out.println(currentPlayer.getName() + " rolled a " + roll + " but needs exactly " + (board.getSize() - currentPosition) + " to win. Turn skipped.");
                players.offer(currentPlayer);
                continue;
            }

            System.out.print(currentPlayer.getName() + " rolled a " + roll + " and moved from " + currentPosition + " to " + nextPosition + ". ");
            nextPosition = board.getNextPosition(nextPosition);
            
            playerPositions.put(currentPlayer.getId(), nextPosition);

            if (nextPosition == board.getSize()) {
                System.out.println("\n" + currentPlayer.getName() + " wins the game!");
                break;
            }

            players.offer(currentPlayer);
        }
    }
}
