package main;

import java.awt.event.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.*;

import model.Cliente;
import view.TelaLogin;


public class App {

	public static void main(String[] args) {

		BufferedReader leia = new BufferedReader(new InputStreamReader(System.in));

		int resposta = JOptionPane.showConfirmDialog(null, "Possui Login?", "Login", JOptionPane.YES_NO_OPTION);
		if (resposta == JOptionPane.YES_OPTION) {
			
			new TelaLogin(new Cliente()).setVisible(true);
			
		}
			

		try {
			String sn = leia.readLine();

			if (sn == "sim") {

			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

}
