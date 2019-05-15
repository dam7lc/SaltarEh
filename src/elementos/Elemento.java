/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 *
 * @author dam7l
 */
public class Elemento {
    protected Image m_imgSprite;
    protected int m_intCx;
    protected int m_intCy;
    protected int m_intAncho;
    protected int m_intAlto;
    protected Rectangle m_rectLimites;
    
    public Elemento(int x, int y, String imgSrc, int ancho, int alto){
        Iniciar(x, y, imgSrc, ancho, alto);
        
    }
    
    private void Iniciar(int x, int y, String imgSrc, int ancho, int alto){
        setCx(x);
        setCy(y);
        setAncho(ancho);
        setAlto(alto);
        ImageIcon icon = new ImageIcon(imgSrc);
        setSprite(icon.getImage());
        Dimensionar(ancho, alto);
    }
    
    public void Dimensionar(int ancho, int alto){
        ImageIcon imgicon = new ImageIcon(getSprite());
        imgicon = new ImageIcon(
                imgicon.getImage().getScaledInstance(
                        ancho, alto,  java.awt.Image.SCALE_SMOOTH
                )
        );
       
        setAncho(imgicon.getIconWidth());
        setAlto(imgicon.getIconHeight());
        addCx(-(getAncho()/2));
        addCy(-(getAlto()/2));
        setSprite(imgicon.getImage());
        
    }
    
    public void Redimensionar(int ancho, int alto){
        ImageIcon imgicon = new ImageIcon(getSprite());
        imgicon = new ImageIcon(
                imgicon.getImage().getScaledInstance(
                        ancho, alto,  java.awt.Image.SCALE_SMOOTH
                )
        );
        setSprite(imgicon.getImage());
        
    }
    
    public void Dibujar(Graphics g){
        g.drawImage(m_imgSprite, m_intCx, m_intCy, null);
    }
    
    public Rectangle getLimites(){
        return m_rectLimites;
    }

    public Image getSprite() {
        return m_imgSprite;
    }
    
    public void setAncho(int ancho){
        m_intAncho = ancho;
    }
    
    public int getAncho(){
        return m_intAncho;
    }
    
    public void setAlto(int alto){
        m_intAlto = alto;
    }
    
    public int getAlto(){
        return m_intAlto;
    }

    public void setSprite(Image image) {
        this.m_imgSprite = image;
        setAncho(image.getWidth(null));
        setAlto(image.getHeight(null));
        calcularLimites();
    }
    
    private void calcularLimites(){
        m_rectLimites = new Rectangle( 
                getCx(),
                getCy(),
                getSprite().getWidth(null),
                getSprite().getHeight(null)
        );
    }
    
    public void pintar(Color c){
        int w = getSprite().getWidth(null);
        int h = getSprite().getHeight(null);
        BufferedImage iconoAPintar = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = iconoAPintar.createGraphics();
        g.drawImage(getSprite(), 0,0, null);
        g.setComposite(AlphaComposite.SrcAtop);
        g.setColor(Color.black);
        g.fillRect(0,0,w,h);
        g.dispose();
        ImageIcon imgicon = new ImageIcon(iconoAPintar);
        this.m_imgSprite = imgicon.getImage();
        calcularLimites();
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
    
}
