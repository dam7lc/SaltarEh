/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import elementos.Player;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

/**
 *
 * @author dam7l
 */
public class ControlJugador extends Controlador{
    
    
    
    public ControlJugador(int anchoVentana, int alturaVentana){
        m_elementoMarioneta = new Player(anchoVentana/2, alturaVentana, anchoVentana, alturaVentana);
        m_intAnchoVentana = anchoVentana;
        m_intAltoVentana = alturaVentana;
    }
    
    @Override
    public void Mover(){
        MovimientoDeSalto();
        if(m_bSeMueveDerecha){
            m_elementoMarioneta.addCx(15);
        }
        if(m_bSeMueveIzquierda){
            m_elementoMarioneta.addCx(-15);
        }
        if(m_elementoMarioneta.getCx() > m_intAnchoVentana){
            m_elementoMarioneta.setCx(0);
        }
        if(m_elementoMarioneta.getCx() < 0){
            m_elementoMarioneta.setCx(m_intAnchoVentana);
        }
    }
    
    public void MovimientoDeSalto(){
        if(m_bEstaCayendo)  {
            m_elementoMarioneta.addCy(7);
        }  
        else{
            m_elementoMarioneta.addCy(-7);
        }
        if(m_elementoMarioneta.getCy() < ((m_intAltoVentana/3)-45)){
            m_bEstaCayendo = true;
        } else if(m_elementoMarioneta.getCy() > m_intAltoVentana-95){
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
