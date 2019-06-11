package dev.ws;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.display.Display;
import dev.gfx.Gfx;
import dev.gfx.UIManager;
import dev.managers.KeyManager;
import dev.managers.MouseManager;
import dev.window.WindowFrame;

public class Workstation implements Runnable {
	
	int width;
	int height;
	String title;
	
	Display display;
	Thread thread;
	private boolean running = false;
	
	KeyManager keyManager;
	MouseManager mouseManager;
	UIManager uiManager;
	WindowFrame windowFrame;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private Gfx fx;
	
	public Workstation(int width, int height, String title) {
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
		uiManager = new UIManager();
		windowFrame = new WindowFrame(0, 0, 640, height);
	}

	private void init() {
		display = new Display(width, height, title);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		
		fx = new Gfx(uiManager, windowFrame, mouseManager, width, height);
	}
	
	private void tick() {
		keyManager.tick();
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Clear Screen
		g.clearRect(0, 0, width, height);
		//Draw Here!
		
		fx.render(g);
		
		//End Drawing!
		bs.show();
		g.dispose();
	}
	
	@Override
	public void run() {
		init();
		
		int fps = 30;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1){
				tick();
				render();
				ticks++;
				delta--;
			}
			
			if(timer >= 1000000000){
				System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();
	}
	
	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
