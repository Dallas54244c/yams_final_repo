package com.example.probability;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProbabilityTest {

    private Probability probability;

    @BeforeEach
    public void setUp() {
        probability = new Probability("1", "Test Probability", 0.5);
    }

    @Test
    public void testGetters() {
        Assertions.assertEquals("1", probability.getId());
        Assertions.assertEquals("Test Probability", probability.getDescription());
        Assertions.assertEquals(0.5, probability.getValeur());
    }

    @Test
    public void testSetters() {
        probability.setId("2");
        probability.setDescription("Updated Probability");
        probability.setValeur(0.8);

        Assertions.assertEquals("2", probability.getId());
        Assertions.assertEquals("Updated Probability", probability.getDescription());
        Assertions.assertEquals(0.8, probability.getValeur());
    }

    @Test
    public void testCalculerProbabiliteCoup() {
        int[] des = {1, 2, 3, 4, 5};
        double probabilite = probability.calculerProbabiliteCoup(des, 3);

        // Verify that the probability is calculated correctly
        Assertions.assertEquals(0.2, probabilite);

        des = new int[]{3, 3, 3, 4, 5};
        probabilite = probability.calculerProbabiliteCoup(des, 3);

        // Verify that the probability is calculated correctly
        Assertions.assertEquals(0.6, probabilite);

        des = new int[]{6, 6, 6, 6, 6};
        probabilite = probability.calculerProbabiliteCoup(des, 6);

        // Verify that the probability is always 1.0 for a carre of 6
        Assertions.assertEquals(1.0, probabilite);
    }


}


