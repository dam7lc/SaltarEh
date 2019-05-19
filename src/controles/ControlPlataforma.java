/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import java.util.Random;
import saltareh.Mundo;

/**
 *
 * @author dam7l
 */
public class ControlPlataforma extends Control {
    
    protected float m_floatNuevoCy;
    protected boolean m_bPuedeMoverse = false;
    protected float m_floatVelocidad;
    protected float m_floatAceleracion;
    protected Random generadorRandom;
    
    public ControlPlataforma(int anchoVentana, int altoVentana, Mundo mundo){
        super(anchoVentana, altoVentana, mundo);
        
        generadorRandom = new Random();
    }
    
    @Override
    public void Mover(){
        movimientoPlataformaEstatica();
        if(m_bPuedeMoverse){
            
          
            m_floatVelocidad += m_floatAceleracion;
            for(ControlPlataformaEstatica c : m_mundoJuego.getCtrlPlataformasEstaticas()){
                
                c.m_elementoMarioneta.addCy(m_floatVelocidad*m_mundoJuego.getDeltaTime());
                
                if(c.m_elementoMarioneta.getCy() > m_intAltoVentana){
                    c.m_elementoMarioneta.setCx(generadorRandom.nextInt(m_intAnchoVentana));
                    c.m_elementoMarioneta.setCy(0);
                }
            }
            for(ControlPlataformaDinamica c : m_mundoJuego.getCtrlPlataformasDinamicas()){
                
                c.m_elementoMarioneta.addCy(m_floatVelocidad*m_mundoJuego.getDeltaTime());
                
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
    
    public void movimientoPlataformaEstatica(){
        
    }
    public void jugadorSalto(float cy){
         
        m_floatNuevoCy = m_intAltoVentana - (m_intAltoVentana/8.0f);
        
        if(m_floatNuevoCy < m_elementoMarioneta.getCy()){
            m_bPuedeMoverse = false;
        }
        else{
            float distancia = (m_floatNuevoCy - m_elementoMarioneta.getCy());
            
            float tiempo = 1;
            float velocidadPromedio = distancia / tiempo;
            float velocidadInicial = (velocidadPromedio*2) -0;
            float aceleracion = (0 - velocidadInicial) / (tiempo - 0);
            
            m_floatVelocidad = velocidadInicial;
            m_floatAceleracion = aceleracion * m_mundoJuego.getDeltaTime();
            m_bPuedeMoverse = true;
        }
        
    }
    
    public void setbPuedeMoverse(boolean puede){
        m_bPuedeMoverse = puede;
    }
}
