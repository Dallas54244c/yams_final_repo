package com.example.probability;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProbabilityServiceTest {

    private ProbabilityService probabilityService;

    @BeforeEach
    public void setUp() {
        probabilityService = new ProbabilityService();
    }

    @Test
    public void testCalculateProbability_AllDiceMatch() {
        int coup = 3;
        int[] dice = {3, 3, 3, 3, 3};

        double expectedProbability = 1.0;
        double actualProbability = probabilityService.calculateProbability(coup, dice);

        Assertions.assertEquals(expectedProbability, actualProbability);
    }

    @Test
    public void testCalculateProbability_NoDiceMatch() {
        int coup = 4;
        int[] dice = {1, 2, 3, 5, 6};

        double expectedProbability = 0.0;
        double actualProbability = probabilityService.calculateProbability(coup, dice);

        Assertions.assertEquals(expectedProbability, actualProbability);
    }

    @Test
    public void testCalculateProbability_SomeDiceMatch() {
        int coup = 2;
        int[] dice = {2, 4, 2, 5, 1};

        double expectedProbability = 0.4;
        double actualProbability = probabilityService.calculateProbability(coup, dice);

        Assertions.assertEquals(expectedProbability, actualProbability);
    }

    @Test
    public void testCalculateProbability_FullHouse() {
        int coup = 5;
        int[] dice = {5, 5, 5, 2, 2};

        double expectedProbability = 0.6;
        double actualProbability = probabilityService.calculateProbability(coup, dice);

        Assertions.assertEquals(expectedProbability, actualProbability);
    }


}


