/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package personajes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 *
 * @author daza
 */
public class Nave implements Personaje {
    Image imagen = new ImageIcon(getClass().getResource("/imagenes/ship.png")).getImage();
    int xPos = 300 - imagen.getWidth(null);
    int yPos = 200 - imagen.getHeight(null);
    int rotation = 0;
    float aceleration = 0;

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        int spriteHeight = imagen.getHeight(null);
        int spriteWidth = imagen.getWidth(null);

        AffineTransform affineTransform = new AffineTransform();
        affineTransform.rotate(Math.toRadians(rotation), xPos + spriteWidth / 2, yPos
                + spriteHeight / 2);
        affineTransform.translate(xPos, yPos);

        g2d.drawImage(imagen, affineTransform, null);
    }
    
    public void update(int c){
        switch(c){
            case 37:
                rotation -= 2;
                break;
            case 39:
                rotation += 2;
                break;
            case 38:
                aceleration += 10;
                playSound();
                break;
        }
    }
    
    @Override
    public void update(){
        aceleration *= 0.9;
        xPos += aceleration * Math.cos(Math.toRadians(rotation));
        yPos += aceleration * Math.sin(Math.toRadians(rotation));
        
        xPos = (600 + xPos) % 600;
        yPos = (400 + yPos) % 400;
    }
    
    @Override
    public Rectangle getRect(){
        Rectangle rect = null;
        rect = new Rectangle(xPos, yPos, imagen.getWidth(null), imagen.getHeight(null));
        return rect;
    }
    
    public void playSound(){
        try {
            final Player apl = new Player(new FileInputStream(getClass().getResource("/sonidos/por_1.mp3").getFile()));
            new Thread(){
                public void run(){
                    try {
                        apl.play();
                    } catch (JavaLayerException ex) {
                        Logger.getLogger(Nave.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }.start();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Nave.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JavaLayerException ex) {
            Logger.getLogger(Nave.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}