package saltareh;

import controles.ControlJugador;
import elementos.Elemento;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import ui.MenuPrincipal;


public class Mundo extends JPanel implements Runnable {
    private final int m_intDelay = 25;
    private final int m_intAnchoVentana;
    private final int m_intAltoVentana;
    private Boolean m_bJuegoIniciado;
    ControlJugador  m_ctrlJugador;
    private Thread m_threadAnimator;
    private final SaltarEh m_Ventana;
    private MenuPrincipal m_menuPrincipal;
    
    public Mundo(int anchoVentana, int altoVentana, SaltarEh ventana){
        m_intAnchoVentana = anchoVentana;
        m_intAltoVentana = altoVentana;
        m_bJuegoIniciado = false;
        m_Ventana = ventana;
        IniciarMundo();
    }
    
    private void IniciarMundo(){
        
        m_menuPrincipal = new MenuPrincipal(m_intAnchoVentana, m_intAltoVentana);
        setPreferredSize(new Dimension(m_intAnchoVentana, m_intAltoVentana));
        setBackground(new Color(255,255,200));
        ConfigurarControles();
        
        addMouseListener(mousel);
        this.addMouseMotionListener(mouseMotionl);
    }
    
    public void ConfigurarControles(){
        m_ctrlJugador = new ControlJugador(m_intAnchoVentana, m_intAltoVentana);
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
        if(!m_bJuegoIniciado){
            m_menuPrincipal.getOpcionSalir().Dibujar(g);
            m_menuPrincipal.getOpcionJugar().Dibujar(g);
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
    
    MouseListener mousel = new MouseAdapter(){
        @Override 
        public void mousePressed(MouseEvent e) {
            Elemento colisionador = buscarElementoColision(e.getX(), e.getY());
            if(colisionador != null){
                if(colisionador == m_menuPrincipal.getOpcionSalir()){
                    System.exit(0);
                }
                if(colisionador == m_menuPrincipal.getOpcionJugar()){
                    m_bJuegoIniciado = true;
                    m_ctrlJugador.m_elementoMarioneta.setCy(
                            m_intAltoVentana+m_ctrlJugador.m_elementoMarioneta.getSprite().getHeight(null)
                    );
                    m_Ventana.Jugar();
                }
            }
        }
        
    };
    
    MouseMotionListener mouseMotionl = new MouseAdapter(){
        @Override
        public void mouseMoved(MouseEvent e){
            Elemento colisionador = buscarElementoColision(e.getX(), e.getY());
            if(colisionador != null){
                if(colisionador == m_menuPrincipal.getOpcionSalir()){
                    m_menuPrincipal.MouseEnOpcionSalir();
                }
                else if(colisionador == m_menuPrincipal.getOpcionJugar()){
                    m_menuPrincipal.MouseEnOpcionJugar();
                }
            }
            else{
               // m_menuPrincipal.resetIcons();
            }
        }
    };
    
    private Elemento buscarElementoColision(int cX, int cY){
        if(m_menuPrincipal.getOpcionSalir().getLimites().intersects(new Rectangle(cX, cY, 1, 1))){
            return m_menuPrincipal.getOpcionSalir();
        }
        else if(m_menuPrincipal.getOpcionJugar().getLimites().intersects(new Rectangle(cX, cY, 1, 1))){
            return m_menuPrincipal.getOpcionJugar();
        }
        return null;
    }
    
   
       
}
