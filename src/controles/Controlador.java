/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import elementos.Elemento;
import java.awt.Graphics;

/**
 *
 * @author dam7l
 */
public abstract class Controlador {
    public int iAnchoVentana, iAltoVentana;
    public Boolean bEstaCayendo = false;
    public Boolean m_bSeMueveDerecha = false;
    public Boolean m_bSeMueveIzquierda = false;
    public Elemento Jugador;
    public abstract void Mover();
    public void Dibujar(Graphics g){
        Jugador.Dibujar(g);
    }
}
