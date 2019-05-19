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
public class MenuPrincipal extends Menu{

    
    private final Elemento m_elementoOpcionSalir;
    private final Elemento m_elementoOpcionJugar;
    private final Elemento m_elementoOpcionAjustes;
    
        
    public MenuPrincipal(double anchoVentana, double altoVentana, boolean activo){
        super(anchoVentana, altoVentana, activo);
        
        m_elementoOpcionSalir = new Elemento(
                "Opcion Salir",
                m_intAnchoVentana-(m_intAnchoVentana/35), 
                (m_intAnchoVentana/35), 
                "src/resources/btnSalir.png",
                "src/resources/btnSalirHover.png",
                m_intAnchoVentana/35,
                m_intAnchoVentana/35,
                null
        );
        m_elementoOpcionSalir.setbEsClickeable(true);
        
        m_elementoOpcionJugar = new Elemento(
                "OpcionJugar", 
                (m_intAnchoVentana/2), 
                (m_intAltoVentana/2)-(m_intAltoVentana/6), 
                "src/resources/btnJugar.png",
                null,
                (m_intAnchoVentana/6),
                m_intAnchoVentana/12,
                null
        );
        m_elementoOpcionJugar.setbEsClickeable(true);
        m_elementoOpcionJugar.pintar(Color.black);
        
        m_elementoOpcionAjustes = new Elemento(
                "Opcion Ajustes",
                (m_intAnchoVentana/2), 
                (m_intAltoVentana/2), 
                "src/resources/textAjustes.png",
                null,
                (m_intAnchoVentana/6),
                m_intAnchoVentana/18,
                null
        );
        m_elementoOpcionAjustes.setbEsClickeable(true);
        m_elementoOpcionAjustes.pintar(Color.black);
        
        m_arrayElementos = new Elemento[3];
        m_arrayElementos[0] = m_elementoOpcionSalir;
        m_arrayElementos[1] = m_elementoOpcionJugar;
        m_arrayElementos[2] = m_elementoOpcionAjustes;
    }
    
    public Elemento getOpcionSalir() {
        return m_elementoOpcionSalir;
    }

    public Elemento getOpcionJugar() {
        return m_elementoOpcionJugar;
    }
}

