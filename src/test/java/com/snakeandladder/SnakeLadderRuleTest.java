package com.snakeandladder;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SnakeLadderRuleTest {

    @Test
    public void snakeAndLadderMovePlayer() {
        Board b = new Board(5);
        Snake snake = new Snake("S1", 14, 4);
        Ladder ladder = new Ladder("L1", 3, 12);
        b.setEntities(java.util.Arrays.asList(snake, ladder));

        Player p = new HumanPlayer("Alice", "Blue");
        Map<Player, Integer> positions = new HashMap<>();
        positions.put(p, 1);

        SnakeLadderRule rule = new SnakeLadderRule();

        // Roll to land on ladder start (from 1 to 3 => roll 2)
        RuleResult res1 = rule.apply(p, 2, b, positions);
        assertEquals(12, res1.getNewPosition());

        // move player to 13 then roll 1 to land on snake at 14
        positions.put(p, 13);
        RuleResult res2 = rule.apply(p, 1, b, positions);
        assertEquals(4, res2.getNewPosition());
    }
}
