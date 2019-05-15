package ui;

import elementos.Elemento;
import java.awt.Color;
import javax.swing.ImageIcon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dam7l
 */
public class MenuPausa {
    private final Elemento m_elementoOpcionSalir;
    private final Elemento m_elementoTextoPausa;
    
    public MenuPausa(double anchoVentana, double altoVentana){
        int intAnchoVentana = (int)(anchoVentana);
        int intAltoVentana = (int)(altoVentana);
        
        
        m_elementoOpcionSalir = new Elemento(
                intAnchoVentana-60, 20, 
                "src/resources/btnSalir.png",
                intAnchoVentana/35,
                intAnchoVentana/35
                   
        );
       
        m_elementoTextoPausa = new Elemento(
                (intAnchoVentana/2)-120, 
                (intAltoVentana/2)-100, 
                "src/resources/btnJugar.png",
                (intAnchoVentana/6),
                intAnchoVentana/12
        );
        m_elementoTextoPausa.pintar(Color.black);
    }
}
