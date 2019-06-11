package dev.tools;

import java.awt.Graphics;

public abstract class Tool {
	private static Tool currentTool = null;
	
	public static void setTool(Tool tool) {
		currentTool = tool;
	}
	
	public static Tool getTool() {
		return currentTool;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
}
