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
public class ControladorPlataforma extends Controlador {
    
    float m_floatNuevoCy;
    boolean m_bPuedeMoverse = false;
    float m_floatVelocidad;
    Random generadorRandom;
    
    public ControladorPlataforma(int anchoVentana, int altoVentana, Mundo mundo){
        
        super(anchoVentana, altoVentana, mundo);
        generadorRandom = new Random();
        m_elementoMarioneta = new Elemento(
                "Plataforma estatica",
                generadorRandom.nextInt(m_intAnchoVentana),
                generadorRandom.nextInt(m_intAltoVentana),
                "src/resources/plataforma.png",
                null,
                m_intAnchoVentana/16,
                m_intAnchoVentana/80
        );
        m_elementoMarioneta.pintar(Color.blue);
    }
    
    @Override
    public void Mover(){
        

        if(m_bPuedeMoverse){
            
          
            m_floatVelocidad -= 9;
            for(ControladorPlataforma c : m_mundoJuego.getCtrlPlataformas()){
                
                c.m_elementoMarioneta.addCy(m_floatVelocidad*m_mundoJuego.getDeltaTime());
                c.m_elementoMarioneta.calcularLimites();
                if(c.m_elementoMarioneta.getCy() > m_intAltoVentana){
                    c.m_elementoMarioneta.setCx(generadorRandom.nextInt(m_intAnchoVentana));
                    c.m_elementoMarioneta.setCy(0);
                }
            }
            
            
            if(m_floatVelocidad < 0){
                m_bPuedeMoverse = false;
            }
        }
    }
    
    public void jugadorSalto(float cy){
        
        m_floatNuevoCy = m_intAltoVentana - (m_intAltoVentana/8.0f);
        
        
        if(m_floatNuevoCy < m_elementoMarioneta.getCy()){
            m_bPuedeMoverse = false;
            m_mundoJuego.getCtrlJugador().setVelocidad(m_intAltoVentana);
        }
        else{
            float distancia = (m_elementoMarioneta.getCy() - m_floatNuevoCy);
            boolean calcular = true;
            float supuestaVelocidad = m_mundoJuego.getCtrlJugador().getVelocidad();
            float tiempo = 0, delta = m_mundoJuego.getDeltaTime();
            while(calcular){
                cy -= supuestaVelocidad;
                tiempo+=delta;
                supuestaVelocidad-=9;
                if(supuestaVelocidad < 0){
                    calcular = false;
                }
            }
            System.out.println(tiempo);
            m_floatVelocidad = m_intAnchoVentana;
            m_bPuedeMoverse = true;
        }
        
    }
    
    public void setVelocidad(float velocidad){
        m_floatVelocidad = velocidad;
    }
    
    public void setbPuedeMoverse(boolean puede){
        m_bPuedeMoverse = puede;
    }
    
    
}
