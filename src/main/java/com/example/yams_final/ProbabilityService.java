package com.example.yams_final;


import org.springframework.stereotype.Service;

@Service
public class ProbabilityService {

    public double calculateProbability(int coup, int[] des) {
        int desCorrespondants = 0;
        int desTotals = des.length;

        // Compter le nombre de dés qui correspondent au coup choisi
        for (int valeurDes : des) {
            if (valeurDes == coup) {
                desCorrespondants++;
            }
        }

        // Cas particuliers pour certaines combinaisons du Yams
        if (coup == 5 && desCorrespondants >= 4) {
            // Si le coup est un brelan de 5 ou plus, la probabilité est toujours de 100%
            return 1.0;
        } else if (coup == 6 && desCorrespondants >= 5) {
            // Si le coup est un carré de 6, la probabilité est toujours de 100%
            return 1.0;
        } else if (coup == 7 && desCorrespondants >= 5) {
            // Si le coup est une quinte de 6, la probabilité est toujours de 100%
            return 1.0;
        } else if (desCorrespondants == 0) {
            // Si aucun dé ne correspond au coup choisi, la probabilité est de 0%
            return 0.0;
        } else if (desCorrespondants == desTotals) {
            // Si tous les dés correspondent au coup choisi, la probabilité est de 100%
            return 1.0;
        }

        // Calculer la probabilité en divisant le nombre de dés correspondants par le nombre total de dés
        double probabilite = (double) desCorrespondants / desTotals;

        return probabilite;
    }

}

