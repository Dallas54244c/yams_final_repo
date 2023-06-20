package com.example.appariement;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Player {
    private Map<Integer, Integer> categories = new HashMap<>();

    public void setLevel(int level) {
        this.level = level;
    }

    private int level;

    private static final int CATEGORY_BRELAN = 1;
    private int brelanScore;
    private int carreScore;
    private int fullScore;
    private int petiteSuiteScore;
    private int grandeSuiteScore;
    private int yamsScore;
    private int chanceScore;

    private static final int CATEGORY_CARRE = 2;
    private static final int CATEGORY_FULL =3 ;
    private static final int CATEGORY_PETITE_SUITE = 4;
    private static final int CATEGORY_GRANDE_SUITE = 5;
    private static final int CATEGORY_YAMS = 6;
    private static final int CATEGORY_CHANCE = 7;
    private long id;
    private String name;
    private int[] dice;
    private int score;
    private int remainingRolls;
    private int totalDiceRolls;
    private boolean gameStarted;
    private int chosenCategory;

    // Constructor, getters, and setters...

    public void setChosenCategory(int category) {
        this.chosenCategory = category;
    }

    public int getChosenCategory() {
        return chosenCategory;
    }


    public Player() {
    }

    public Player(long id, String name) {
        this.id = id;
        this.name = name;
        this.level=0;
        this.score=0;
    }

    // Getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getDice() {
        return dice;
    }

    public void setDice(int[] dice) {
        this.dice = dice;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getRemainingRolls() {
        return remainingRolls;
    }

    public void setRemainingRolls(int remainingRolls) {
        this.remainingRolls = remainingRolls;
    }

    public int getTotalDiceRolls() {
        return totalDiceRolls;
    }

    public void setTotalDiceRolls(int totalDiceRolls) {
        this.totalDiceRolls = totalDiceRolls;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    // Additional methods

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dice=" + Arrays.toString(dice) +
                ", score=" + score +
                ", remainingRolls=" + remainingRolls +
                ", totalDiceRolls=" + totalDiceRolls +
                ", gameStarted=" + gameStarted +
                '}';
    }


    public Player(long id) {
        this.id = id;
    }

    public int[] rollDice() {
        // Generate random values for the dice
        dice = new int[5];
        for (int i = 0; i < 5; i++) {
            dice[i] = (int) (Math.random() * 6) + 1;
        }
        totalDiceRolls++;
        remainingRolls--;
        return dice;
    }
    public void resetDice() {
        dice = new int[5];
        remainingRolls = 3;
    }



    public boolean hasBonus() {
        return score >= 63;
    }
    public boolean hasNoMoreRolls() {
        return remainingRolls <= 0;
    }

    public void rerollDice(int[] diceToReroll) {
        Random random = new Random();
        for (int i = 0; i < diceToReroll.length; i++) {
            if (diceToReroll[i] == 1) {
                dice[i] = random.nextInt(6) + 1;
            }
        }
    }


    private boolean contains(int[] arr, int val) {
        for (int i : arr) {
            if (i == val) {
                return true;
            }
        }
        return false;
    }

    public int getUpperSectionScore() {
        int upperSectionScore = 0;
        for (int i = 1; i <= 6; i++) {
            if (categories.containsKey(i)) {
                upperSectionScore += categories.get(i);
            }
        }
        return upperSectionScore;
    }

    public int getLevel() {
        return level;
    }

    public int calculateScore(int chosenCategory, int[] dice) {
        int score = 0;

        switch (chosenCategory) {
            case CATEGORY_BRELAN:
                score = calculateBrelanScore(dice);
                break;
            case CATEGORY_CARRE:
                score = calculateCarreScore(dice);
                break;
            case CATEGORY_FULL:
                score = calculateFullScore(dice);
                break;
            case CATEGORY_PETITE_SUITE:
                score = calculatePetiteSuiteScore(dice);
                break;
            case CATEGORY_GRANDE_SUITE:
                score = calculateGrandeSuiteScore(dice);
                break;
            case CATEGORY_YAMS:
                this.score = calculateYamsScore(dice);
                break;
            case CATEGORY_CHANCE:
                score = calculateChanceScore(dice);
                break;
            default:
                // Handle the default case if no category matches
                break;
        }

        return score;
    }

    public int calculateBrelanScore(int[] dice) {
        int score = 0;
        int[] counts = new int[6];

        // Count the occurrences of each dice value
        for (int value : dice) {
            counts[value - 1]++;
        }

        // Check if there is a brelan (three dice with the same value)
        for (int i = 0; i < 6; i++) {
            if (counts[i] >= 3) {
                score = (i + 1) * 3;
                break;
            }
        }

        return score;
    }


    public int calculateCarreScore(int[] dice) {
        int score = 0;
        int[] counts = new int[6];

        // Count the occurrences of each dice value
        for (int value : dice) {
            counts[value - 1]++;
        }

        // Check if there is a carré (four dice with the same value)
        for (int i = 0; i < 6; i++) {
            if (counts[i] >= 4) {
                score = (i + 1) * 4;
                break;
            }
        }

        return score;
    }


    public int calculateFullScore(int[] dice) {
        int score = 0;
        int[] counts = new int[6];

        // Count the occurrences of each dice value
        for (int value : dice) {
            counts[value - 1]++;
        }

        boolean hasBrelan = false;
        boolean hasPair = false;

        // Check if there is a brelan (three dice with the same value)
        for (int i = 0; i < 6; i++) {
            if (counts[i] >= 3) {
                hasBrelan = true;
                break;
            }
        }

        // Check if there is a pair (two dice with the same value)
        for (int i = 0; i < 6; i++) {
            if (counts[i] >= 2) {
                hasPair = true;
                break;
            }
        }

        // Calculate the score based on the full combination
        if (hasBrelan && hasPair) {
            score = 25;
        }

        return score;
    }


    public int calculatePetiteSuiteScore(int[] dice) {
        int score = 0;

        // Sort the dice values in ascending order
        Arrays.sort(dice);

        // Check for a valid petite suite (4 consecutive dice values)
        if ((dice[1] == dice[0] + 1) && (dice[2] == dice[1] + 1) && (dice[3] == dice[2] + 1)) {
            score = 30;
        }

        return score;
    }


    public int calculateGrandeSuiteScore(int[] dice) {
        int score = 0;

        // Sort the dice values in ascending order
        Arrays.sort(dice);

        // Check for a valid grande suite (5 consecutive dice values)
        if ((dice[1] == dice[0] + 1) && (dice[2] == dice[1] + 1) && (dice[3] == dice[2] + 1) && (dice[4] == dice[3] + 1)) {
            score = 40;
        }

        return score;
    }


    public int calculateYamsScore(int[] dice) {
        int score = 0;

        // Check if all dice values are the same
        if (dice[0] == dice[1] && dice[1] == dice[2] && dice[2] == dice[3] && dice[3] == dice[4]) {
            score = 50;
        }

        return score;
    }


    public int calculateChanceScore(int[] dice) {
        int score = 0;

        // Sum all the dice values
        for (int value : dice) {
            score += value;
        }

        return score;
    }
    public void setscore(int chosenCategory, int score) {
        // Assign the score to the chosen category
        switch (chosenCategory) {
            case CATEGORY_BRELAN:
                this.brelanScore += score;
                break;
            case CATEGORY_CARRE:
                this.carreScore += score;
                break;
            case CATEGORY_FULL:
                this.fullScore += score;
                break;
            case CATEGORY_PETITE_SUITE:
                this.petiteSuiteScore += score;
                break;
            case CATEGORY_GRANDE_SUITE:
                this.grandeSuiteScore += score;
                break;
            case CATEGORY_YAMS:
                this.yamsScore += score;
                break;
            case CATEGORY_CHANCE:
                this.chanceScore += score;
                break;
            default:
                // Invalid category, do nothing
                break;
        }
    }

    public int chooseCategory(int[] dice) {
        int chosenCategory = -1;

        // Evaluate the dice for different categories and choose the highest scoring category
        int maxScore = 0;

        // Check for Brelan
        int brelanScore = calculateBrelanScore(dice);
        if (brelanScore > maxScore) {
            maxScore = brelanScore;
            chosenCategory = CATEGORY_BRELAN;
        }

        // Check for Carré
        int carreScore = calculateCarreScore(dice);
        if (carreScore > maxScore) {
            maxScore = carreScore;
            chosenCategory = CATEGORY_CARRE;
        }

        // Check for Full
        int fullScore = calculateFullScore(dice);
        if (fullScore > maxScore) {
            maxScore = fullScore;
            chosenCategory = CATEGORY_FULL;
        }

        // Check for Petite Suite
        int petiteSuiteScore = calculatePetiteSuiteScore(dice);
        if (petiteSuiteScore > maxScore) {
            maxScore = petiteSuiteScore;
            chosenCategory = CATEGORY_PETITE_SUITE;
        }

        // Check for Grande Suite
        int grandeSuiteScore = calculateGrandeSuiteScore(dice);
        if (grandeSuiteScore > maxScore) {
            maxScore = grandeSuiteScore;
            chosenCategory = CATEGORY_GRANDE_SUITE;
        }

        // Check for Yams
        int yamsScore = calculateYamsScore(dice);
        if (yamsScore > maxScore) {
            maxScore = yamsScore;
            chosenCategory = CATEGORY_YAMS;
        }

        // Check for Chance
        int chanceScore = calculateChanceScore(dice);
        if (chanceScore > maxScore) {
            maxScore = chanceScore;
            chosenCategory = CATEGORY_CHANCE;
        }

        return chosenCategory;
    }

    public int getTotalScore() {
        int totalScore = 0;
        // Calculate the total score by summing up all the category scores
        totalScore += this.brelanScore;
        totalScore += this.carreScore;
        totalScore += this.fullScore;
        totalScore += this.petiteSuiteScore;
        totalScore += this.grandeSuiteScore;
        totalScore += this.yamsScore;
        totalScore += this.chanceScore;

        // Add the upper section score bonus if applicable
        if (getUpperSectionScore() >= 63) {
            totalScore += 35;
        }

        return totalScore;
    }


}

