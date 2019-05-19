/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saltareh;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JFrame;
import ui.MenuPrincipal;

/**
 *
 * @author dam7l
 */
public class SaltarEh extends JFrame{

    /**
     * 
     */
    private MenuPrincipal m_menuPrincipal;
    private Mundo m_mundo;
    private int m_intAnchoVentana;
    private int m_intAltoVentana;
    
    /**
     * Configura el JFrame o ventana del juego
     */
    public SaltarEh(){
        IniciarUI();
    }
    
    /**
     * Configura el JFrame o ventana del juego
     */
    private void IniciarUI(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        double ancho = screenSize.getWidth();
//        double alto = screenSize.getHeight();
//        double ratio = ancho/alto;
double ancho = 1280;
double alto = 720;
        m_intAnchoVentana = (int)ancho;
        
        m_intAltoVentana = (int)alto;
        setPreferredSize(new Dimension(m_intAnchoVentana, m_intAltoVentana));
//        setResizable(false);
//        setExtendedState(JFrame.MAXIMIZED_BOTH); 
//        setUndecorated(true);
        setVisible(true);
        setTitle("Saltar Eh!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        m_mundo = new Mundo(m_intAnchoVentana, m_intAltoVentana, this);
        add(m_mundo);
        m_mundo.setFocusable(false);
        pack();
    }
    
    /**
     * Metodo que se ejecuta al seleccionar la opcion "Jugar" del menú principal,
     * solicita focus en la ventana para recibir la entrada del teclado
     */
    public void Jugar(){
        m_mundo.setFocusable(true);
        m_mundo.requestFocus();
    }
    
    /**
     * Metodo main, se inicia el EDT para Swing
     * @param args 
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            SaltarEh Juego = new SaltarEh();
            Juego.setVisible(true);
        });
    }
    
    
    
}
