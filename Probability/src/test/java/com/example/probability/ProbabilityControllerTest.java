package com.example.probability;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ProbabilityControllerTest {
    private ProbabilityService probabilityService;
    private ProbabilityController probabilityController;

    @BeforeEach
    public void setUp() {
        probabilityService = Mockito.mock(ProbabilityService.class);
        probabilityController = new ProbabilityController(probabilityService);
    }

    @Test
    public void testCalculateProbability() {
        int coup = 3;
        int[] des = {1, 2, 3, 4, 5};
        double expectedProbability = 0.1667;
        Mockito.when(probabilityService.calculateProbability(coup, des)).thenReturn(expectedProbability);

        double result = probabilityController.calculateProbability(coup, des);

        Assertions.assertEquals(expectedProbability, result);
    }
}


