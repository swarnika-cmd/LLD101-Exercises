package models;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Board {
    private int size;
    private Map<Integer, Snake> snakes;
    private Map<Integer, Ladder> ladders;

    public Board(int size, List<Snake> snakeList, List<Ladder> ladderList) {
        this.size = size;
        this.snakes = new HashMap<>();
        this.ladders = new HashMap<>();

        for (Snake snake : snakeList) {
            snakes.put(snake.getHead(), snake);
        }
        for (Ladder ladder : ladderList) {
            ladders.put(ladder.getBottom(), ladder);
        }
    }

    public int getSize() {
        return size;
    }

    public int getNextPosition(int currentPosition) {
        if (snakes.containsKey(currentPosition)) {
            System.out.println("Bitten by a snake!");
            return snakes.get(currentPosition).getTail();
        }
        if (ladders.containsKey(currentPosition)) {
            System.out.println("Climbed a ladder!");
            return ladders.get(currentPosition).getTop();
        }
        return currentPosition;
    }
}
