/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Characters;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * @author Aaronjo
 * Se crea la clase "genericPVPCharacter" para reutilizar el codigo de la clase "caballero"
 * Esto con el fin de que hereden estas caracteristicas genericas a sus personajes del tipo PVP
 * osea que ataquen cuerpo a cuerpo
 * 
 * 
 */
public class GenericPVPCharacter {
    public double posX;
    public double posY;
    public int width;
    public int height;
    public double velocidad;
    public BufferedImage imagenCharacterLeft;
    public BufferedImage imagenCharacterRight;
    public boolean mirandoDerecha;
    public boolean seleccionado;

    // Constructor
    public GenericPVPCharacter() throws IOException {
        this.width = 64;
        this.height = 64;
        this.posX = 350;
        this.posY = 850;
        this.velocidad = 10.0;

        this.imagenCharacterLeft = ImageIO.read(this.getClass().getResource(""));
        this.imagenCharacterRight = ImageIO.read(this.getClass().getResource(""));

        this.mirandoDerecha = true;
        this.seleccionado = false;
    }
    
    public BufferedImage getImagenCharacterLeft() {
        return imagenCharacterLeft;
    }

    public void setImagenCharacterLeft(BufferedImage img) {
        this.imagenCharacterLeft = img;
    }
    
    public BufferedImage getImagenCharacterRight() {
        return imagenCharacterRight;
    }

    public void setImagenCharacterRight(BufferedImage img) {
        this.imagenCharacterRight = img;
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

    // Método para cambiar la dirección del personaje
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
//        // Verificar si el personaje cambia de dirección
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
//    // Método para hacer que el personaje ataque a los objetivos cercanos
//    public void atacar() {
//    }
    // Método para dibujar al personaje
    public void paintComponent(Graphics g) {
        // Seleccionar la imagen adecuada según la dirección
        BufferedImage imageToDraw = mirandoDerecha ? imagenCharacterRight : imagenCharacterLeft;
        g.drawImage(imageToDraw, (int) posX, (int) posY, width, height, null);
    }

    // Método para actualizar el comportamiento del personaje 
    public void actualizar() {
        if (!seleccionado) {
//            moverHaciaCastillo();
//            atacar();
        }
    }

//    // Método para seleccionar el personaje
//    public void seleccionar() {
//        seleccionado = true;
//    }
//
//    // Método para deseleccionar el personaje
//    public void deseleccionar() {
//        seleccionado = false;
//    }
    // Método para mover el personaje hacia arriba
    public void moveUp() {
        posY -= velocidad;
        if (posY < 0) {
            posY = 0;
        }
        cambiarDireccion();
    }

    // Método para mover el personaje hacia abajo
    public void moveDown() {
        posY += velocidad;
        if (posY > 900 - height) {
            posY = 900 - height;
        }
        cambiarDireccion();
    }

    // Método para mover el personaje hacia la izquierda
    public void moveLeft() {
        posX -= velocidad;
        if (posX < 0) {
            posX = 0;
        }
        cambiarDireccion();
    }

    // Método para mover el perrsonaje hacia la derecha
    public void moveRight() {
        posX += velocidad;
        if (posX > 700 - width) {
            posX = 700 - width;
        }
        cambiarDireccion();
    }
}
