/*
 * PlayPanel.java
 *
 * Created on 2010/3/30, 12:09
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package keshe;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.sound.midi.*;
import java.util.Stack;
/**
 *
 * @author kai wang
 */
public class PlayPanel extends JPanel implements KeyListener ,MouseListener{
    
       int max=10;//count of maps
       JLabel label_1 = new JLabel();
	int[][] map,maptmp;
  
	int manX,manY,boxnum,x,y,endX,endY;
	Image[] myImage;
	Map Levelmap;
	Map Levelmaptmp;
	int len=30;
	public int level=1,m=0;

	Stack mystack=new Stack();
        Stack hfstack = new Stack();
        Stack mousestack = new Stack();
        Stack remousestack = new Stack();
       
     
        Stack bt = new Stack<PosType>();
        Stack<SElemType> djfx = new Stack();
        Point spot = new Point();

 
         
       
    /** Creates a new instance of mainPanel */
     public PlayPanel()
    {
        
		setBounds(0,0,600,600);
	
		addKeyListener(this);
                addMouseListener(this);
		//first 10 pictures
		myImage=new Image[10];
		for(int i=0; i<10; i++)
		{
		    myImage[i] = Toolkit.getDefaultToolkit().getImage("pic\\"+i+".gif");
		}
		
		setVisible(true);
                JLabel   label_1   =   new   JLabel();   // status bar
   
        
        label_1.setText("author: Kai Wang ");
       
     
//        FM.add("",background);
        add(label_1);
        label_1.setBounds(0,600,500,20);
    }
    void play(int i)//游戏
	{
		Levelmap=new Map(i);
		Levelmaptmp=new Map(i);
		map=Levelmap.getmap();
		manX=Levelmap.getmanX();
		manY=Levelmap.getmanY();
		maptmp=Levelmaptmp.getmap();
		repaint();
                m=0;
	}
	int maxlevel()
        {return max;}

	public void paint(Graphics g)//draw pictures
	{  
            int n =100-m;
		for(int i=0; i<20; i++)
			for(int j=0; j<20; j++)
		    {
			    g.drawImage(myImage[map[j][i]],i*len,j*len,this);
                            
			}		
		g.setColor(new Color(0,0,0));
		g.setFont(new Font("2312",Font.BOLD,30));
		g.drawString("Level",0,110);
		g.drawString(String.valueOf(level),80,110);
                g.drawString("Steps",400,110);
                g.drawString(String.valueOf(m),490,110);
                g.drawString("Scores",220,110);
                g.drawString(String.valueOf(n),320,110);
	}

