package com.snakeandladder;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class RuleEngineTest {

    @Test
    public void applyRules_combinesExtraTurnAndMovement() {
        Player p = new HumanPlayer("Z", "G");
        Map<Player, Integer> positions = new HashMap<>();
        positions.put(p, 0);

        RuleEngine engine = new RuleEngine();
        engine.addRule(new ExtraTurnRule(1)); // grants extra turn on roll==6

        RuleResult res = engine.applyRules(p, 6, null, positions);
        assertTrue(res.hasExtraTurn(), "ExtraTurnRule should grant extra turn on all sixes");
        // movement should be 0 + 6 = 6
        assertEquals(6, res.getNewPosition());
    }
}
