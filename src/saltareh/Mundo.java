package saltareh;

import controles.ControlJugador;
import controles.ControladorEnemigo;
import controles.ControladorPlataforma;
import elementos.Elemento;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import java.util.Random;
import ui.MenuPausa;
import ui.MenuPrincipal;


public class Mundo extends JPanel implements Runnable {
    private final int m_intDelay = 25;
    private final int m_intAnchoVentana;
    private final int m_intAltoVentana;
    private final int m_intNumeroEnemigos;
    private final int m_intNumeroPlataformasEstaticas;
    private final int m_intNumeroPlataformasDinamicas;
    private final int m_intNumeroPlataformasFragiles;
    private boolean m_bJuegoIniciado;
    private ControlJugador  m_ctrlJugador;
    private ControladorEnemigo[] m_ctrlEnemigos;
    private ControladorPlataforma[] m_ctrlPlataformas;
    private Thread m_threadAnimator;
    private final SaltarEh m_Ventana;
    private MenuPrincipal m_menuPrincipal;
    private MenuPausa m_menuPausa;
    private boolean m_bJuegoPausado;
    
    public Mundo(int anchoVentana, int altoVentana, SaltarEh ventana){
        m_intAnchoVentana = anchoVentana;
        m_intAltoVentana = altoVentana;
        m_bJuegoIniciado = false;
        m_bJuegoPausado = false;
        m_Ventana = ventana;
        m_intNumeroEnemigos = 1 ;
        m_intNumeroPlataformasEstaticas = 5;
        m_intNumeroPlataformasDinamicas = 1;
        m_intNumeroPlataformasFragiles = 2;
        IniciarMundo();
    }
    
    private void IniciarMundo(){
        
        m_menuPrincipal = new MenuPrincipal(m_intAnchoVentana, m_intAltoVentana, true);
        m_menuPausa = new MenuPausa(m_intAnchoVentana, m_intAltoVentana, false);
        setPreferredSize(new Dimension(m_intAnchoVentana, m_intAltoVentana));
        setBackground(new Color(255,255,200));
        ConfigurarControles();
        
        addMouseListener(mousel);
        this.addMouseMotionListener(mouseMotionl);
    }
    
    public void IniciarPartida(){
        m_bJuegoIniciado = true;
        m_menuPrincipal.setbEstaActivo(false);
        m_menuPausa.setbEstaActivo(true);
        m_ctrlJugador.m_elementoMarioneta.setCy(
                m_intAltoVentana+m_ctrlJugador.m_elementoMarioneta.getSprite().getHeight(null)
        );
        m_Ventana.Jugar();
        m_ctrlPlataformas = new ControladorPlataforma[m_intNumeroPlataformasEstaticas]; 
        for(int i = 0; i < m_intNumeroPlataformasEstaticas; i++){
            m_ctrlPlataformas[i] = new ControladorPlataforma(
                    m_intAnchoVentana,
                    m_intAltoVentana
            );
        }
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
            m_menuPrincipal.Dibujar(g);
        }
        else{
            m_menuPausa.Dibujar(g);
            for(ControladorPlataforma c : m_ctrlPlataformas){
                if(c != null){
                    c.m_elementoMarioneta.Dibujar(g);
                }
            }
        }
        Toolkit.getDefaultToolkit().sync();
    }
    
    
    
    private void cycle(){
        if(!m_bJuegoPausado){
            m_ctrlJugador.Mover();
            if(m_bJuegoIniciado){
                m_ctrlJugador.probarColision(m_ctrlPlataformas);
            }
            
        }
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
                if(m_menuPrincipal.getbEstaActivo()){
                    if(colisionador == m_menuPrincipal.getOpcionSalir()){
                        System.exit(0);
                    }
                    else if(colisionador == m_menuPrincipal.getOpcionJugar()){
                        IniciarPartida();
                    }
                }
                if(m_menuPausa.getbEstaActivo()){
                    if(colisionador == m_menuPausa.getOpcionPausa()){
                        m_bJuegoPausado = true;
                        m_menuPausa.setbJuegoPausado(true);
                    } else if( colisionador == m_menuPausa.getOpcionSalir()){
                        System.exit(0);
                    } else if( colisionador == m_menuPausa.getTextoPausa()){
                        m_bJuegoPausado = false;
                        m_menuPausa.setbJuegoPausado(false);
                    }
                }
            }
        }
        
    };
    
    MouseMotionListener mouseMotionl = new MouseAdapter(){
        @Override
        public void mouseMoved(MouseEvent e){
            Elemento colisionador = buscarElementoColision(e.getX(), e.getY());
            if(colisionador == null){
                if(m_menuPrincipal.getbEstaActivo()){
                    m_menuPrincipal.resetIcons();
                }
                else if(m_menuPausa.getbEstaActivo()){
                    m_menuPausa.resetIcons();
                }
            }
        }
    };
    
    private Elemento buscarElementoColision(int cX, int cY){
        if(m_menuPrincipal.getbEstaActivo()){
            return m_menuPrincipal.buscarElementoColision(cX, cY);
        }
        else if(m_menuPausa.getbEstaActivo()){
            return m_menuPausa.buscarElementoColision(cX, cY);
        }
        return null;
    }
    
   
       
}
