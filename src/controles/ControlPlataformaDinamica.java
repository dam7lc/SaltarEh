/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import elementos.Elemento;
import java.util.Random;
import saltareh.Mundo;

/**
 *
 * @author dam7l
 */
public class ControlPlataformaDinamica extends ControlPlataforma {
    
    float m_floatVelocidadLateral;
    
    public ControlPlataformaDinamica(int anchoVentana, int altoVentana, Mundo mundo){
        
        super(anchoVentana, altoVentana, mundo);
        m_elementoMarioneta = new Elemento(
                "Plataforma estatica",
                generadorRandom.nextInt(m_intAnchoVentana),
                generadorRandom.nextInt(m_intAltoVentana),
                "src/resources/plataformaMovil.png",
                null,
                m_intAnchoVentana/16,
                m_intAnchoVentana/80,
                this
        );
        m_floatVelocidadLateral = 300;
    }
    
    @Override
    public void movimientoPlataformaEstatica(){
        m_elementoMarioneta.addCx(m_floatVelocidadLateral * m_mundoJuego.getDeltaTime());
        if(m_elementoMarioneta.getCx() < 0 || m_elementoMarioneta.getCx() > (m_intAnchoVentana + m_elementoMarioneta.getAncho())){
            m_floatVelocidadLateral = -m_floatVelocidadLateral;
        }
    }
    
}
