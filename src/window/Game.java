package window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.Random;

import javax.swing.JFrame;

import framework.Handler;
import framework.ID;
import input.MouseHandler;
import input.MouseTrail;
import objects.Ball;

public class Game extends Canvas implements Runnable{
	
	public  int WIDTH=600,HEIGHT=400;
	private Thread thread;
	private boolean isRunning = false;
	private Handler handler;
	private MouseTrail mouseTrail;
	private Random r = new Random();
	private MouseHandler mouseHandler = new MouseHandler(); 
	
	public Game(){
		setFocusable(true);
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		this.setMaximumSize(new Dimension(WIDTH,HEIGHT));
		this.setMinimumSize(new Dimension(WIDTH,HEIGHT));
	
	}
	
	
	public void init(){
		handler = new Handler();
		mouseTrail = new MouseTrail(30);
		for(int i=0;i<30;i++){
			handler.addObject(new Ball(r.nextInt(550),-10*(r.nextInt(500)), ID.Player,handler,this));
		}

		addMouseListener(mouseHandler);
		addMouseMotionListener(mouseHandler);
		
	}
	
	public void tick(){
		handler.tick();
		
        if (mouseHandler.isPressed()) {
            mouseTrail.addTrail(mouseHandler.getX(), mouseHandler.getY());
        }
        else {
            mouseTrail.standBy();
        }
		
	}
	
	public void render(){
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		mouseTrail.render(g);
		g.setFont(new Font("New Times Roman",Font.BOLD,12));
		g.drawString("Cordinats",mouseHandler.getX() -27 , mouseHandler.getY()-30);
		g.drawString( mouseHandler.getX() + " , " + mouseHandler.getY() ,mouseHandler.getX() -25 , mouseHandler.getY()-10);
		
		g.setFont(new Font("New Times Roman",Font.BOLD,20));
		if(mouseHandler.isButtonLeft()){
			mouseHandler.setButtonMiddle(false);
			mouseHandler.setButtonRight(false);
			g.drawString("Mouse LEFT --- Activated", 40, 50);

			
		}
		if(mouseHandler.isButtonMiddle()){
			mouseHandler.setButtonRight(false);
			mouseHandler.setButtonLeft(false);
			g.drawString("Mouse Middle --- Activated", 40, 50);
			
		}
		if(mouseHandler.isButtonRight() ){
			mouseHandler.setButtonLeft(false);
			mouseHandler.setButtonMiddle(false);
			g.drawString("Mouse Right --- Activated", 40, 50);

		}

		
		
		bs.show();
		g.dispose();
		
	}
	
	public void run(){
		
		init();
		requestFocus();
		int FPS = 60;
		double targetTime = 1000000000/FPS;
		double delta =0;
		
		long lastTime = System.nanoTime();
		long now;
		long timer = System.currentTimeMillis();
		
		int ticks=0;
		int frames = 0;
		
		while(isRunning){
			now = System.nanoTime();
			delta += (now - lastTime)/targetTime;
			lastTime = now;
			
			if(delta>=1){
				tick();
				ticks++;
				delta--;
			}
			
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer >= 1000){
				timer += 1000;
				System.out.println("FPS : " + ticks + " Updates : " + frames);
				ticks =0;
				frames = 0;
			}
		}
		stop();
		
	}
	
	public synchronized void start(){
		if(isRunning)
			return;
		isRunning = true;
		thread = new Thread(this);
		thread.start();
		
	}
	public synchronized void stop(){
		if(!isRunning)
			return;
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args){
		
		JFrame frame = new JFrame();
		Game game = new Game();
		frame.setResizable(false);
		frame.add(game);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		game.start();
		
	}

}
