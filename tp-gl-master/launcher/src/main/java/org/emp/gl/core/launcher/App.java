package org.emp.gl.core.launcher;

import org.emp.gl.clients.Horloge;
import org.emp.gl.clients.CompteARebours;
import org.emp.gl.clients.HorlogeGUI;
import org.emp.gl.timer.service.TimerService;
import org.emp.gl.time.service.impl.DummyTimeServiceImpl;

import javax.swing.*;

public class App {

    public static void main(String[] args) {
        // Lancer l'interface graphique dans le thread EDT
        SwingUtilities.invokeLater(() -> {
            testDuTimeService();
        });
    }

    private static void testDuTimeService() {
        TimerService timerService = new DummyTimeServiceImpl();
        
        // Partie (c) - Horloges console
        Horloge horloge1 = new Horloge("Horloge Console 1", timerService);
        Horloge horloge2 = new Horloge("Horloge Console 2", timerService);
        
        // Partie (d) - Compte à rebours
        CompteARebours compte1 = new CompteARebours("Compte-5", 5, timerService);
        
        // Bonus (f) - Horloge Graphique
        HorlogeGUI horlogeGUI = new HorlogeGUI(timerService);
        horlogeGUI.setVisible(true);
        
        System.out.println("=== APPLICATION DÉMARRÉE ===");
        System.out.println("- 2 horloges console");
        System.out.println("- 1 compte à rebours (5 secondes)");
        System.out.println("- 1 horloge graphique");
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}