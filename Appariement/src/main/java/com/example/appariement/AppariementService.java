package com.example.appariement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ComponentScan(basePackages = "com.example.probability")
public class AppariementService {

    private final ProbabilityService probabilityService;
    @Autowired
    public AppariementService(ProbabilityService probabilityService) {
        this.probabilityService = probabilityService;
    }

    public HostingServer findAvailableHostingServer(List<HostingServer> hostingServers) {
        for (HostingServer server : hostingServers) {
            if (server.isAvailable()) {
                return server;
            }
        }
        return null;
    }

    public String appariementJoueurProbabilite(Player player, int coup, int[] dice) {
        double probability = probabilityService.calculateProbability(coup, dice);


        if (probability >= 0.5) {
            // Le joueur est apparié avec la probabilité
            System.out.println("Le joueur " + player.getName() + " est apparié avec la probabilité " + probability+" pour le coup: "+coup);
            return null;
        } else {
            // La probabilité est trop faible, aucun appariement n'est effectué
            System.out.println("La probabilité est trop faible pour le joueur " + player.getName());
        } return null;}

    public void appariementJoueurHebergeur( Player player, List<HostingServer> hostingServers) {

        boolean joueurApparie = false;

        for (HostingServer hostingServer : hostingServers) {
            if (hostingServer.isAvailable()) {
                // L'hébergeur est disponible, le joueur peut être apparié
                if (hostingServer.addPlayer(player)&& ! hostingServer.isPlayerParticipating(player))
                    System.out.println("Le joueur " + player.getName() + " est apparié avec l'hébergeur " + hostingServer.getServerId());

                // Mettre à jour l'état de disponibilité de l'hébergeur
                hostingServer.setAvailable(false);

                joueurApparie = true;
                break; // Sortir de la boucle après avoir trouvé un hébergeur disponible
            }
        }

        if (!joueurApparie) {
            // Aucun hébergeur disponible, aucun appariement n'est effectué
            System.out.println("Aucun hébergeur disponible pour le joueur " + player.getName());
        }
    }

}


