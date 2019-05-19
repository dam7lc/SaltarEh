package saltareh;

import controles.ControlJugador;
import controles.ControlPlataformaDinamica;
import controles.ControladorEnemigo;
import controles.ControlPlataformaEstatica;
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

/**
 * 
 * @author dam7l
 */
public class Mundo extends JPanel implements Runnable {
    private final int m_intDelay = 16;
    private float m_floatDeltaTime;
    private final int m_intAnchoVentana;
    private final int m_intAltoVentana;
    private final int m_intNumeroEnemigos;
    private final int m_intNumeroPlataformasEstaticas;
    private final int m_intNumeroPlataformasDinamicas;
    private float m_floatTiempoAnterior;
    private final int m_intNumeroPlataformasFragiles;
    private boolean m_bJuegoIniciado;
    private ControlJugador  m_ctrlJugador;
    private ControladorEnemigo[] m_ctrlEnemigos;
    private ControlPlataformaEstatica[] m_ctrlPlataformasEstaticas;
    
    private ControlPlataformaDinamica[] m_ctrlPlataformasDinamicas;
    private Thread m_threadAnimator;
    private final SaltarEh m_Ventana;
    private MenuPrincipal m_menuPrincipal;
    private MenuPausa m_menuPausa;
    private boolean m_bJuegoPausado;
    
    /**
     * Constructor de la clase Mundo que se encarga de controlar todo lo 
     * relacionado al juego
     * @param anchoVentana El ancho en pixeles de la ventana donde se muestra el juego
     * @param altoVentana El alto el pixeles de la ventana donde se muestra el juego
     * @param ventana Referencia al JFrame donde se ejecuta el juego
     */
    public Mundo(int anchoVentana, int altoVentana, SaltarEh ventana){
        m_intAnchoVentana = anchoVentana;
        m_intAltoVentana = altoVentana;
        m_bJuegoIniciado = false;
        m_bJuegoPausado = false;
        m_Ventana = ventana;
        m_intNumeroEnemigos = 1 ;
        m_intNumeroPlataformasEstaticas = 3;
        m_intNumeroPlataformasDinamicas = 2;
        m_intNumeroPlataformasFragiles = 1;
        IniciarMundo();
    }
    
    /**
     * Metodo llamado desde el contructor, encargado de iniciar las variables,
     * llamar ConfigurarControles() y añadir listener para el click y movimiento 
     * del mouse
     */
    private void IniciarMundo(){
        
        m_menuPrincipal = new MenuPrincipal(m_intAnchoVentana, m_intAltoVentana, true);
        m_menuPausa = new MenuPausa(m_intAnchoVentana, m_intAltoVentana, false);
        setPreferredSize(new Dimension(m_intAnchoVentana, m_intAltoVentana));
        setBackground(new Color(255,255,200));
        ConfigurarControles();
        
        addMouseListener(mousel);
        this.addMouseMotionListener(mouseMotionl);
    }
    
    /**
     * Metodo llamado al seleccionar la opcion "Jugar" del menú principal,
     * intercambia ui entre menu principal al menu de juego/pausa, resetea el doodle
     * e inicializa las plataformas
     */
    public void IniciarPartida(){
        m_bJuegoIniciado = true;
        m_menuPrincipal.setbEstaActivo(false);
        m_menuPausa.setbEstaActivo(true);
        m_ctrlJugador.m_elementoMarioneta.setCy(
                m_intAltoVentana+m_ctrlJugador.m_elementoMarioneta.getSprite().getHeight(null)
        );
        m_Ventana.Jugar();
        m_ctrlPlataformasEstaticas = new ControlPlataformaEstatica[m_intNumeroPlataformasEstaticas]; 
        m_ctrlPlataformasDinamicas = new ControlPlataformaDinamica[m_intNumeroPlataformasDinamicas]; 
        for(int i = 0; i < m_intNumeroPlataformasEstaticas; i++){
            m_ctrlPlataformasEstaticas[i] = new ControlPlataformaEstatica(
                    m_intAnchoVentana,
                    m_intAltoVentana,
                    this
            );
        }
        for(int i = 0; i < m_intNumeroPlataformasDinamicas; i++){
            m_ctrlPlataformasDinamicas[i] = new ControlPlataformaDinamica(
                    m_intAnchoVentana,
                    m_intAltoVentana,
                    this
            );
        }
        m_floatTiempoAnterior = System.nanoTime();
    }
    
