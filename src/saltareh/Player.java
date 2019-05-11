/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saltareh;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 *
 * @author dam7lc
 */
public class Player {
    private Boolean bEstaCayendo = false;
    private Boolean bSeMueveDerecha = false;
    private Boolean bSeMueveIzquierda = false;
    private Image sprite;
    private int cx;
    private int cy;
    private int altura;
    private int ancho;
    
    Player(int x, int y, int ancho, int altura){
        cx = x;
        cy = y;
        this.ancho = ancho;
        this.altura = altura;
        ImageIcon ii = new ImageIcon("src/resources/Doodle.jpg");
        sprite = ii.getImage();
    }
    
    public void Dibujar(Graphics g){
        g.drawImage(sprite, cx, cy, null);
    }
    
    public void Saltar(){
        if(bEstaCayendo)  {
            cy+=7;
        }  
        else{
            cy-=7;
        }
        if(cy < (altura/3)){
            bEstaCayendo = true;
        } else if(cy > altura-95){
            bEstaCayendo = false;
        }
    }
    
    public void Mover(){
        int delay = 200;
        if(getbSeMueveDerecha()){
            cx+=15;
        }
        if(getbSeMueveIzquierda()){
            cx-=15;
        }
        if(cx > ancho){
            cx = 0;
        }
        if(cx < 0){
            cx = ancho;
        }
    }
    
    public void keyReleased(KeyEvent e){
        System.out.println("Tecla Despresionada xd");
    }

    public Boolean getbEstaCayendo() {
        return bEstaCayendo;
    }

    public void setbEstaCayendo(Boolean bEstaCayendo) {
        this.bEstaCayendo = bEstaCayendo;
    }
    
    public Boolean getbSeMueveDerecha() {
        return bSeMueveDerecha;
    }

    public void setbSeMueveDerecha(Boolean bSeMueveDerecha) {
        this.bSeMueveDerecha = bSeMueveDerecha;
    }

    public Boolean getbSeMueveIzquierda() {
        return bSeMueveIzquierda;
    }

    public void setbSeMueveIzquierda(Boolean bSeMueveIzquieda) {
        this.bSeMueveIzquierda = bSeMueveIzquieda;
    }

    public Image getSprite() {
        return sprite;
    }

    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }

    public int getCx() {
        return cx;
    }

    public void setCx(int cx) {
        this.cx = cx;
    }

    public int getCy() {
        return cy;
    }

    public void setCy(int cy) {
        this.cy = cy;
    }
    
}
