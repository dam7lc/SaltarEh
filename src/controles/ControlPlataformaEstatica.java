/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import elementos.Elemento;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import saltareh.Mundo;

/**
 *
 * @author dam7l
 */
public class ControlPlataformaEstatica extends ControlPlataforma {
    
    
    
    public ControlPlataformaEstatica(int anchoVentana, int altoVentana, Mundo mundo){
        
        super(anchoVentana, altoVentana, mundo);
        m_elementoMarioneta = new Elemento(
                "Plataforma estatica",
                generadorRandom.nextInt(m_intAnchoVentana),
                generadorRandom.nextInt(m_intAltoVentana-(m_intAnchoVentana/16)),
                "src/resources/plataformaEstatica.png",
                null,
                m_intAnchoVentana/16,
                m_intAnchoVentana/80,
                this
        );
    }
    
    
    
    
    
    
}
