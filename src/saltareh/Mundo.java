package saltareh;

import controles.ControlJugador;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;


public class Mundo extends JPanel implements Runnable {
    private final int m_intDelay = 25;
    private final int m_intAnchoVentana;
    private final int m_intAltoVentana;
    ControlJugador  m_ctrlJugador;
    private Thread m_threadAnimator;
    
    public Mundo(int anchoVentana, int altoVentana){
        m_intAnchoVentana = anchoVentana;
        m_intAltoVentana = altoVentana;
        IniciarMundo();
    }
    
    private void IniciarMundo(){
        setPreferredSize(new Dimension(m_intAnchoVentana, m_intAltoVentana));
        setBackground(new Color(255,255,200));
        m_ctrlJugador = new ControlJugador(m_intAnchoVentana, m_intAltoVentana);
        ConfigurarControles();
        
        
    }
    
    private void ConfigurarControles(){
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "MoverIzq");
        getActionMap().put("MoverIzq", m_ctrlJugador.moverIzq); 
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "MoverDer");
        getActionMap().put("MoverDer", m_ctrlJugador.moverDer); 
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "MoverIzq");
        getActionMap().put("MoverIzq", m_ctrlJugador.moverIzq); 
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "MoverDer");
        getActionMap().put("MoverDer", m_ctrlJugador.moverDer); 
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), "MoverIzqSoltado");
        getActionMap().put("MoverIzqSoltado", m_ctrlJugador.moverIzqSoltado); 
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), "MoverDerSoltado");
        getActionMap().put("MoverDerSoltado", m_ctrlJugador.moverDerSoltado); 
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), "MoverIzqSoltado");
        getActionMap().put("MoverIzqSoltado", m_ctrlJugador.moverIzqSoltado); 
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), "MoverDerSoltado");
        getActionMap().put("MoverDerSoltado", m_ctrlJugador.moverDerSoltado); 
    }
    
    @Override
    public void addNotify(){
        super.addNotify();
        m_threadAnimator = new Thread(this);
        m_threadAnimator.start();
    }
   
    
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawImage(g);
    }
    
    private void drawImage(Graphics g){
        if(m_ctrlJugador.m_elementoMarioneta != null){      
            m_ctrlJugador.m_elementoMarioneta.Dibujar(g);
        }
        Toolkit.getDefaultToolkit().sync();
    }
    
    
    
    private void cycle(){
        m_ctrlJugador.Mover();
    }
    
    @Override
    public void run(){
        long beforeTime, timeDiff, sleep;
        beforeTime = System.currentTimeMillis();
        
        while(true){
            cycle();
            repaint();
            
            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = m_intDelay - timeDiff;
            
            if(sleep < 0){
                sleep = 2;
            }
            
            try{
                Thread.sleep(sleep);
            } catch(InterruptedException e){
                String msg = String.format("Thread interrupted: %s", e.getMessage());
                JOptionPane.showMessageDialog(
                        this, 
                        msg, 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE
                );
            }
            beforeTime = System.currentTimeMillis();
        }
    }
    
   
       
}
