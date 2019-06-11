package dev.gfx;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import dev.gfx.UIManager;
import dev.managers.*;
import dev.window.WindowFrame;

public class Gfx {
	
	private Graphics g;
	private UIManager uiManager;
	private MouseManager mouseManager;
	private WindowFrame windowFrame;
	
	private int rectX, rectY;
	private int rectWidth = 6;
	private int rectHeight = 6;
	
	private int width, height;

	public Gfx(UIManager uiManager, WindowFrame windowFrame, MouseManager mouseManager, int width, int height) {
		this.width = width;
		this.height = height;
		this.uiManager = uiManager;
		this.mouseManager = mouseManager;
		this.windowFrame = windowFrame;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		drawBackground(g, width, height);
		uiManager.render(g);
		windowFrame.render(g);
		mouseDraw(g);
	}
	
	private void mouseDraw(Graphics g) {
		g.setColor(Color.RED);
		rectX = (int) mouseManager.getMouseX();
		rectY = (int) mouseManager.getMouseY();
		g.drawRect(rectX - 3, rectY - 3, rectWidth, rectHeight);
	}
	
	private void drawBackground(Graphics g, int width, int height) {
		g.setColor(new Color(250, 250, 255));
		g.fillRect(0, 0, width, height);
	}
	
}
