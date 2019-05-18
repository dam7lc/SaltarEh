/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos;

import controles.ControladorPlataforma;
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
    protected String m_stringNombre;
    protected Image m_imgSprite;
    protected Image m_imgSpriteSobre;
    protected float m_floatCx;
    protected float m_floatCy;
    protected int m_intAncho;
    protected int m_intAlto;
    protected Rectangle m_rectLimites;
    protected boolean m_bMouseSobre;
    protected boolean m_bHayColision;protected boolean m_bEsSpriteAjustada = false;
    protected boolean m_bEsClickeable = false;
    
    public Elemento(String nombre, int x, int y, String imgSrc, String imgHoverSrc, int ancho, int alto){
        Iniciar(nombre, x, y, imgSrc, imgHoverSrc, ancho, alto);
        m_bMouseSobre = false;
    }
    
    private void Iniciar(String nombre, int x, int y, String imgSrc, String imgHoverSrc, int ancho, int alto){
        m_stringNombre = nombre;
        setCx(x);
        setCy(y);
        setAncho(ancho);
        setAlto(alto);
        ImageIcon icon = new ImageIcon(imgSrc);
        setSprite(Dimensionar(icon, ancho, alto));
        addCx(-((getAncho())/2));
        addCy(-((getAlto())/2));
        ImageIcon iconHover = new ImageIcon(imgHoverSrc);
        if(imgHoverSrc == null){
            setSpriteSobre(Dimensionar(icon, (int)(getAncho()*.9f), (int)(getAlto()*.9f)));
            m_bEsSpriteAjustada = true;
        }
        else {
            setSpriteSobre(Dimensionar(iconHover, (int)(getAncho()), (int)(getAlto())));
        }
        
    }
    
    public Image Dimensionar(ImageIcon imageIcon, int ancho, int alto){
        imageIcon = new ImageIcon(
                imageIcon.getImage().getScaledInstance(
                        ancho, alto,  java.awt.Image.SCALE_SMOOTH
                )
        );
        return imageIcon.getImage();
        
    }
    
    
    public void enMouseSobre(){
        m_bMouseSobre = true;
    }
    
    public void enMouseFuera(){
        m_bMouseSobre = false;
    }
    
    public void Dibujar(Graphics g){
        
        if(m_bMouseSobre && m_imgSpriteSobre != null){
            if(m_bEsSpriteAjustada){
                g.drawImage(m_imgSpriteSobre, Math.round(m_floatCx)+((int)(getAncho()*.1f)/2), Math.round(m_floatCy)+((int)(getAlto()*.1f)/2), null);
            }
            else {
                g.drawImage(m_imgSpriteSobre, Math.round(m_floatCx), Math.round(m_floatCy), null);
            }
        }
        else{
            g.drawImage(m_imgSprite, Math.round(m_floatCx), Math.round(m_floatCy), null);
        }
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
    
    public void setbEsClickeable(boolean b){
        m_bEsClickeable = b;
    }
    
    public boolean getbEsClickeable(){
        return m_bEsClickeable;
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
    
    public void setSpriteSobre(Image image) {
        this.m_imgSpriteSobre = image;
        setAncho(image.getWidth(null));
        setAlto(image.getHeight(null));
        calcularLimites();
        
    }
    
    public void calcularLimites(){
        m_rectLimites = new Rectangle( 
                Math.round(getCx()),
                Math.round(getCy()),
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
        g.setColor(c);
        g.fillRect(0,0,w,h);
        g.dispose();
        
        BufferedImage iconoAPintar2 = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = iconoAPintar2.createGraphics();
        g2.drawImage(m_imgSpriteSobre, 0,0, null);
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.setColor(c);
        g2.fillRect(0,0,w,h);
        g2.dispose();
        
        ImageIcon imgicon = new ImageIcon(iconoAPintar);
        this.m_imgSprite = imgicon.getImage();
        
        ImageIcon imgicon2 = new ImageIcon(iconoAPintar2);
        this.m_imgSpriteSobre = imgicon2.getImage();
        calcularLimites();
    }
    
    public ControladorPlataforma probarColision(ControladorPlataforma[] ctrlPlataformasEstaticas){
        for(ControladorPlataforma c : ctrlPlataformasEstaticas){
            if(c!=null){
                if(!getbHayColision() && getLimites().intersects(c.m_elementoMarioneta.getLimites()) ){
                    
                    setbHayColision(true);
                    if(c.m_elementoMarioneta.getCy() > getCy()){
                        return c;
                    }
                    return null;
                }
                 
                if(!getLimites().intersects(c.m_elementoMarioneta.getLimites())){
                    setbHayColision(false);
                }
                
            }
        }
        
        return null;
    }

    public float getCx() {
        return m_floatCx;
    }

    public void setCx(float cx) {
        this.m_floatCx = cx;
    }

    public float getCy() {
        return m_floatCy;
    }

    public void setCy(float cy) {
        this.m_floatCy = cy;
    }
    
    public void addCx(float cx) {
        this.m_floatCx += cx;
    }
    
    public void addCy(float cy) {
        this.m_floatCy += cy;
    }
    
    public String getName(){
        return m_stringNombre;
    }

    public boolean getbHayColision() {
        return m_bHayColision;
    }

    public void setbHayColision(boolean m_bHayColision) {
        this.m_bHayColision = m_bHayColision;
    }
    
}