	public void keyPressed(KeyEvent e)//keybroad
	{
		if(e.getKeyCode()==KeyEvent.VK_UP){moveup();m++;hfstack.removeAllElements();
                remousestack.removeAllElements();}
		if(e.getKeyCode()==KeyEvent.VK_DOWN){movedown();m++;hfstack.removeAllElements();
                remousestack.removeAllElements();}
		if(e.getKeyCode()==KeyEvent.VK_LEFT){moveleft();m++;hfstack.removeAllElements();
                remousestack.removeAllElements();}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT){moveright();m++;hfstack.removeAllElements();
                remousestack.removeAllElements();}
		if(iswin())
		{
			if(level==max){JOptionPane.showMessageDialog(this, "congratulation!you win!");}
			else
			{
				String msg="congratulation! you won level "+level+"!! Do you want to play next level?";
				int type=JOptionPane.YES_NO_OPTION;
				String title="Level Pass!";
				int choice=0;
				choice=JOptionPane.showConfirmDialog(null,msg,title,type);
				if(choice==1)System.exit(0);
				else if(choice==0)
				{
					level++;
					play(level);
                                        m=0;
				}
			}
			mystack.removeAllElements();
                        hfstack.removeAllElements();
                        mousestack.removeAllElements();
                        remousestack.removeAllElements();
		}
	}
        public void mousePressed(MouseEvent e) //mouse event
        {           
            
              spot=e.getPoint();
          
              endX = spot.x/30;
              endY = spot.y/30;
              m++;
       
           if(map[endY][endX]==3||map[endY][endX]==9)//click the box, make sure if the people beside the box
           {
              // if((map[endY][endX+1]==5)||)   
               
               if(map[endY][endX+1]==5||map[endY][endX+1]==6||map[endY][endX+1]==7||map[endY][endX+1]==8)//people in the right side of the box
                   moveleft();
               else if(map[endY][endX-1]==5||map[endY][endX-1]==6||map[endY][endX-1]==7||map[endY][endX-1]==8)//people in the left side of the box
                   moveright();
               else if(map[endY-1][endX]==5||map[endY-1][endX]==6||map[endY-1][endX]==7||map[endY-1][endX]==8)//people in the upper side of the box
                   movedown();
               else if(map[endY+1][endX]==5||map[endY+1][endX]==6||map[endY+1][endX]==7||map[endY+1][endX]==8)//people in the down side of the box
                   moveup();
               else 
                   JOptionPane.showMessageDialog(this, "if you wanna push the box, you must beside the box!");
           }
         
           else  if(map[endY][endX]==5)//click himself
               {  map[endY][endX]=5;m--;
                   repaint();}
           else  if(map[endY][endX]==6)//click himself
               {  map[endY][endX]=6;m--;
                   repaint();}
           else  if(map[endY][endX]==7)//click himself
               {  map[endY][endX]=7;m--;
                   repaint();}
           else  if(map[endY][endX]==8)//click himself
               {  map[endY][endX]=8;m--;
                   repaint();}
              
          else    if(map[endY][endX]==2||map[endY][endX]==4)   
           {
              if(through()==true)//have way to pass
        
             {
                    Point po = new Point();
                    po.x=manX;po.y=manY;
                   mystack.push(5);
                   mousestack.push(po);
                  map[endY][endX] = 5;
                 if(maptmp[manY][manX]==4)
                     map[manY][manX]=4;
                
                 
                 else 
                     map[manY][manX]=2;
                
                 
                  repaint();
                   manX = spot.x/30;
                   manY = spot.y/30;
                 djfx.removeAllElements();
                 bt.removeAllElements();
                 
          }
               
          else//no way to pass
          {    JOptionPane.showMessageDialog(this, "sorry，the is not a way to pass");
                djfx.removeAllElements();
                bt.removeAllElements();
            }
           }
             
          if(iswin())//make sure you win
		{
			if(level==max){JOptionPane.showMessageDialog(this, "congratulation!you win!");}
			else
			{
				String msg="congratulation! you won level "+level+"!! Do you want to play next level?";
				int type=JOptionPane.YES_NO_OPTION;
				String title="Level Pass!";
				int choice=0;
				choice=JOptionPane.showConfirmDialog(null,msg,title,type);
				if(choice==1)System.exit(0);
				else if(choice==0)
				{
					level++;
					play(level);
                                        m=0;
				}
			}
			mystack.removeAllElements();
		}
          }
        
            
        

	
	boolean isMystackEmpty(){return mystack.isEmpty();}//make sure the backout stack is empty
	boolean isHftackEmpty(){return hfstack.isEmpty();}//make sure the recover stack is empty
        
	int  back(){return (Integer)mystack.pop();}//return the backout stack
        int  reback(){return (Integer)hfstack.pop();}//return the recover stack
        
	void remove(){mystack.removeAllElements();}//empty the backout stack
        void rebackremove(){hfstack.removeAllElements();}//empty the recover stack
        
          void pushhf(int i){hfstack.push(i);}
         void mouseback(){
             Point po=(Point)mousestack.pop();
           
            
            remousestack.push(new Point(manX,manY));
            map[po.y][po.x] = map[manY][manX];
            if(maptmp[manY][manX]==4) map[manY][manX]=4;
            else map[manY][manX]=2;
            repaint();
            manX=po.x;
            manY=po.y;
        }
	void remouse(){
           
            Point po = (Point)remousestack.pop();
            mousestack.push(new Point(manX,manY));
            mystack.push(5);
            map[po.y][po.x] = map[manY][manX];
            if(maptmp[manY][manX]==4) map[manY][manX]=4;
            else map[manY][manX]=2;
            repaint();
            manX=po.x;
            manY=po.y;
         }    
	//1wall2groud3the prepared to push box4ball5people9the right box
	void moveup()//moveup
	{
		if(map[manY-1][manX]==2||map[manY-1][manX]==4)//make sure no obstacles
		{
			if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
				map[manY][manX]=4;
			else map[manY][manX]=2;
			       map[manY-1][manX]=8;
			repaint();manY--;mystack.push(10);
		}
		else if(map[manY-1][manX]==3)//make sure their is box
		{
                    if((map[manY-2][manX]==2&&map[manY-2][manX-1]==1&&map[manY-3][manX]==1)||(map[manY-2][manX]==2&&map[manY-2][manX+1]==1&&map[manY-3][manX]==1))
                    {
                        JOptionPane.showMessageDialog(this, "if take the box to the wall angle  there is no way out!");
                         
                    }
                     if(map[manY-2][manX]==1)
                    {
                       m--;
                        
                    }
                    if(map[manY-2][manX]==9)
                    {
                       m--;
                        
                    }
                       if(map[manY-2][manX]==4)
			{
				if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
					map[manY][manX]=4;
				else map[manY][manX]=2;
				map[manY-1][manX]=8;
				map[manY-2][manX]=9;
				repaint();manY--;mystack.push(11);
			}
			else if(map[manY-2][manX]==2)
			{
				if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
					map[manY][manX]=4;
				else map[manY][manX]=2;
				map[manY-1][manX]=8;
				map[manY-2][manX]=3;
				repaint();manY--;mystack.push(11);
			}
			else {map[manY][manX]=8;repaint();}
		}
		else if(map[manY-1][manX]==9)//if the box is in the right place
		{     
                         if(map[manY-2][manX]==1)
                    {
                       m--;
                        
                    }
			if(map[manY-2][manX]==4)
			{
                            
				if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
					map[manY][manX]=4;
				else map[manY][manX]=2;
				map[manY-1][manX]=8;
				map[manY-2][manX]=9;
				repaint();manY--;mystack.push(11);
			}
			else if(map[manY-2][manX]==2)
			{
				if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
					map[manY][manX]=4;
				else map[manY][manX]=2;
				map[manY-1][manX]=8;
				map[manY-2][manX]=3;
				repaint();manY--;mystack.push(11);
			}
                         else if(map[manY-2][manX]==9)
                             m--;
			else {map[manY][manX]=8;repaint();}
		}
		if(map[manY-1][manX]==1)
		{
			map[manY][manX]=8;
                        repaint();
                        m--;
		}
                
	}
	
	void backup(int t)//upper side back
	{
		int n=t;
		if(n==10)//１means the before step is up,０means before have't move the box
		{
			if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
			{
				map[manY][manX]=4;
			}
			else map[manY][manX]=2;
		}
		else if(n==11)//１means the before step is up,１means before move the box
		{
			if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
			{
				map[manY][manX]=9;
			}
			else map[manY][manX]=3;
			if(maptmp[manY-1][manX]==4||maptmp[manY-1][manX]==9)
			{
				map[manY-1][manX]=4;
			}
			else map[manY-1][manX]=2;
		}
		map[manY+1][manX]=8;
                
		repaint();
                m++;
                manY++;
	}

	void movedown()
	{
            
		if(map[manY+1][manX]==2||map[manY+1][manX]==4)
		{
			if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
				map[manY][manX]=4;
			else map[manY][manX]=2;
			map[manY+1][manX]=5;
			repaint();manY++;mystack.push(20);
		}
		else if(map[manY+1][manX]==3)
		{
                 if((map[manY+2][manX]==2&&map[manY+2][manX-1]==1&&map[manY+3][manX]==1)||(map[manY+2][manX]==2&&map[manY+2][manX+1]==1&&map[manY+3][manX]==1))
                    {
                        JOptionPane.showMessageDialog(this, "if take the box to the wall angle  there is no way out!");
                         
                    }
                     if(map[manY+2][manX]==1)
                    {
                       m--;
                        
                    }
                   if(map[manY+2][manX]==9)
                    {
                       m--;
                        
                    }
			if(map[manY+2][manX]==4)
			{
				if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
					map[manY][manX]=4;
				else map[manY][manX]=2;
				map[manY+1][manX]=5;
				map[manY+2][manX]=9;
				repaint();manY++;mystack.push(21);
			}
			else if(map[manY+2][manX]==2)
			{
				if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
					map[manY][manX]=4;
				else map[manY][manX]=2;
				map[manY+1][manX]=5;
				map[manY+2][manX]=3;
				repaint();manY++;mystack.push(21);
			}
                    else if(map[manY+2][manX]==9)
                                m--;
			else {map[manY][manX]=5;repaint();}
		}
		else if(map[manY+1][manX]==9)
		{
                      m--;
			if(map[manY+2][manX]==4)
			{
				if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
					map[manY][manX]=4;
				else map[manY][manX]=2;
				map[manY+1][manX]=5;
				map[manY+2][manX]=9;
				repaint();manY++;mystack.push(21);
			}
			else if(map[manY+2][manX]==2)
			{
				if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
					map[manY][manX]=4;
				else map[manY][manX]=2;
				map[manY+1][manX]=5;
				map[manY+2][manX]=3;
				repaint();manY++;mystack.push(21);
			}
			else {map[manY][manX]=5;repaint();}
		}
		else if(map[manY+1][manX]==1)
		{
			map[manY][manX]=5;
                        m--;
                        repaint();
		}
	}

	void backdown(int t)
	{
		int n=t;
		if(n==20)
		{
			if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
			{
				map[manY][manX]=4;
			}
			else map[manY][manX]=2;
		}
		else if(n==21)
		{
			if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
			{
				map[manY][manX]=9;
			}
			else map[manY][manX]=3;
			if(maptmp[manY+1][manX]==4||maptmp[manY+1][manX]==9)
			{
				map[manY+1][manX]=4;
			}
			else map[manY+1][manX]=2;
		}
		map[manY-1][manX]=5;
                m++;
		repaint();manY--;
	}

	void moveleft()
	{
		if(map[manY][manX-1]==2||map[manY][manX-1]==4)
		{
			if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
				map[manY][manX]=4;
			else map[manY][manX]=2;
			map[manY][manX-1]=6;			
			repaint();manX--;mystack.push(30);
		}
		else if(map[manY][manX-1]==3)
		{
                     if((map[manY][manX-2]==2&&map[manY-1][manX-2]==1&&map[manY][manX-3]==1)||(map[manY][manX-2]==2&&map[manY+1][manX-2]==1&&map[manY][manX-3]==1))
                    {
                        JOptionPane.showMessageDialog(this, "if take the box to the wall angle  there is no way out!");
                         
                    }
                     if(map[manY][manX-2]==1)
                    {
                       m--;
                        
                    }
                    if(map[manY][manX-2]==9)
                    {
                       m--;
                        
                    }
			if(map[manY][manX-2]==4)
			{
				if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
					map[manY][manX]=4;
				else map[manY][manX]=2;
				map[manY][manX-1]=6;
				map[manY][manX-2]=9;
				repaint();manX--;mystack.push(31);
			}
			else if(map[manY][manX-2]==2)
			{
				if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
					map[manY][manX]=4;
				else map[manY][manX]=2;
				map[manY][manX-1]=6;
				map[manY][manX-2]=3;
				repaint();manX--;mystack.push(31);
			}
                     else if(map[manY][manX-2]==9)
                         m--;
			else {map[manY][manX]=6;repaint();}
		}
		else if(map[manY][manX-1]==9)
		{
                        if(map[manY][manX-2]==1)
                    {
                       m--;
                        
                    }
			if(map[manY][manX-2]==4)
			{
				if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
					map[manY][manX]=4;
				else map[manY][manX]=2;
				map[manY][manX-1]=6;
				map[manY][manX-2]=9;
				repaint();manX--;mystack.push(31);
			}
			else if(map[manY][manX-2]==2)
			{
				if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
					map[manY][manX]=4;
				else map[manY][manX]=2;
				map[manY][manX-1]=6;
				map[manY][manX-2]=3;
				repaint();manX--;mystack.push(31);
			}
			else {map[manY][manX]=6;repaint();}
		}
		else if(map[manY][manX-1]==1)
		{
			map[manY][manX]=6;
                        m--;
                        repaint();
		}
	}

	void backleft(int t)
	{
		int n=t;
		if(n==30)
		{
			if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
			{
				map[manY][manX]=4;
			}
			else map[manY][manX]=2;
		}
		else if(n==31)
		{
			if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
			{
				map[manY][manX]=9;
			}
			else map[manY][manX]=3;
			if(maptmp[manY][manX-1]==4||maptmp[manY][manX-1]==9)
			{
				map[manY][manX-1]=4;
			}
			else map[manY][manX-1]=2;
		}
		map[manY][manX+1]=6;
                m++;
		repaint();manX++;
	}

	void moveright()
	{
		if(map[manY][manX+1]==2||map[manY][manX+1]==4)
		{			
			if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
				map[manY][manX]=4;
			else map[manY][manX]=2;
			map[manY][manX+1]=7;			
			repaint();manX++;mystack.push(40);
		}
		else if(map[manY][manX+1]==3)
		{
                      if((map[manY][manX+2]==2&&map[manY-1][manX+2]==1&&map[manY][manX+3]==1)||(map[manY][manX+2]==2&&map[manY+1][manX+2]==1&&map[manY][manX+3]==1))
                    {
                        JOptionPane.showMessageDialog(this, "if take the box to the wall angle  there is no way out!");
                         
                    }
                     if(map[manY][manX+2]==1)
                    {
                       m--;
                        
                    }
                    if(map[manY][manX+2]==9)
                    {
                       m--;
                        
                    }  
			if(map[manY][manX+2]==4)
			{
				if(maptmp[manY][manX]==4)
					map[manY][manX]=4;
				else map[manY][manX]=2;
				map[manY][manX+1]=7;
				map[manY][manX+2]=9;
				repaint();manX++;mystack.push(41);
			}
			else if(map[manY][manX+2]==2)
			{
				if(maptmp[manY][manX]==4)
					map[manY][manX]=4;
				else map[manY][manX]=2;
				map[manY][manX+1]=7;
				map[manY][manX+2]=3;
				repaint();manX++;mystack.push(41);
			}
			else {map[manY][manX]=7;repaint();}
		}
		else if(map[manY][manX+1]==9)
		{
                       if(map[manY][manX+2]==1)
                    {
                       m--;
                        
                    }
			if(map[manY][manX+2]==4)
			{
				if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
					map[manY][manX]=4;
				else map[manY][manX]=2;
				map[manY][manX+1]=7;
				map[manY][manX+2]=9;
				repaint();manX++;mystack.push(41);
			}
			else if(map[manY][manX+2]==2)
			{
				if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
					map[manY][manX]=4;
				else map[manY][manX]=2;
				map[manY][manX+1]=7;
				map[manY][manX+2]=3;
				repaint();manX++;mystack.push(41);
			}
                       else if(map[manY][manX+2]==9)
                           m--;
			else {map[manY][manX]=7;repaint();}
		}
		else if(map[manY][manX+1]==1)
		{
			map[manY][manX]=7;
                        m--;
                        repaint();
		}
	}

	void backright(int t)
	{
		int n=t;
		if(n==40)
		{
			if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
			{
				map[manY][manX]=4;
			}
			else map[manY][manX]=2;
		}
		else if(n==41)
		{
			if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
			{
				map[manY][manX]=9;
			}
			else map[manY][manX]=3;
			if(maptmp[manY][manX+1]==4||maptmp[manY][manX+1]==9)
			{
				map[manY][manX+1]=4;
			}
			else map[manY][manX+1]=2;
		}
		map[manY][manX-1]=7;
                m++;
		repaint();manX--;
	}
           
       

        boolean through()
        
        {  
             
            PosType curpos = new PosType();
            PosType cc = new PosType();          
            cc.i=0;
            cc.j=0;
            curpos.i=manX;
            curpos.j=manY;
                      
             bt.push(cc);
                           
            do{ 
          
                
                SElemType e = new SElemType(curpos,1);
           
             
                   if(map[endY][endX]==5)
                        return true;
                  if(  (pass1(curpos)==1)&&(pass2(bt,curpos)==1))
                    {
                      
                       djfx.push(e);                 
                         
                       bt.push(e.seat);  //pass  
                       
                                 //System.out.print(bt+"\n");
                                           
                        if(curpos.i==endX&&curpos.j==endY)
                            return true;
                                   
                            curpos = NextPos(e.seat,e.di);
                      
                         
                    }
                     
                                          
                  else{                     
                        //n++;
                    // System.out.print(djfx+"\n");
                     if(!djfx.empty()){
                            e=djfx.pop();
                        while(e.di==4&&(!djfx.empty())){
                              bt.push(e.seat);
                              e=djfx.pop();
                               }
                       if(e.di<4){
                            e.di++;
                            djfx.push(e);
                            cc=curpos;
                            curpos = NextPos(e.seat,e.di);
                            
                              }
                        
                            }
                     }
                    
                }while(!djfx.empty());
            return false;
            }
        
      int pass1(PosType curpos)
      {
          switch(map[curpos.j][curpos.i])
          {
              case 0:return 0;
              case 1:return 0;
              case 2:return 1;
              case 3:return 0;
              case 4:return 1;
              case 5:return 1;
              case 6:return 1;
              case 7:return 1;
              case 8:return 1;
              case 9:return 0;
                  
          }
          return 0;
      }
           
              
      int pass2(Stack <PosType>bt,PosType curpos)
     {
       int length =bt.size();
     //  System.out.print(length); 
      //System.out.print(curpos.i+"\n");
    //  System.out.print("o"+"\n");
       for(int t=0;t<length;t++)
       {
               //   System.out.print(bt.get(t).i+"\n");  
              //    System.out.print(curpos.i+"\n");  
               //    System.out.print("k"+"\n");
           if((bt.get(t).getI()==curpos.i)&&(bt.get(t).getJ()==curpos.j))    
           {
            
               return 0;
           } 
       }  
       return 1;
      }

    public PosType NextPos(PosType seat, int di){
        int i=0 ;
        int j=0 ;
        PosType nextSeat = new PosType();
        
        switch(di){
            case 1:{
                i = (seat.i)+1;
                j = seat.j;
                nextSeat = new PosType( i, j);
              
                break;
            }
            case 2:{
                i = seat.i;
                j = seat.j+1;
                nextSeat = new PosType( i, j);
                break;
            }
            case 3:{
                i = seat.i-1;
                j = seat.j;
                
                nextSeat = new PosType( i, j);
                break;
            }
            case 4:{
                i = seat.i;
                j = seat.j-1;
                nextSeat = new PosType( i, j);
                break;
            }
        }
        nextSeat.i=i;
        nextSeat.j=j;
   //     System.out.print(nextSeat.i+"\n");
            return nextSeat; 
     
    }
 	boolean iswin()//whether win
	{
		boolean num=false;
		out:for(int i=0; i<20; i++)
			for(int j=0; j<20; j++)
		{
			if(maptmp[i][j]==4||maptmp[i][j]==9)
				if(map[i][j]==9)num=true;
			    else {num=false;break out;}
		}
		return num;
	}
      
    public void mouseClicked(MouseEvent e) {
    }

 
    public void mouseReleased(MouseEvent e) {
       
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
    public void keyTyped(KeyEvent e){}
    public void keyReleased(KeyEvent e){}
    void playsave()
     
	{
               
		Levelmap=new Map(1);
		Levelmaptmp=new Map(1);
		map=Levelmap.getData();
                maptmp=Levelmaptmp.getmap();
                
		manX=Levelmap.getmanX();
		manY=Levelmap.getmanY();
                //PlayPanel..setText("stage:"+   m);
               // level=m;
		repaint();
	}
      
    
}

