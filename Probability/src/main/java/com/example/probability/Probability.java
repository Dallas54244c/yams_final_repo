package com.example.probability;

public class Probability {
    private String id;
    private String description;
    private double valeur;

    public Probability(String id, String description, double valeur) {
        this.id = id;
        this.description = description;
        this.valeur = valeur;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getValeur() {
        return valeur;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    public double calculerProbabiliteCoup(int[] des, int coup) {
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
        }

        // Calculer la probabilité en divisant le nombre de dés correspondants par le nombre total de dés
        double probabilite = (double) desCorrespondants / desTotals;

        return probabilite;
    }

}


