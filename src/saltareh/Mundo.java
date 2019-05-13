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
    
    private final int B_WIDTH = 1280;
    private final int B_HEIGHT = 720;
    private final int INITIAL_X = 1280/2;
    private final int INITIAL_Y = 720;
    private final int DELAY = 25;
    ControlJugador  ctrlJugador;
   
    private Thread animator;
    
    public Mundo(){
        IniciarMundo();
    }
    
    private void IniciarMundo(){
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        ctrlJugador = new ControlJugador(INITIAL_X, INITIAL_Y, B_WIDTH, B_HEIGHT);
        loadImage();
        ConfigurarControles();
        
        //int w = jugadorImg.getWidth(this);
        //int h = jugadorImg.getHeight(this);
        //setPreferredSize(new Dimension(h,w));
  
        //this.get
        
        
    }
    
    private void ConfigurarControles(){
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "MoverIzq");
        getActionMap().put("MoverIzq", ctrlJugador.moverIzq); 
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "MoverDer");
        getActionMap().put("MoverDer", ctrlJugador.moverDer); 
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "MoverIzq");
        getActionMap().put("MoverIzq", ctrlJugador.moverIzq); 
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "MoverDer");
        getActionMap().put("MoverDer", ctrlJugador.moverDer); 
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), "MoverIzqSoltado");
        getActionMap().put("MoverIzqSoltado", ctrlJugador.moverIzqSoltado); 
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), "MoverDerSoltado");
        getActionMap().put("MoverDerSoltado", ctrlJugador.moverDerSoltado); 
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), "MoverIzqSoltado");
        getActionMap().put("MoverIzqSoltado", ctrlJugador.moverIzqSoltado); 
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), "MoverDerSoltado");
        getActionMap().put("MoverDerSoltado", ctrlJugador.moverDerSoltado); 
    }
    
    @Override
    public void addNotify(){
        super.addNotify();
        animator = new Thread(this);
        animator.start();
    }
   
    
    private void loadImage(){
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawImage(g);
    }
    
    private void drawImage(Graphics g){
        ctrlJugador.Dibujar(g);
        Toolkit.getDefaultToolkit().sync();
    }
    
    
    
    private void cycle(){
        ctrlJugador.Mover();
    }
    
    @Override
    public void run(){
        long beforeTime, timeDiff, sleep;
        beforeTime = System.currentTimeMillis();
        
        while(true){
            cycle();
            repaint();
            
            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;
            
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
