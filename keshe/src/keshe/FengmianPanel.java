/*
 * MenuPanel.java
 *
 * Created on 2010/3/30, 12:09
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package keshe;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author kai wang
 */
public class FengmianPanel extends JPanel implements MouseListener, MouseMotionListener{
    
   
    
    private ImageIcon imageIcon = new ImageIcon("menu\\"+0+".GIF");

    ImageIcon icon10 = new ImageIcon("menu\\"+10+".GIF");
    ImageIcon icon11 = new ImageIcon("menu\\"+11+".GIF");
    ImageIcon icon20 = new ImageIcon("menu\\"+20+".GIF");
    ImageIcon icon21 = new ImageIcon("menu\\"+21+".GIF");
    ImageIcon icon30 = new ImageIcon("menu\\"+30+".GIF");
    ImageIcon icon40 = new ImageIcon("menu\\"+12+".GIF");
    ImageIcon icon50 = new ImageIcon("menu\\"+50+".GIF");
    ImageIcon icon60 = new ImageIcon("menu\\"+60+".GIF");
    ImageIcon icon70 = new ImageIcon("menu\\"+70+".GIF");
    ImageIcon icon80 = new ImageIcon("menu\\"+80+".GIF");
    ImageIcon icon81 = new ImageIcon("menu\\"+81+".GIF");
   
    JLabel label1 = new JLabel(icon10); 
    JLabel label2 = new JLabel(icon20); 
    JLabel label3 = new JLabel(icon30);
    JLabel label4 = new JLabel(icon40); 
    JLabel label5 = new JLabel(icon50);  
    JLabel label6 = new JLabel(icon60); 
    JLabel label7 = new JLabel(icon70); 
    JLabel label8 = new JLabel(icon80); 
   
   
    public FengmianPanel() {
      
       setPreferredSize(new Dimension(600,680));
       setLayout(null);
       
        label1.setBounds(210,290,200,90);
        label2.setBounds(210,380,200,90);
        label3.setBounds(100,210,50,80);
        label4.setBounds(220,200,50,80);
        label5.setBounds(340,210,50,80);
        label6.setBounds(460,210,50,80);
        label7.setBounds(110,50,400,200);
        label8.setBounds(210,470,200,90);

        
        add(label1);
        label1.addMouseListener(this);
        label1.addMouseMotionListener(this);
        add(label2);
        label2.addMouseListener(this);
        label2.addMouseMotionListener(this);
        add(label8);
        label8.addMouseListener(this);
        label8.addMouseMotionListener(this);
        add(label3);     
        add(label4); 
        add(label5);
        add(label6);
        add(label7);
    }

public void paintComponent(Graphics page) {
        super.paintComponent( page );
        imageIcon.paintIcon(this, page, 0, 0);
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
           if(e.getSource() == label2) {
                System.exit(0);
            }
           else if( e.getSource() == label1 ){
                MainFrame.changeGame();
        
            }
            else if(e.getSource()==label8){
                MainFrame.continueGame();
                
            }
        
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
       if(e.getSource()==label1) label1.setIcon(icon11);
       if(e.getSource()==label2) label2.setIcon(icon21);
       if(e.getSource()==label8) label8.setIcon(icon81);
            
       
    }

    public void mouseExited(MouseEvent e) {
       if(e.getSource()==label1) label1.setIcon(icon10);
       if(e.getSource()==label2) label2.setIcon(icon20);
       if(e.getSource()==label3) label3.setIcon(icon30);
       if(e.getSource()==label4) label4.setIcon(icon40);
       if(e.getSource()==label5) label5.setIcon(icon50);
       if(e.getSource()==label8) label8.setIcon(icon80);
        
    }

    
    
}
