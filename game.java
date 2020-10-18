import javax.swing.JFrame;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.Rectangle;
import java.awt.*;
import java.util.Timer;
//import java.util.Timer;
 class game
{
public static void main(String args [])
{
JFrame obj=new JFrame();
obj.setBounds(10,10,700,600);
obj.setTitle("Brick-Breaker");
obj.setVisible(true);
obj.setResizable(true);
Gameplay obj2=new Gameplay();
obj.add(obj2);
}
}
class Gameplay extends JPanel implements KeyListener , ActionListener
{
private boolean play =false;
private int score=0;
private int totalbricks =21;
private Timer timer;
private int delay=8;
private int playerx=310;

private int ballposx=120;;
private int ballposy=350;
private  int balldix=-1;
private int balldiy=-2;
private map m1;
public Gameplay()
{
m1=new map(1,7);
addKeyListener(this);
setFocusable(true);
setFocusTraversalKeysEnabled(false);
timer=new Timer(delay,this);
timer.start();

}
public void paint(Graphics g)
{
g.setColor(Color.black); // background
g.fillRect(1,1,692,592);

m1.draw((Graphics2D)g);
  
g.setColor(Color.red); //border
g.fillRect(0,0,3,592);   //from top left corner to bottom left corner
g.fillRect(0,0,692,3);
g.fillRect(691,0,3,592);

g.setColor(Color.green);
g.fillRect(playerx,550,100,8);

g.setColor(Color.yellow);
g.fillOval(ballposx,ballposy,20,20);

g.dispose();
}
public void actionPerformed(ActionEvent e)
{ 
timer.start();
if(play)
{
if(new Rectangle(ballposx,ballposy,20,20).intersects(new Rectangle(playerx,550,100,8)))
{
balldiy=-balldiy;
}
ballposx+=balldix;
ballposy+=balldiy;
if(ballposx<0)
balldix=-balldix;
if(ballposy<0)
balldiy=-balldiy;
if(ballposx>670)
balldix=-balldix;
}
repaint();
}
public void keyTyped(KeyEvent e){}
public void keyReleased(KeyEvent e){}
public void keyPressed(KeyEvent e)
{
if(e.getKeyCode()==KeyEvent.VK_RIGHT)
{
if(playerx >= 600)
playerx=600;
else
 moveRight();
}
if(e.getKeyCode()==KeyEvent.VK_LEFT)
{
if(playerx <10)
playerx=10;
else
 moveLeft();
}
}
public void moveLeft()
{
play=true;
playerx-=20;
}
public void moveRight()
{
play=true;
playerx+=20;
 }
}
class map
{
public int m[][];
public int brickwidth;
public int brickheight;
public map(int row,int col)
{
m=new int[row][col];
for(int i=0;i<m.length;i++)
{
for(int j=0;j<m[0].length;j++)
{
m[i][j]=1;
}
}
brickwidth=540/col;
brickheight=150/row;
}
public void draw(Graphics g)
{
Graphics2D g1=null;
for(int i=0;i<m.length;i++)
{
for(int j=0;j<m[0].length;j++)
{
if(m[i][j]>1)
{
g.setColor(Color.white);
g.fillRect(j*brickwidth+ 80 ,i*brickheight+50, brickwidth , brickheight);
g1.setStroke(new BasicStroke(3.0f));
g.drawRect(j*brickwidth+ 80 ,i*brickheight+50, brickwidth , brickheight);
}
}
}
}
}





