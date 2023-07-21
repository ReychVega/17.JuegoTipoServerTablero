package Demos;

import Characters.Caballero;
import Characters.Duende;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Juego extends JFrame {
    private Caballero caballero;
    private Duende duende;
    private JuegoPanel juegoPanel;

    public Juego() {
        setTitle("Mi Juego");
        setSize(700, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            caballero = new Caballero();
            duende = new Duende();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Crear un temporizador para actualizar el juego cada 10 ms
        javax.swing.Timer timer = new javax.swing.Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                caballero.actualizar();
                duende.actualizar();
                juegoPanel.repaint(); // Volver a dibujar para reflejar el movimiento
            }
        });
        timer.start();

        // Crear el JPanel del juego
        juegoPanel = new JuegoPanel();
        setContentPane(juegoPanel);

        // Agregar el KeyListener al JFrame para capturar eventos de teclado
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // No necesitamos implementar esto
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // Obtener el código de la tecla presionada
                int key = e.getKeyCode();
                // Mover al caballero según la tecla presionada
                if (key == KeyEvent.VK_UP) {
                    caballero.moveUp();
                    duende.moveUp();
                } else if (key == KeyEvent.VK_DOWN) {
                    caballero.moveDown();
                    duende.moveDown();
                } else if (key == KeyEvent.VK_LEFT) {
                    caballero.moveLeft();
                    duende.moveLeft();
                } else if (key == KeyEvent.VK_RIGHT) {
                    caballero.moveRight();
                    duende.moveRight();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // No necesitamos implementar esto
            }
        });

        // Hacer que el JFrame tenga el enfoque para que pueda recibir eventos de teclado
        setFocusable(true);
        requestFocus();
    }

    // Clase interna para representar el JPanel del juego
        private class JuegoPanel extends JPanel {
        private BufferedImage fondoImagen;

        public JuegoPanel() {
            try {
                fondoImagen = ImageIO.read(this.getClass().getResource("/images/fondo.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Dibujar el fondo del juego
            g.drawImage(fondoImagen, 0, 0, getWidth(), getHeight(), this);
            // Dibujar el caballero
            caballero.paintComponent(g);
            duende.paintComponent(g);
        }
    }

    public static void main(String[] args) {
        // Crear y mostrar el JFrame del juego
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Juego juegoFrame = new Juego();
                juegoFrame.setVisible(true);
            }
        });
    }
}