package objects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.Random;

import framework.GameObject;
import framework.Handler;
import framework.ID;
import input.MouseTrail;
import window.Game;

public class Ball extends GameObject {
	
	private Game game;
	private Handler handler;
	private double gravity = 0.98;
	private double energyloss = .65;
	private double dt = .3;
	private double aci=0;
	private int uzunluk = 100;
	private int i,j = 0;
	private int i1,i2,i3,i4;
	private int j1,j2,j3,j4;
	private int radius;
	private MouseTrail mouseTrail = new MouseTrail(30);
	private Color color;
	private Random r = new Random();
	

	public Ball(float x, float y, ID id,Handler handler,Game game) {
		super(x, y, id);
		this.game = game;
		this.handler = handler;
		radius = width = 30;
		height =30;
		setVelX(r.nextInt(10)+1);
		
	}

	@Override
	public void tick() {
		
		x +=velX;
		y +=velY;
		color = new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255),200);
		
		if(x<0 && velX<0) velX = (float) (-velX -1);
		if(x>game.WIDTH - width - 1&& velX>0) velX = (float) (-velX +2); 

	
		if(y>game.HEIGHT - height -1){
			y = game.HEIGHT - height - 1;
			velY *=energyloss; 
			velY = -velY;
		}else{
			//velocity formul
			velY += gravity*dt;
			//position formul
			y += velY*dt + 0.5*gravity*dt*dt;		
		}
		
		
	
		
		i1 = game.WIDTH/2+ (int) ((int) uzunluk * Math.cos(aci)) ;
		j1 = game.HEIGHT/2-(int) ((int) uzunluk * Math.sin(aci));
		
		i2 = game.WIDTH/2 + (int) ((int) uzunluk * Math.cos(aci + (0.5*Math.PI)));
		j2 = game.HEIGHT/2-(int) ((int) uzunluk * Math.sin(aci + (0.5*Math.PI)));
		
		i3 = game.WIDTH/2+ (int) ((int) uzunluk * Math.cos(aci + (1*Math.PI)));
		j3 = game.HEIGHT/2-(int) ((int) uzunluk * Math.sin(aci + (1*Math.PI)));
		
		i4 = game.WIDTH/2 +(int) ((int) uzunluk * Math.cos(aci + (1.5*Math.PI)));
		j4 = game.HEIGHT/2-(int) ((int) uzunluk * Math.sin(aci + (1.5*Math.PI)));
		

		aci = aci + (0.005*Math.PI);
		
	    if (y > 0) {
            mouseTrail.addTrail((int)x + radius/2, (int)y + radius/2);

        }
        else {
            mouseTrail.standBy();
        }

	}

	@Override
	public void render(Graphics2D g) {
		

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		

		
		g.setColor(color);
		
		
		
		g.fillOval((int)x, (int)y, radius, radius);

		
		g.setStroke(new BasicStroke(5));
		g.drawLine(game.WIDTH/2, game.HEIGHT/2, game.WIDTH/2 - i*2, game.HEIGHT/2 -j*2);
		g.drawLine(game.WIDTH/2, game.HEIGHT/2, game.WIDTH/2 + i*2, game.HEIGHT/2 +j*2);
		
		g.drawLine(i1, j1, i2, j2);
		g.drawLine(i2, j2, i3, j3);
		g.drawLine(i3, j3, i4, j4);
		g.drawLine(i4, j4, i1, j1);
	
		mouseTrail.render(g);
	}
	
	public void collosion(){
		for(int i=0;i<handler.object.size();i++){
			GameObject temp = handler.object.get(i);
			
			float diffX = x - temp.getX();
			float diffY = ( y + radius) - temp.getY();
			
			float distance = (float) Math.sqrt((x - temp.getX())*(x - temp.getX()) + (( y + radius) - temp.getY())*( y + radius) - temp.getY());
			
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, width, height);
	}

}
