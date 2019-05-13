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
    protected Image m_iSprite;
    protected int m_iCx;
    protected int m_iCy;
    protected int m_iAltura;
    protected int m_iAncho;
    
    Elemento(int x, int y, int ancho, int altura, String imgSource){
        m_iCx = x;
        m_iCy = y;
        m_iAncho = ancho;
        m_iAltura = altura;
        ImageIcon ii = new ImageIcon(imgSource);
        m_iSprite = ii.getImage();
    }
    
    public void Dibujar(Graphics g){
        g.drawImage(m_iSprite, m_iCx, m_iCy, null);
    }

    public Image getSprite() {
        return m_iSprite;
    }

    public void setSprite(Image sprite) {
        this.m_iSprite = sprite;
    }

    public int getCx() {
        return m_iCx;
    }

    public void setCx(int cx) {
        this.m_iCx = cx;
    }

    public int getCy() {
        return m_iCy;
    }

    public void setCy(int cy) {
        this.m_iCy = cy;
    }
    
    public void addCx(int cx) {
        this.m_iCx += cx;
    }
    
    public void addCy(int cy) {
        this.m_iCy += cy;
    }
    
    public int getAncho(){
        return m_iAncho;
    }
}
