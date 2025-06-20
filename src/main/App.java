package main;

import javax.swing.SwingUtilities;

import view.login.TelaInicialLogin;

public class App {
	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> {
			new TelaInicialLogin().setVisible(true);
		});

	}
}