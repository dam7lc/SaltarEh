/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import elementos.Elemento;
import java.awt.event.ActionEvent;
import java.util.Random;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import saltareh.Mundo;

/**
 *
 * @author dam7l
 */
public class ControlJugador extends Control{
    
    private float m_floatVelocidadCaida;
    private int m_intAlturaSalto;
    private float m_floatAceleracionCaida;
    private int m_intVelocidadLateral;
    
    public ControlJugador(int anchoVentana, int altoVentana, Mundo mundo){
        super(anchoVentana, altoVentana, mundo);
        m_elementoMarioneta = new Elemento(
                "Personaje ",
                anchoVentana/2, 
                altoVentana, 
                "src/resources/Doodle.png", 
                null,
                anchoVentana/20, 
                anchoVentana/20,
                this
        );
        m_intAlturaSalto = m_intAltoVentana/3 - m_elementoMarioneta.getAlto();
    }
    
    public void probarColision(ControlPlataformaEstatica[] ctrlPlataformasEstaticas){
        if(ctrlPlataformasEstaticas != null){
            ControlPlataforma c = m_elementoMarioneta.probarColision();
            if(c!=null){
                System.out.println("tiempo: " + System.currentTimeMillis() + 
                    " c es nulo " + (c == null) + 
                   " HayColision " + m_elementoMarioneta.getbHayColision() + 
                   " EstaCayendo " + m_bEstaCayendo + 
                   " Esta debajo " + (c.m_elementoMarioneta.getCy() > (m_elementoMarioneta.getCy() + m_elementoMarioneta.getAlto()))
                );
            }
           
            if(c!=null && m_elementoMarioneta.getbHayColision() && m_bEstaCayendo && c.m_elementoMarioneta.getCy()+2 > (m_elementoMarioneta.getCy() + m_elementoMarioneta.getAlto())){
                calcularVelocidad();
                c.jugadorSalto(m_elementoMarioneta.getCy()); 
                
            }
        }
    }
    
    public void calcularVelocidad(){
        float distancia = m_elementoMarioneta.getCy() - ((m_intAltoVentana/3));
        
        m_floatVelocidadCaida = (distancia) * 2;
        m_floatAceleracionCaida = (-(m_floatVelocidadCaida)) * m_mundoJuego.getDeltaTime();
        
        m_bEstaCayendo = false;
    }
    
    @Override
    public void Mover(){
        m_intVelocidadLateral = (m_intAnchoVentana/102);
        MovimientoDeSalto();
        if(m_bSeMueveDerecha){
            m_elementoMarioneta.addCx(m_intVelocidadLateral);
        }
        if(m_bSeMueveIzquierda){
            m_elementoMarioneta.addCx(-(m_intVelocidadLateral));
        }
        if(m_elementoMarioneta.getCx() > m_intAnchoVentana){
            m_elementoMarioneta.setCx(0);
        }
        if(m_elementoMarioneta.getCx() < 0){
            m_elementoMarioneta.setCx(m_intAnchoVentana);
        }
    }
    
    public void MovimientoDeSalto(){
                
        m_floatVelocidadCaida += m_floatAceleracionCaida;
        m_elementoMarioneta.addCy(-(m_floatVelocidadCaida*m_mundoJuego.getDeltaTime()));
        if(m_floatVelocidadCaida < 0 ){
            m_bEstaCayendo = true;
            m_floatAceleracionCaida = -9;
        }
        if(m_elementoMarioneta.getCy() > m_intAltoVentana-m_elementoMarioneta.getSprite().getHeight(null)){
            
            calcularVelocidad();
        }
    }
    
    public Action moverIzq = new AbstractAction(){
        @Override
        public void actionPerformed(ActionEvent e){
            m_bSeMueveIzquierda = true;
        }
    };
    
    public Action moverDer = new AbstractAction(){
        @Override
        public void actionPerformed(ActionEvent e){
            m_bSeMueveDerecha = true;
        }
    };
    
    public Action moverIzqSoltado = new AbstractAction(){
        @Override
        public void actionPerformed(ActionEvent e){
            m_bSeMueveIzquierda = false;
        }
    };
    
    public Action moverDerSoltado = new AbstractAction(){
        @Override
        public void actionPerformed(ActionEvent e){
            m_bSeMueveDerecha = false;
        }
    };
    
    public float getVelocidad(){
        return m_floatVelocidadCaida;
    }
    public void setVelocidad(float velocidad){
        m_floatVelocidadCaida = velocidad;
    }
    
    
}
