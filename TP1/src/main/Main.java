package main;

import gui.InterfazUsuario;

public class Main {

	public static void main(String[] args) {
		InterfazUsuario juego2048 = new InterfazUsuario();
		juego2048.setVisible(true); // Hacer visible la ventana primero
		juego2048.dibujarMatriz();
	}

}