/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import elementos.Elemento;

/**
 *
 * @author dam7l
 */
public abstract class Controlador {
    public int m_intAnchoVentana, m_intAltoVentana;
    public Boolean m_bSeMueveDerecha = false;
    public Boolean m_bSeMueveIzquierda = false;
    public Boolean m_bEstaCayendo = false;
    public Elemento m_elementoMarioneta;
    public abstract void Mover();
   
}
