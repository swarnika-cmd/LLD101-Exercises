import models.*;
import java.util.List;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Snake snake1 = new Snake(14, 7);
        Snake snake2 = new Snake(31, 26);
        Snake snake3 = new Snake(99, 78);
        List<Snake> snakes = Arrays.asList(snake1, snake2, snake3);

        Ladder ladder1 = new Ladder(3, 22);
        Ladder ladder2 = new Ladder(50, 67);
        Ladder ladder3 = new Ladder(80, 98);
        List<Ladder> ladders = Arrays.asList(ladder1, ladder2, ladder3);

        Board board = new Board(100, snakes, ladders);
        Dice dice = new Dice(6);

        Player p1 = new Player("1", "Alice");
        Player p2 = new Player("2", "Bob");

        Queue<Player> players = new LinkedList<>(Arrays.asList(p1, p2));

        Game game = new Game(board, dice, players);
        System.out.println("Starting Snake and Ladder Game...");
        game.play();
    }
}
