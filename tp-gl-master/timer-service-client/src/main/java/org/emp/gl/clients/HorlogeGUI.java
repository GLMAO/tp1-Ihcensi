package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerService;
import org.emp.gl.timer.service.TimerChangeListener;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;

public class HorlogeGUI extends JFrame implements TimerChangeListener {
    private JLabel labelHeure;
    private TimerService timerService;

    public HorlogeGUI(TimerService timerService) {
        this.timerService = timerService;
        this.timerService.addTimeChangeListener(this);
        setupWindow();
        updateHeure();
        System.out.println("✨ Horloge Mignonne démarrée !");
    }

    private void setupWindow() {
        setTitle("💖 Ma Belle Horloge 💖");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        
        Color rosePastel = new Color(255, 182, 193);    // Rose pastel
        Color violetDouce = new Color(216, 191, 216);   // Violet doux
        Color blancCreme = new Color(255, 253, 250);    // Blanc crème
        Color corailMignon = new Color(240, 128, 128);  // Corail doux
        
       
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(
                    0, 0, rosePastel, 
                    getWidth(), getHeight(), violetDouce
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout());
        
        
        labelHeure = new JLabel("00:00:00", JLabel.CENTER);
        labelHeure.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
        labelHeure.setForeground(new Color(75, 0, 130)); // Violet foncé mignon
        labelHeure.setOpaque(false);
        labelHeure.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 105, 180), 3, true),
            BorderFactory.createEmptyBorder(25, 30, 25, 30)
        ));
        
        
        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.add(new JLabel("🌸 ⏰ 🌸"));
        topPanel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.add(new JLabel("💕 Belle Journée ! 💕"));
        bottomPanel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
        
       
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(labelHeure, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        setContentPane(mainPanel);
        pack();
        setSize(500, 300);
        setLocationRelativeTo(null);
        
        // Curseur mignon (si disponible)
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        } catch (Exception e) {
            // Curseur par défaut si problème
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (TimerChangeListener.SECONDE_PROP.equals(evt.getPropertyName())) {
            updateHeure();
        }
    }

    private void updateHeure() {
        SwingUtilities.invokeLater(() -> {
            String heure = String.format("%02d : %02d : %02d",
                    timerService.getHeures(),
                    timerService.getMinutes(),
                    timerService.getSecondes());
            labelHeure.setText(heure);
            
            // Changement de couleur doux selon les secondes (optionnel)
            if (timerService.getSecondes() % 2 == 0) {
                labelHeure.setForeground(new Color(75, 0, 130)); // Violet
            } else {
                labelHeure.setForeground(new Color(199, 21, 133)); // Rose vif
            }
        });
    }
}