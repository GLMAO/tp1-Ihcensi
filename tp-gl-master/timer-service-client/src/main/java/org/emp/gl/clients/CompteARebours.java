package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerService;
import org.emp.gl.timer.service.TimerChangeListener;
import java.beans.PropertyChangeEvent;

public class CompteARebours implements TimerChangeListener {
    private int compteur;
    private TimerService timerService;
    private String nom;

    public CompteARebours(String nom, int initial, TimerService timerService) {
        this.nom = nom;
        this.compteur = initial;
        this.timerService = timerService;
        this.timerService.addTimeChangeListener(this);
        System.out.println(nom + " démarré avec " + compteur + " secondes");
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (TimerChangeListener.SECONDE_PROP.equals(evt.getPropertyName())) {
            if (compteur > 0) {
                System.out.println(nom + " : " + compteur);
                compteur--;
            } else if (compteur == 0) {
                System.out.println(nom + " : TERMINÉ !");
                timerService.removeTimeChangeListener(this);
            }
        }
    }
}