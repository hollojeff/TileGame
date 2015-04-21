package dev.codenmore.tilegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import dev.codenmore.tilegame.display.Display;
import dev.codenmore.tilegame.gfx.ImageLoader;

public class Game implements Runnable{
	
	private Display display;
	
	public int width, height;
	public String title;
	
	private boolean running = false;
	public Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	
	
	public Game(String title, int width, int height){
		this.width = width;
		this.height = height;
		this.title = title;
		
		
			
	}
	
	private void init(){
		display = new Display(title, width, height);
		testImage = ImageLoader.loadImage("/textures/test.jpg");
	}
	
	private void tick(){
		
	}
	
	private void render(){
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Clear screen
		g.clearRect(0,0,width,height);
		//Draw Here!
		

		
		//End Drawing
		bs.show();
		g.dispose();
	}
	
	public void run(){
		
		init();
		
		while(running){
			tick();
			render();
		}
		
		
	}
	
	public synchronized void Start(){
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void Stop(){
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
