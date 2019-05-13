/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saltareh;

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 *
 * @author dam7l
 */
public class SaltarEh extends JFrame{

    
    public SaltarEh(){
        IniciarUI();
    }
    
    private void IniciarUI(){
        add(new Mundo());
        setResizable(true);
        /*setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setUndecorated(true);
        setVisible(true);*/
        pack();
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
