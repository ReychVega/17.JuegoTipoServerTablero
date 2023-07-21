/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Characters;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Aaronjo
 */
public class Duende extends GenericPVPCharacter {
    
     
    public Duende ()throws IOException{
        width = 64;
        height = 64;
        posX = 150;
        posY = 850;
        velocidad = 14.0;

        imagenCharacterLeft = ImageIO.read(getClass().getResource("/images/goblinL.png"));
        imagenCharacterRight = ImageIO.read(getClass().getResource("/images/goblinR.png"));

        mirandoDerecha = true;
        seleccionado = false;
    }
    
}
