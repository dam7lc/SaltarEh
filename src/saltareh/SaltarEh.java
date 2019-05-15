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

    private Boolean m_bJuegoIniciado = false;
    
    public SaltarEh(){
        IniciarUI();
    }
    
    private void IniciarUI(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double ancho = screenSize.getWidth();
        double alto = screenSize.getHeight();
        double ratio = ancho/alto;
        System.out.println(ratio);
        setPreferredSize(new Dimension((int)ancho, (int)alto));
        add(new MenuPrincipal((int)ancho, (int)alto));
        //add(new Mundo(width, height));
        setResizable(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setUndecorated(true);
        setVisible(true);
        setTitle("Saltar Eh!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            SaltarEh Juego = new SaltarEh();
            Juego.setVisible(true);
        });
    }
    
}
