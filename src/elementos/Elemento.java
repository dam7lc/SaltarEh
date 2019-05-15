/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author dam7l
 */
public class Elemento {
    protected Image m_imgSprite;
    protected int m_intCx;
    protected int m_intCy;
    protected int m_intAlto;
    protected int m_intAncho;
    
    Elemento(int x, int y, int ancho, int altura, String imgSource){
        m_intCx = x;
        m_intCy = y;
        m_intAlto = ancho;
        m_intAncho = altura;
        ImageIcon ii = new ImageIcon(imgSource);
        m_imgSprite = ii.getImage();
    }
    
    public void Dibujar(Graphics g){
        g.drawImage(m_imgSprite, m_intCx, m_intCy, null);
    }
    
   

    public Image getSprite() {
        return m_imgSprite;
    }

    public void setSprite(Image sprite) {
        this.m_imgSprite = sprite;
    }

    public int getCx() {
        return m_intCx;
    }

    public void setCx(int cx) {
        this.m_intCx = cx;
    }

    public int getCy() {
        return m_intCy;
    }

    public void setCy(int cy) {
        this.m_intCy = cy;
    }
    
    public void addCx(int cx) {
        this.m_intCx += cx;
    }
    
    public void addCy(int cy) {
        this.m_intCy += cy;
    }
    
    public int getAncho(){
        return m_intAncho;
    }
}
