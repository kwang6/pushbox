/*
 * Readmap.java
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

/**
 *
 * @author 南_2
 */
public class Map {
    private int level,mx,my;
	private int[][] mymap=new int[20][20];
	FileReader r;
	BufferedReader br;String bb="";
	int[] x;int c=0;
        
        static FileWriter fw;
        static BufferedWriter bw;
        static String dd="";
        static int stage=0;
    
    /** Creates a new instance of Readmap */
   Map(int k)
	{
		level=k;
		String s;
		try
		{
			File f=new File("maps\\"+level+".map");
			r=new FileReader(f);
			br=new BufferedReader(r);
		}
		catch (IOException e)
		{
			System.out.println(e);
		}
		try
		{
			while ((s=br.readLine())!=null)
			{
				bb=bb+s;
				
			}
		}
		catch (IOException g)
		{
			System.out.println(g);
		}
		byte[] d=bb.getBytes();
		int len=bb.length();
		int[] x=new int[len];
		for(int i=0;i<bb.length();i++)
                    x[i]=d[i]-48;
		for(int i=0;i<20;i++)
		{
			for(int j=0;j<20;j++)
		    {
				mymap[i][j]=x[c];
		        if(mymap[i][j]==5)
		        {
					mx=j;my=i;
		        }
		        c++;
		    }
	    }
	}
	int[][] getmap(){return mymap;}
	int getmanX(){return mx;}
	int getmanY(){return my;}
        
        static void saveData(int[][] temp,int m) {
        try
        {
                File f=new File("data\\save.dat");
                fw=new FileWriter(f);
                bw=new BufferedWriter(fw);
        }
        catch (IOException e)
        {
                System.out.println(e);
        }
        try
        {
            //wirte the current map
            for(int i=0; i<20; i++)
            {
                dd="";
                for(int j=0; j<20; j++)
                {
                    dd = dd+temp[i][j];
                }
                byte[] d=dd.getBytes();  
                int[] y=new int[dd.length()];
                
                for(int k=0;k<dd.length();k++)
                {
                    y[k]=d[k];
                    bw.write(y[k]);
                }
                bw.newLine();
                
            }
            dd = Integer.toString(m);
            byte[] d=dd.getBytes();  
            int[] y=new int[dd.length()];
            for(int k=0;k<dd.length();k++)
            {
                y[k]=d[k];
                bw.write(y[k]);
            } 
            bw.newLine(); 
           
            bw.flush();
        }
        catch (IOException g)
        {
                System.out.println(g);
        }
        
      
             
    
    }
       int[][] getData(){
      
        String s,s1="";
        try
        {
                File f=new File("data\\save.dat");
                r=new FileReader(f);
                br=new BufferedReader(r);
        }
        catch (IOException e)
        {
                System.out.println(e);
        }
        try
        {
                int lines= 0;
                while ((s=br.readLine())!=null)
                {
                    if(lines<20)
                     bb=bb+s;
                    if(lines == 20)
                        s1 = s;
                    lines ++;
                   
                }
                
        }
        catch (IOException g)
        {
                System.out.println(g);
        }
        byte[] d=bb.getBytes();
        int len=bb.length();
        int[] y=new int[len];
        for(int i=0;i<bb.length();i++)
            y[i]=d[i]-48;
        for(int i=0;i<20;i++)
        {
            for(int j=0;j<20;j++)
            {
                mymap[i][j]=y[c];
                if(mymap[i][j]==5)
                {
                    mx=j;my=i;
                }
                
                c++;
            }
        }
      stage = Integer.parseInt(s1);
        return mymap;
    }
    
}

