/*
 * mainFrame.java
 *
 * Created on 2010/3/30, 12:09
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package keshe;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.BevelBorder;

/**
 *
 * @author kai wang
 */
public class MainFrame extends JFrame implements ActionListener,ItemListener{
     private static JPanel mypanel = new JPanel();
    private static CardLayout card = new CardLayout();
    private static FengmianPanel menupanel = new FengmianPanel();
    private static ButtonPanel game = new ButtonPanel();
    private static PlayPanel panel = new PlayPanel();
     private static Sound sound = new Sound();


          MenuBar bar = new MenuBar();

    Menu choice1 =new Menu("File");
    Menu choice2 =new Menu("option");
    Menu choice3 =new Menu("help");
    
    MenuItem item11 = new MenuItem("restart");
    MenuItem item12 = new MenuItem("back");
    MenuItem item15 = new MenuItem("recover");
    MenuItem item13 = new MenuItem("select");
    MenuItem item14 = new MenuItem("quit");
    
    Menu item21 = new Menu("backsound choose");
    Menu item22 = new Menu("backsound on/off");
    MenuItem item211 = new MenuItem("as long as you love me");
    MenuItem item212 = new MenuItem("pretty boy");
    MenuItem item213 = new MenuItem("alway slient");
    MenuItem item221 = new MenuItem("off");
    MenuItem item222 = new MenuItem("on");
   
    
    MenuItem item31 = new MenuItem("rules");
    MenuItem item32 = new MenuItem("about");    
   
        
    
    /** Creates a new instance of mainFrame */
    public MainFrame() {
        super("warehouse keeper");
        setMenuBar(bar);//menu
        
      choice1.add(item11);//menu
      choice1.add(item12);
      choice1.add(item15);
      choice1.add(item13);
      choice1.add(item14);
     
      
      item21.add(item211);
      item21.add(item212);
      item21.add(item213);
      item22.add(item221);
      item22.add(item222);
      item222.setEnabled(false);
      choice2.add(item21);
      choice2.add(item22);
      
      choice3.add(item31);
      choice3.add(item32);
        
      bar.add(choice1);
      bar.add(choice2);
      bar.add(choice3);
      
        
   //   choice1.setEnabled(false);
//      item12.setEnabled(false);
//      item13.setEnabled(false);
      
        
        JLabel   label_1   =   new   JLabel();   //Status Bar
        label_1.setBorder(new   BevelBorder(BevelBorder.LOWERED));   
        
        label_1.setText("author: Kai Wang ");
       
    
        this.add(label_1);
        label_1.setBounds(0,600,600,20);


        mypanel.setLayout(card);
        mypanel.setPreferredSize(new Dimension(600,620));
        mypanel.add(menupanel,"panel1");
        mypanel.add(game,"panel2");
        mypanel.setFocusable(true);
        
        
        
        getContentPane().add( mypanel );
        setVisible(true);
        setResizable(false);
        setLocation(20,20);
        setBackground(Color.pink);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        
         sound.loadSound();
         item11.addActionListener(this);
        item12.addActionListener(this);
        item13.addActionListener(this);
        item14.addActionListener(this);
        item15.addActionListener(this);
        item211.addActionListener(this);
        item212.addActionListener(this);
        item213.addActionListener(this);
        item221.addActionListener(this);
        item222.addActionListener(this);
        item32.addActionListener(this);
        item31.addActionListener(this);
   
   
  
        
    }
   static   public void changeGame() {
        card.show(mypanel, "panel2");
        
        mypanel.addKeyListener(game.map);
    }
    static public void changeFengmian() {
        card.show(mypanel, "panel1");
    }   
    static public void continueGame() {
         card.show(mypanel, "panel2");
         panel.playsave();
        
        mypanel.addKeyListener(game.map);
    }
    
public void actionPerformed(ActionEvent e){
   
   if(e.getSource()==item14)//quit
    {
       System.exit(0);
    }
    else if(e.getSource()==item13)//choose the level
		{
			String lel=JOptionPane.showInputDialog(this,"please enter the level you want to play:(1~10)");
			
			game.map.level=Integer.parseInt(lel);
			game.map.remove();
                        game.map.rebackremove();
			if(game.map.level>game.map.maxlevel()||game.map.level<1)
			{JOptionPane.showMessageDialog(this, "no such level!");game.map.requestFocus();}
			else
				{
				game.map.play(game.map.level);
				game.map.requestFocus();
				}
		}
    else if(e.getSource()==item12)//backout
		{
			if(game.map.isMystackEmpty())JOptionPane.showMessageDialog(this, "This is the beginning place!");
			else
			{
				switch(game.map.back())
				{
					case 10:game.map.backup(10);game.map.pushhf(1);break;
					case 11:game.map.backup(11);game.map.pushhf(1);break;
					case 20:game.map.backdown(20);game.map.pushhf(2);break;
					case 21:game.map.backdown(21);game.map.pushhf(2);break;
					case 30:game.map.backleft(30);game.map.pushhf(3);break;
					case 31:game.map.backleft(31);game.map.pushhf(3);break;
					case 40:game.map.backright(40);game.map.pushhf(4);break;
					case 41:game.map.backright(41);game.map.pushhf(4);break;
                                        case 5: game.map.mouseback();game.map.pushhf(5);break;
				}
			}
			game.map.requestFocus();
		}
    else if(e.getSource()==item15)//恢复一步
		{
			if(game.map.isHftackEmpty())JOptionPane.showMessageDialog(this, "Sorry,you haven't backout");
			else
			{
				switch(game.map.reback())
				{
					case 1:game.map.moveup();break;
					case 2:game.map.movedown();break;
					case 3:game.map.moveleft();break;
					case 4:game.map.moveright();break;
					 case 5:game.map.remouse();break;
				}
			}
			game.map.requestFocus();
		}
    else if (e.getSource()==item11)
    {
                        panel.play(panel.level);
			panel.requestFocus();
			panel.remove();
    }
    else if(e.getSource()==item221)//music off
		{
		      sound.mystop();
                      item221.setEnabled(false);
                      item222.setEnabled(true);
                      item212.setEnabled(false);
                      item211.setEnabled(false);
			
		}
    else if(e.getSource()==item222)//music on
               {
                  
                      sound.loadSound();
                      item222.setEnabled(false);
                      item221.setEnabled(true);
                      item212.setEnabled(true);
                      item211.setEnabled(true);
                    
                }
   else if(e.getSource()==item211)
		{
			sound.setMusic("as long as you love me.mid");
                        sound.mystop();
		        sound.loadSound();

		}
   else if(e.getSource()==item212)
		{
			sound.setMusic("Pretty Boy.mid");
                        sound.mystop();
		        sound.loadSound();
		}
   else if(e.getSource()==item213)
		{
			sound.setMusic("一直很安静.mid");
                        sound.mystop();
		        sound.loadSound();
		}
    else if(e.getSource()==item32)//关于
   {
			JOptionPane.showMessageDialog(this, "Push Box\nauthor: Kai Wang\nEmail: wk88520@gmail.com\n");
		}
    else if(e.getSource()==item31)//规则
    {
        JOptionPane.showMessageDialog(this,"游戏的目：把所有的箱子都推到目标位置上。\n游戏玩法：通过鼠标键盘推动箱子，不能拉动，一次只能推动一个箱子。\n游戏难度：按难度不同分不同级别，每进入下一关，难度就适当增加。 ");
    }
    }

    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

  
}


