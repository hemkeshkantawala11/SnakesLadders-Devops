package com.snakeandladder;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiceTest {

    @Test
    public void rollWithinRange_singleDie() {
        Dice d = new Dice(1);
        int r = d.roll();
        assertTrue(r >= 1 && r <= 6, "roll must be between 1 and 6");
    }

    @RepeatedTest(20)
    public void rollWithinRange_multipleRuns() {
        Dice d = new Dice(2);
        int r = d.roll();
        assertTrue(r >= 2 && r <= 12, "roll must be between 2 and 12 for 2 dice");
    }
}
