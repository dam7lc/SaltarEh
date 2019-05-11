package saltareh;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;


public class Board extends JPanel implements Runnable {
    
    private final int B_WIDTH = 1280;
    private final int B_HEIGHT = 720;
    private final int INITIAL_X = 1280/2;
    private final int INITIAL_Y = 720;
    private final int DELAY = 25;
    
    private Thread animator;
    private Player Jugador;
    
    public Board(){
        initBoard();
    }
    
    private void initBoard(){
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImage();
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "MoverIzq");
        getActionMap().put("MoverIzq", moverIzq); 
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "MoverDer");
        getActionMap().put("MoverDer", moverDer); 
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), "MoverIzqSoltado");
        getActionMap().put("MoverIzqSoltado", moverIzqSoltado); 
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), "MoverDerSoltado");
        getActionMap().put("MoverDerSoltado", moverDerSoltado); 
        //int w = jugadorImg.getWidth(this);
        //int h = jugadorImg.getHeight(this);
        //setPreferredSize(new Dimension(h,w));
  
        //this.get
        Jugador = new Player(INITIAL_X, INITIAL_Y, B_WIDTH, B_HEIGHT);
        
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
        Jugador.Dibujar(g);
        Toolkit.getDefaultToolkit().sync();
    }
    
    Action moverIzq = new AbstractAction(){
        @Override
        public void actionPerformed(ActionEvent e){
            Jugador.setbSeMueveIzquierda(true);
        }
    };
    
    Action moverDer = new AbstractAction(){
        @Override
        public void actionPerformed(ActionEvent e){
            Jugador.setbSeMueveDerecha(true);
        }
    };
    
    Action moverIzqSoltado = new AbstractAction(){
        @Override
        public void actionPerformed(ActionEvent e){
            Jugador.setbSeMueveIzquierda(false);
        }
    };
    
    Action moverDerSoltado = new AbstractAction(){
        @Override
        public void actionPerformed(ActionEvent e){
            Jugador.setbSeMueveDerecha(false);
        }
    };
    
    private void cycle(){
        Jugador.Saltar();
        Jugador.Mover();
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
