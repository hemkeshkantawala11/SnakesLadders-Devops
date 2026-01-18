package com.snakeandladder;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ExtraTurnRuleTest {

    @Test
    public void allSixes_grantsExtraTurn() {
        Player p = new HumanPlayer("X", "Y");
        Map<Player, Integer> positions = new HashMap<>();
        positions.put(p, 0);

        ExtraTurnRule rule = new ExtraTurnRule(2); // two dice

        // roll equals 12 (2*6)
        RuleResult res = rule.apply(p, 12, new Board(5), positions);
        assertTrue(res.hasExtraTurn());
        assertEquals(12, res.getNewPosition());
    }

    @Test
    public void nonAllSixes_noExtraTurn() {
        Player p = new HumanPlayer("X", "Y");
        Map<Player, Integer> positions = new HashMap<>();
        positions.put(p, 0);

        ExtraTurnRule rule = new ExtraTurnRule(1);
        RuleResult res = rule.apply(p, 5, new Board(5), positions);
        assertFalse(res.hasExtraTurn());
        assertEquals(5, res.getNewPosition());
    }
}
