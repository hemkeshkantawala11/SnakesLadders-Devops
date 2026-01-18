package com.snakeandladder;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class KillRuleTest {

    @Test
    public void landingOnOtherPlayer_sendsThemToStart() {
        Player a = new HumanPlayer("A", "Red");
        Player b = new HumanPlayer("B", "Blue");

        Map<Player, Integer> positions = new HashMap<>();
        positions.put(a, 1);
        positions.put(b, 4);

        KillRule kill = new KillRule();

        // A rolls 3 and lands on B's cell (4)
        RuleResult res = kill.apply(a, 3, new Board(5), positions);
        assertEquals(4, res.getNewPosition());
        // B should be sent to 0
        assertEquals(0, positions.get(b).intValue());
    }
}
