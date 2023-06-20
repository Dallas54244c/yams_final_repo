package com.example.player;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PlayerTest {

    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Player(1, "John");
    }

    @Test
    public void testRollDice() {
        int[] dice = player.rollDice();

        // Verify that the dice array has 5 elements
        Assertions.assertEquals(5, dice.length);

        // Verify that each element in the dice array is between 1 and 6
        for (int value : dice) {
            Assertions.assertTrue(value >= 1 && value <= 6);
        }

        // Verify that the totalDiceRolls and remainingRolls variables are updated correctly
        Assertions.assertEquals(1, player.getTotalDiceRolls());
        Assertions.assertEquals(-1, player.getRemainingRolls());
    }

    @Test
    public void testResetDice() {
        player.resetDice();

        // Verify that the dice array is reset to all zeros
        int[] dice = player.getDice();
        for (int value : dice) {
            Assertions.assertEquals(0, value);
        }

        // Verify that the remainingRolls variable is reset to 3
        Assertions.assertEquals(3, player.getRemainingRolls());
    }

    @Test
    public void testCalculateBrelanScore() {
        int[] dice = {1, 2, 3, 3, 3};
        int score = player.calculateBrelanScore(dice);

        // Verify that the brelan score is calculated correctly
        Assertions.assertEquals(9, score);
    }

    @Test
    public void testCalculateCarreScore() {
        int[] dice = {2, 4, 4, 4, 4};
        int score = player.calculateCarreScore(dice);

        // Verify that the carre score is calculated correctly
        Assertions.assertEquals(16, score);
    }

    @Test
    public void testCalculateFullScore() {
        int[] dice = {2, 2, 3, 3, 3};
        int score = player.calculateFullScore(dice);

        // Verify that the full score is calculated correctly
        Assertions.assertEquals(25, score);
    }

    @Test
    public void testCalculatePetiteSuiteScore() {
        int[] dice = {1, 2, 3, 4, 4};
        int score = player.calculatePetiteSuiteScore(dice);

        // Verify that the petite suite score is calculated correctly
        Assertions.assertEquals(30, score);

        dice = new int[]{1, 2, 3, 4, 5};
        score = player.calculatePetiteSuiteScore(dice);

        // Verify that the petite suite score is calculated correctly
        Assertions.assertEquals(30, score);
    }

    @Test
    public void testCalculateGrandeSuiteScore() {
        int[] dice = {2, 3, 4, 5, 6};
        int score = player.calculateGrandeSuiteScore(dice);

        // Verify that the grande suite score is calculated correctly
        Assertions.assertEquals(40, score);

        dice = new int[]{1, 2, 3, 4, 4};
        score = player.calculateGrandeSuiteScore(dice);

        // Verify that the grande suite score is calculated correctly
        Assertions.assertEquals(0, score);
    }

    @Test
    public void testCalculateYamsScore() {
        int[] dice = {4, 4, 4, 4, 4};
        int score = player.calculateYamsScore(dice);

        // Verify that the yams score is calculated correctly
        Assertions.assertEquals(50, score);

        dice = new int[]{1, 2, 3, 4, 4};
        score = player.calculateYamsScore(dice);

        // Verify that the yams score is calculated correctly
        Assertions.assertEquals(0, score);
    }

    @Test
    public void testCalculateChanceScore() {
        int[] dice = {2, 3, 4, 5, 6};
        int score = player.calculateChanceScore(dice);

        // Verify that the chance score is calculated correctly
        Assertions.assertEquals(20, score);
    }

    @Test
    public void testCalculateScorecardTotal() {
        player.setscore(1,15);
        player.setscore(2, 20);
        player.setscore(3, 25);

        int totalScore = player.getTotalScore();

        // Verify that the total score is calculated correctly
        Assertions.assertEquals(60, totalScore);
    }


}