    /**
     * Metodo que se encarga de detener las plataformas cuando han llegado a su 
     * destino despues de que el doodle realiza un salto valido
     */
    public void detenerPlataformas(){
        if(m_ctrlPlataformasEstaticas != null){
            
            for(ControlPlataformaEstatica c : m_ctrlPlataformasEstaticas){
                c.setbPuedeMoverse(false);
            }
        }
    }
    
    //Metodo que añade los listeners al presionar las teclas a y d o flecha izquierda y derecha
    public void ConfigurarControles(){
        m_ctrlJugador = new ControlJugador(m_intAnchoVentana, m_intAltoVentana, this);
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
    
    /**
     * Metodo que añade el hilo de ejecución 
    */
    @Override
    public void addNotify(){
        super.addNotify();
        m_threadAnimator = new Thread(this);
        m_threadAnimator.start();
    }
   
    
    /**
     * Metodo que se encarga de pintar los objetos en pantalla
     * @param g Clase Graphics que pinta los elementos en pantalla
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawImage(g);
    }
    
    /**
     * Metodo llamado desde paintComponent que delega pintar a cada elemento
     * @param g Clase Graphics que pinta los elementos en pantalla
     */
    private void drawImage(Graphics g){
        if(m_ctrlJugador.m_elementoMarioneta != null){      
            m_ctrlJugador.m_elementoMarioneta.Dibujar(g);
        }
        if(!m_bJuegoIniciado){
            m_menuPrincipal.Dibujar(g);
        }
        else{
            m_menuPausa.Dibujar(g);
            for(ControlPlataformaEstatica c : m_ctrlPlataformasEstaticas){
                if(c != null){
                    c.m_elementoMarioneta.Dibujar(g);
                }
            }
            
            for(ControlPlataformaDinamica c : m_ctrlPlataformasDinamicas){
                if(c != null){
                    c.m_elementoMarioneta.Dibujar(g);
                }
            }
        }
        Toolkit.getDefaultToolkit().sync();
    }
    
    
    /**
     * Metodo que se ejecuta en cada cuadro del videojuego, utilizado para mover
     * los elementos que se muestran
     */
    private void cycle(){
        m_floatDeltaTime = System.nanoTime() - m_floatTiempoAnterior;
        if(!m_bJuegoPausado){
            m_ctrlJugador.Mover();
            if(m_ctrlPlataformasEstaticas!=null && m_ctrlPlataformasDinamicas != null){
                for(ControlPlataformaEstatica c : m_ctrlPlataformasEstaticas){
                    if(c!=null){
                        c.Mover();
                    }
                }
                for(ControlPlataformaDinamica c : m_ctrlPlataformasDinamicas){
                    if(c!=null){
                        c.Mover();
                    }
                }
            }
            if(m_bJuegoIniciado){
                m_ctrlJugador.probarColision(m_ctrlPlataformasEstaticas);
            }
            
        }
        m_floatTiempoAnterior = System.nanoTime();
    }
    
    /**
     * Metodo que implementa como va a correr el hilo encargado de realizar la 
     * ejecucion del juego
     */
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
                m_threadAnimator.sleep(sleep);
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
    
    /**
     * Funcion encargada de comprobar si hay colision entre un objeto y el mouse
     * 
     * @param cX coordenada en x del mouse
     * @param cY coordenada en y del mouse 
     * @return El elemento con el que hay colison o null si no hay elemento
     */
    private Elemento buscarElementoColision(int cX, int cY){
        if(m_menuPrincipal.getbEstaActivo()){
            return m_menuPrincipal.buscarElementoColision(cX, cY);
        }
        else if(m_menuPausa.getbEstaActivo()){
            return m_menuPausa.buscarElementoColision(cX, cY);
        }
        return null;
    }
    
    public float getDeltaTime(){
        float delta = m_floatDeltaTime/1000000000;
        if(delta > 0){
            delta = 0.016f;
        }
        return delta;
    }
    
    public ControlPlataformaEstatica[] getCtrlPlataformasEstaticas(){
        return m_ctrlPlataformasEstaticas;
    }
    
    public ControlPlataformaDinamica[] getCtrlPlataformasDinamicas(){
        return m_ctrlPlataformasDinamicas;
    }
    
    public ControlJugador getCtrlJugador(){
        return m_ctrlJugador;
    }
}
