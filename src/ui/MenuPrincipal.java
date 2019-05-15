/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import elementos.Elemento;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

/**
 *
 * @author dam7l
 */
public class MenuPrincipal{

    private final int m_intAnchoVentana;
    private final int m_intAltoVentana;
    private final Elemento m_elementoOpcionSalir;
    private final Elemento m_elementoOpcionJugar;
        
    public MenuPrincipal(double anchoVentana, double altoVentana){
        m_intAnchoVentana = (int)(anchoVentana);
        m_intAltoVentana = (int)(altoVentana);
        
        
        m_elementoOpcionSalir = new Elemento(
                m_intAnchoVentana-(m_intAnchoVentana/34), 
                (m_intAnchoVentana/34), 
                "src/resources/btnSalir.png",
                m_intAnchoVentana/35,
                m_intAnchoVentana/35
        );
        
        
        m_elementoOpcionJugar = new Elemento(
                (m_intAnchoVentana/2), 
                (m_intAltoVentana/2)-(m_intAltoVentana/6), 
                "src/resources/btnJugar.png",
                (m_intAnchoVentana/6),
                m_intAnchoVentana/12
        );
        m_elementoOpcionJugar.pintar(Color.black);
    }
    
    public Elemento getOpcionSalir() {
        return m_elementoOpcionSalir;
    }

    public Elemento getOpcionJugar() {
        return m_elementoOpcionJugar;
    }
    
    public void MouseEnOpcionSalir(){
        ImageIcon imageicon = new ImageIcon("src/resources/btnSalirHover.png");
        m_elementoOpcionSalir.setSprite(imageicon.getImage());
        m_elementoOpcionSalir.Redimensionar(m_intAnchoVentana/35, m_intAnchoVentana/35);
        
       
    }
    
    public void MouseEnOpcionJugar(){
         m_elementoOpcionJugar.Redimensionar((m_intAnchoVentana/8), m_intAnchoVentana/16);
    }
    
    public void resetIcons(){
        
        ImageIcon imageicon = new ImageIcon("src/resources/btnSalirHover.png");
        m_elementoOpcionSalir.setSprite(imageicon.getImage());
        m_elementoOpcionSalir.Redimensionar(m_intAnchoVentana/35, m_intAnchoVentana/35);
        
        m_elementoOpcionJugar.Redimensionar((m_intAnchoVentana/6), m_intAnchoVentana/12);
        
    }
    
}

