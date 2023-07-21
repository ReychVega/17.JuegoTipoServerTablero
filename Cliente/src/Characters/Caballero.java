package Characters;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Shuperman23
 */
public class Caballero {

    private double posX;
    private double posY;
    private int width;
    private int height;
    private final double velocidad;
    private final BufferedImage imagenCaballeroLeft;
    private final BufferedImage imagenCaballeroRight;
    private boolean mirandoDerecha;
    private boolean seleccionado;

    // Constructor
    public Caballero() throws IOException {
        this.width = 64;
        this.height = 64;
        this.posX = 350;
        this.posY = 850;
        this.velocidad = 10.0;

        this.imagenCaballeroLeft = ImageIO.read(this.getClass().getResource("/images/caballeroiz.png"));
        this.imagenCaballeroRight = ImageIO.read(this.getClass().getResource("/images/caballerode.png"));

        this.mirandoDerecha = true;
        this.seleccionado = false;
    }

    // Métodos getter y setter
    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    // Método para cambiar la dirección del caballero
    private void cambiarDireccion() {
        mirandoDerecha = !mirandoDerecha;
    }

    public double getVelocidad() {
        return velocidad;
    }

//    // Método para hacer que el caballero se mueva hacia el castillo
//    public void moverHaciaCastillo() {
//        // Ajustamos la posición del castillo en función del nuevo tamaño del fondo
//        double xCastillo = 350; // Posición en el centro del ancho del fondo (700/2)
//        double yCastillo = 48; // Posición en el borde superior del fondo
//
//        double dx = xCastillo - posX;
//        double dy = yCastillo - posY;
//        double distancia = Math.sqrt(dx * dx + dy * dy);
//
//        dx /= distancia;
//        dy /= distancia;
//
//        posX += dx * velocidad;
//        posY += dy * velocidad;
//
//        // Verificar si el caballero cambia de dirección
//        if (Math.abs(dx) > 0.01) { // Solo cambiar si se está moviendo horizontalmente
//            if ((dx < 0 && mirandoDerecha) || (dx > 0 && !mirandoDerecha)) {
//                cambiarDireccion();
//            }
//        }
//
//        // Manejar colisiones con los bordes del fondo
//        if (posX <= 0 || posX + width >= 700) { // Izquierda y derecha del fondo
//            cambiarDireccion();
//            posX = Math.max(0, Math.min(posX, 700 - width));
//        }
//
//        posY = Math.max(0, Math.min(posY, 900 - height)); // Borde inferior del fondo
//    }
//
//    // Método para hacer que el caballero ataque a los objetivos cercanos
//    public void atacar() {
//    }
    // Método para dibujar al caballero
    public void paintComponent(Graphics g) {
        // Seleccionar la imagen adecuada según la dirección
        BufferedImage imageToDraw = mirandoDerecha ? imagenCaballeroRight : imagenCaballeroLeft;
        g.drawImage(imageToDraw, (int) posX, (int) posY, width, height, null);
    }

    // Método para actualizar el comportamiento del caballero 
    public void actualizar() {
        if (!seleccionado) {
//            moverHaciaCastillo();
//            atacar();
        }
    }

//    // Método para seleccionar el caballero
//    public void seleccionar() {
//        seleccionado = true;
//    }
//
//    // Método para deseleccionar el caballero
//    public void deseleccionar() {
//        seleccionado = false;
//    }
    // Método para mover el caballero hacia arriba
    public void moveUp() {
        posY -= velocidad;
        if (posY < 0) {
            posY = 0;
        }
        cambiarDireccion();
    }

    // Método para mover el caballero hacia abajo
    public void moveDown() {
        posY += velocidad;
        if (posY > 900 - height) {
            posY = 900 - height;
        }
        cambiarDireccion();
    }

    // Método para mover el caballero hacia la izquierda
    public void moveLeft() {
        posX -= velocidad;
        if (posX < 0) {
            posX = 0;
        }
        cambiarDireccion();
    }

    // Método para mover el caballero hacia la derecha
    public void moveRight() {
        posX += velocidad;
        if (posX > 700 - width) {
            posX = 700 - width;
        }
        cambiarDireccion();
    }
}
