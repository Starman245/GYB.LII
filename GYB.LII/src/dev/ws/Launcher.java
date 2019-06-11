package dev.ws;

import dev.ws.Workstation;

public class Launcher {
	public static void main(String[] args) {
		Workstation workstation = new Workstation(720, 480, "GYB.LII Workstation");
		workstation.start();
	}
}
