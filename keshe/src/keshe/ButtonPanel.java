/*
 * ButtonPanel.java
 *
 * Created on 2010/3/30, 12:09
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package keshe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.BevelBorder;
/**
 *
 * @author kai wang
 */
public class ButtonPanel extends JPanel implements ActionListener{
    
    /** Creates a new instance of ButtonPanel */
    static PlayPanel map = new PlayPanel();
    JLabel lb;
    JButton btrenew,btlast,btnext,btchoose,btmuc,btback,btquit,btsave;
    
    public ButtonPanel() {
        setBackground(Color.pink);
        setPreferredSize(new Dimension(500,600));
        setLayout(null);
 
       
        lb=new JLabel("author: Kai Wang ");
         lb.setBorder(new   BevelBorder(BevelBorder.LOWERED));   
         add(lb);
        lb.setBounds(0,600,600,20);
 	
        add(map);
        map.play(map.level);
        map.requestFocus();
        btrenew=new JButton("again ");
        btrenew.addActionListener(this);
        btrenew.setBounds(56,530,80,30);
        btback=new JButton("Back");
        btback.addActionListener(this);
	btback.setBounds(192,530,80,30);
        btlast=new JButton("Last Lv");
        btlast.addActionListener(this);
	btlast.setBounds(328,530,80,30);
        btnext=new JButton("Next Lv");
        btnext.addActionListener(this);
	btnext.setBounds(464,530,80,30);
        btchoose=new JButton("select");
        btchoose.addActionListener(this);
	btchoose.setBounds(56,570,80,30);
        btmuc=new JButton("Recover");
        btmuc.addActionListener(this);
        btmuc.setBounds(192,570,80,30);
        btsave=new JButton("Save");
        btsave.setBounds(328,570,80,30);       
        btsave.addActionListener(this);
        btquit = new JButton("main");
        btquit.setBounds(464,570,80,30);
        btquit.addActionListener(this);
        
	add(btrenew);
        add(btlast);
        add(btnext);
        add(btchoose);
        add(btmuc);
        add(btback);
        add(btsave);
        add(btquit);
	
                
        add(map);
        map.setBounds(0, -70, 600, 600);
        map.play(map.level);
        map.requestFocus();
	validate();
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btrenew)//start again
		{
			map.play(map.level);
			map.requestFocus();
			map.remove();
                        map.rebackremove();
		}
		else if(e.getSource()==btlast)//last level
		{
			map.level--;
			if(map.level<1)
			{map.level++;JOptionPane.showMessageDialog(this,"this the first level!");map.requestFocus();}
			else 
			{
				map.play(map.level);
				map.requestFocus();
			}
			map.remove();
                        map.rebackremove();
		}
		else if(e.getSource()==btnext)//next level
		{
			map.level++;
			if(map.level>map.maxlevel())
			{map.level--;JOptionPane.showMessageDialog(this,"this the last level!");map.requestFocus();}
			else 
			{
				map.play(map.level);
				map.requestFocus();
			}
			map.remove();
                        map.rebackremove();
		}
               
		
		
		else if(e.getSource()==btchoose)//choose level
		{
			String lel=JOptionPane.showInputDialog(this,"Enter the level you want to play(1~10)");
			map.level=Integer.parseInt(lel);
			map.remove();
                        map.rebackremove();
			if(map.level>map.maxlevel()||map.level<1)
			{JOptionPane.showMessageDialog(this, "sorry,this is no that level!");map.requestFocus();}
			else
				{
				map.play(map.level);
				map.requestFocus();
				}
		}
		
		
		
		else if(e.getSource()==btback)//Backout
		{
			if(map.isMystackEmpty())JOptionPane.showMessageDialog(this, "This is the beginning place!");
			else
			{
				switch(map.back())
				{
					case 10:map.backup(10);map.pushhf(1);break;
					case 11:map.backup(11);map.pushhf(1);break;
					case 20:map.backdown(20);map.pushhf(2);break;
					case 21:map.backdown(21);map.pushhf(2);break;
					case 30:map.backleft(30);map.pushhf(3);break;
					case 31:map.backleft(31);map.pushhf(3);break;
					case 40:map.backright(40);map.pushhf(4);break;
					case 41:map.backright(41);map.pushhf(4);break;
                                        case 5: map.mouseback();map.pushhf(5);break;
 				}
			}
			map.requestFocus();
		}
        else if(e.getSource()==btmuc)//recover
		{
			if(map.isHftackEmpty())JOptionPane.showMessageDialog(this, "This is the beginning backout place!");
		       else
			{
				switch(map.reback())
				{
					case 1:map.moveup();break;
					case 2:map.movedown();break;
					case 3:map.moveleft();break;
					case 4:map.moveright();break;
                                         case 5:map.remouse();break;
				}
			}
			map.requestFocus();
		}
        else if(e.getSource()==btquit)// back to the front page
        {
            MainFrame.changeFengmian();
            
        }
        else if(e.getSource()==btsave)//save the level
        {
           Map.saveData(map.map,map.level);
        }
		
    }
    
}
