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
    
    
    public ControlJugador(int x, int y, int anchoVentana, int alturaVentana){
        this.Jugador = new Player(x, y, anchoVentana, alturaVentana);
        iAnchoVentana = anchoVentana;
        iAltoVentana = alturaVentana;
    }
    
    @Override
    public void Mover(){
        MovimientoDeSalto();
        if(m_bSeMueveDerecha){
            Jugador.addCx(15);
        }
        if(m_bSeMueveIzquierda){
            Jugador.addCx(-15);
        }
        if(Jugador.getCx() > iAnchoVentana){
            Jugador.setCx(0);
        }
        if(Jugador.getCx() < 0){
            Jugador.setCx(iAnchoVentana);
        }
    }
    
    public void MovimientoDeSalto(){
        if(bEstaCayendo)  {
            Jugador.addCy(7);
            System.out.println(Jugador.getCx());
        }  
        else{
            Jugador.addCy(-7);
        }
        if(Jugador.getCy()< (iAltoVentana/3)){
            bEstaCayendo = true;
        } else if(Jugador.getCy() > iAltoVentana-95){
            bEstaCayendo = false;
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
