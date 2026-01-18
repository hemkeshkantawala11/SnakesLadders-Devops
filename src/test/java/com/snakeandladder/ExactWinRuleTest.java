package com.snakeandladder;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ExactWinRuleTest {

    @Test
    public void overshoot_preventsMovement() {
        Player p = new HumanPlayer("P", "C");
        Map<Player, Integer> positions = new HashMap<>();
        positions.put(p, 48);

        ExactWinRule rule = new ExactWinRule(50);
        // roll 3 overshoots final cell 50
        RuleResult res = rule.apply(p, 3, new Board(5), positions);
        assertEquals(48, res.getNewPosition());
    }
}
