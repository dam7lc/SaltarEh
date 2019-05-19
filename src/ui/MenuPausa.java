package ui;

import elementos.Elemento;
import java.awt.Color;
import java.awt.Graphics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dam7l
 */
public class MenuPausa extends Menu{
    private final Elemento m_elementoOpcionSalir;
    private final Elemento m_elementoTextoPausa;
    private final Elemento m_elementoOpcionPausa;
    private boolean m_bJuegoPausado = false;
    
    public MenuPausa(double anchoVentana, double altoVentana, boolean activo){
        super(anchoVentana, altoVentana, activo);
        
        
        m_elementoOpcionSalir = new Elemento(
                "Opcion Pausa Salir",
                m_intAnchoVentana-(m_intAnchoVentana/35),  
                (m_intAnchoVentana/35), 
                "src/resources/btnSalir.png",
                "src/resources/btnSalirHover.png",
                m_intAnchoVentana/35,
                m_intAnchoVentana/35,
                null   
        );
       
        m_elementoTextoPausa = new Elemento(
                "Texto Pausa",
                (m_intAnchoVentana/2), 
                (m_intAltoVentana/2), 
                "src/resources/textPausa.png",
                null,
                (m_intAnchoVentana/3),
                m_intAnchoVentana/12,
                null
        );

        m_elementoOpcionPausa = new Elemento(
                "Opcion Pausa",
                m_intAnchoVentana-(m_intAnchoVentana/35), 
                (m_intAnchoVentana/35),  
                "src/resources/btnPausa.jpg",
                "src/resources/btnPausaHover.jpg",
                m_intAnchoVentana/35,
                m_intAnchoVentana/35,
                null
        );
        
        m_elementoOpcionPausa.setbEsClickeable(true);
        m_elementoTextoPausa.pintar(Color.black);
        
        m_arrayElementos = new Elemento[3];
        m_arrayElementos[0] = m_elementoOpcionSalir;
        m_arrayElementos[1] = m_elementoTextoPausa;
        m_arrayElementos[2] = m_elementoOpcionPausa;
        
    }
    
    @Override
    public void Dibujar(Graphics g){
        if(m_bJuegoPausado){
            m_elementoTextoPausa.Dibujar(g);
            m_elementoOpcionSalir.Dibujar(g);
        }else {
            m_elementoOpcionPausa.Dibujar(g);
        }
    }
    
    public Elemento getOpcionSalir(){
        return m_elementoOpcionSalir;
    }
    
    public Elemento getOpcionPausa(){
        return m_elementoOpcionPausa;
    }
    
    public Elemento getTextoPausa(){
        return m_elementoTextoPausa;
    }
          
    public void setbJuegoPausado(boolean b){
        m_bJuegoPausado = b;
        if(b){
            m_elementoOpcionPausa.setbEsClickeable(false);
            m_elementoTextoPausa.setbEsClickeable(true);
            m_elementoOpcionSalir.setbEsClickeable(true);
        } else {
            
            m_elementoOpcionPausa.setbEsClickeable(true);
            m_elementoTextoPausa.setbEsClickeable(false);
            m_elementoOpcionSalir.setbEsClickeable(false);
        }
    }
}
