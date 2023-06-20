package com.example.probability;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProbabilityServiceIntegrationTest {

    @Test
    public void testCalculateProbability() {
        // Créer une instance du service à tester
        ProbabilityService probabilityService = new ProbabilityService();

        // Définir les données de test
        int coup = 5;
        int[] des = {5, 5, 5, 2, 4};

        // Appeler la méthode à tester
        double probability = probabilityService.calculateProbability(coup, des);

        // Vérifier le résultat
        Assertions.assertEquals(0.6, probability);
    }
}


