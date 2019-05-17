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

/**
 *
 * @author dam7l
 */
public class ControladorPlataforma extends Controlador {
    
    public ControladorPlataforma(int anchoVentana, int alturaVentana){
        
        
        m_intAnchoVentana = anchoVentana;
        m_intAltoVentana = alturaVentana;
        Random generadorRandom = new Random();
        m_elementoMarioneta = new Elemento(
                "Plataforma estatica",
                generadorRandom.nextInt(m_intAnchoVentana),
                m_intAltoVentana/2+(generadorRandom.nextInt((m_intAltoVentana/2))),
                "src/resources/plataforma.png",
                null,
                m_intAnchoVentana/16,
                m_intAnchoVentana/80
        );
        m_elementoMarioneta.pintar(Color.blue);
    }
    
    @Override
    public void Mover(){
        
    }
    
    public void jugadorSalto(int altura){
        System.out.println(m_elementoMarioneta.getCy() - altura);
        int nuevaPosicion = (m_elementoMarioneta.getCy() - altura) + m_elementoMarioneta.getCy();
        Random generadorRandom = new Random();
        m_elementoMarioneta.setCx(generadorRandom.nextInt(m_intAnchoVentana));
        m_elementoMarioneta.setCy(m_intAltoVentana/3+(generadorRandom.nextInt((m_intAltoVentana/2))));
        m_elementoMarioneta.calcularLimites();
                
    }
    
    
}
