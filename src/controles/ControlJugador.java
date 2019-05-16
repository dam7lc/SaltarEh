/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import elementos.Elemento;
import elementos.Player;
import java.awt.event.ActionEvent;
import java.util.Random;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

/**
 *
 * @author dam7l
 */
public class ControlJugador extends Controlador{
    
    private int m_intVelocidadCaida;
    
    
    public ControlJugador(int anchoVentana, int alturaVentana){
        
        m_elementoMarioneta = new Elemento(
                "Personaje ",
                anchoVentana/2, 
                alturaVentana, 
                "src/resources/Doodle.png", 
                null,
                anchoVentana/20, 
                anchoVentana/20
        );
        m_intAnchoVentana = anchoVentana;
        m_intAltoVentana = alturaVentana;
    }
    
    public Elemento probarColision(Elemento[] plataformasEstaticas){
        Elemento e = m_elementoMarioneta.probarColision(plataformasEstaticas);
        if(e!=null && m_elementoMarioneta.getbHayColision()){
            Random generadorRandom = new Random();
            e.setCx(generadorRandom.nextInt(m_intAnchoVentana));
            e.setCy(m_intAltoVentana/3+(generadorRandom.nextInt((m_intAltoVentana/2))));
            e.calcularLimites();
            if(m_bEstaCayendo){
                m_bEstaCayendo=false;
            }
        }
        return e;
    }
    
    @Override
    public void Mover(){
        MovimientoDeSalto();
        if(m_bSeMueveDerecha){
            m_elementoMarioneta.addCx(m_intAnchoVentana/91);
        }
        if(m_bSeMueveIzquierda){
            m_elementoMarioneta.addCx(-(m_intAnchoVentana/91));
        }
        if(m_elementoMarioneta.getCx() > m_intAnchoVentana){
            m_elementoMarioneta.setCx(0);
        }
        if(m_elementoMarioneta.getCx() < 0){
            m_elementoMarioneta.setCx(m_intAnchoVentana);
        }
        m_elementoMarioneta.calcularLimites();
    }
    
    public void MovimientoDeSalto(){
        m_intVelocidadCaida = m_elementoMarioneta.getCy()/32;
        if(m_bEstaCayendo)  {
            m_elementoMarioneta.addCy(m_intVelocidadCaida);
        }  
        else{
            m_elementoMarioneta.addCy(-(m_intVelocidadCaida));
        }
        if(m_elementoMarioneta.getCy() < ((m_intAltoVentana/3)-(m_elementoMarioneta.getSprite().getHeight(null)/2))){
            m_bEstaCayendo = true;
        } else if(m_elementoMarioneta.getCy() > m_intAltoVentana-m_elementoMarioneta.getSprite().getHeight(null)){
            m_bEstaCayendo = false;
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
}
