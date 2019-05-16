/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import elementos.Elemento;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author dam7l
 */
public class Menu {
    
    protected boolean m_bMouseSobreOpcion = false;
    protected boolean m_bEstaActivo;
    protected Elemento[] m_arrayElementos; 
    protected final int m_intAnchoVentana;
    protected final int m_intAltoVentana;
    
    public Menu(double anchoVentana, double altoVentana, boolean activo){
        m_intAnchoVentana = (int)(anchoVentana);
        m_intAltoVentana = (int)(altoVentana);
        m_bEstaActivo = activo;
    }
    
    public void resetIcons(){
        if(m_bMouseSobreOpcion){
            for(Elemento e : m_arrayElementos){ 
                e.enMouseFuera();
            }
            m_bMouseSobreOpcion = false;
        }
    }
    
    public void Dibujar(Graphics g){
        for(Elemento e : m_arrayElementos){
            e.Dibujar(g);
        }
    }
    
    public Elemento buscarElementoColision(int cX, int cY){
        
        
            for(Elemento e : m_arrayElementos){
                if(e.getLimites().intersects(new Rectangle(cX, cY, 1, 1)) && e.getbEsClickeable()){
                    if(!m_bMouseSobreOpcion){
                        
                        e.enMouseSobre();
                        m_bMouseSobreOpcion = true;
                    }
                    return e;
                }
            } 
            
            return null;
       
    }
    
    public Boolean getbEstaActivo(){
        return m_bEstaActivo;
    }
    
    public void setbEstaActivo(Boolean Esta){
        m_bEstaActivo = Esta;
    }
}
