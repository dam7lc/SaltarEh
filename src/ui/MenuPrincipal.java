/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author dam7l
 */
public class MenuPrincipal extends JPanel{
    private final int m_intAnchoVentana;
    private final int m_intAltoVentana;
    private final int m_intBtnSalirX;
    private final int m_intBtnSalirY = 20;
    private Image img;
    private final Rectangle exitButtonBounds;
    
    public MenuPrincipal(double anchoVentana, double altoVentana){
        m_intAnchoVentana = (int)(anchoVentana);
        m_intAltoVentana = (int)(altoVentana);
        m_intBtnSalirX = m_intAnchoVentana-60;
        setPreferredSize(new Dimension(m_intAnchoVentana, m_intAltoVentana));
        setBackground(new Color(255,255,200));
        ImageIcon imgicon = new ImageIcon("src/resources/btnSalir.png");
        img = imgicon.getImage().getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
        imgicon = new ImageIcon(img);
        img = imgicon.getImage();
        exitButtonBounds = new Rectangle( 
                m_intBtnSalirX,
                m_intBtnSalirY,
                img.getWidth(null),
                img.getHeight(null)
        );
        
        
        
        addMouseListener(mousel);
        
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawString("Comenzar!", 200, 400);
        g.drawImage(img, m_intBtnSalirX, m_intBtnSalirY, null);
    }
    
    MouseListener mousel = new MouseAdapter(){
        @Override 
        public void mousePressed(MouseEvent e) {
            probarColision(e.getX(), e.getY());
        }
    };
    
    private void probarColision(int cX, int cY){
        if(exitButtonBounds.intersects(new Rectangle(cX, cY, 1, 1))){
            System.out.println("Exit pressed");
            System.exit(0);
        }
    }
    
    
    
}

