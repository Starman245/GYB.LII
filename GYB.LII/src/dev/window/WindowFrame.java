package dev.window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import dev.managers.MouseManager;

public class WindowFrame {
	
	private Graphics g;
	MouseManager mouseManager;
	
	private int x, y, width, height;
	private Rectangle bounds;

	public WindowFrame(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		bounds = new Rectangle(x, y, width, height);
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.GRAY);
		for (int i = 0; i < 3; i++) {
			g.drawRect(x + i, y + i, width, height - i - 1);
		}
		//g.drawRect(x, y, width, height - 1);
		//g.drawRect(x + 1, y + 1, width, height - 2);
		//g.drawRect(x + 2, y + 2, width, height - 3);
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	public void onMouseRelease(MouseEvent e) {
		
	}

	public void onMouseMove(MouseEvent e) {
		
	}
	
}
