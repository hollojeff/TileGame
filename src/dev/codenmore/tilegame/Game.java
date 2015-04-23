package dev.codenmore.tilegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import dev.codenmore.tilegame.display.Display;
import dev.codenmore.tilegame.gfx.Assets;
import dev.codenmore.tilegame.gfx.ImageLoader;
import dev.codenmore.tilegame.gfx.SpriteSheet;

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
		Assets.init();
	}
	
	int x = 0;
	
	private void tick(){
		x += 1;
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
		
		g.drawImage(Assets.grass, x, 10, null);
		
		//End Drawing
		bs.show();
		g.dispose();
	}
	
	public void run(){
		
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime)/ timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if (delta >= 1){
				tick();
				render();
				ticks++;
				delta --;
			}
			if (timer >= 1000000000){
				System.out.println("Ticks and Frame: " + ticks);
				ticks = 0;
				timer = 0;
			}
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
