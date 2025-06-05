package main;

import javax.swing.SwingUtilities;
import model.Cliente;
import model.Estabelecimento;
import repo.ClienteRepo;
import repo.EstabelecimentoRepo;
import view.login.TelaInicialLogin;
import view.login.TelaLoginCliente;

public class App {
	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> {
			new TelaInicialLogin().setVisible(true);
		});
		
		Estabelecimento est = new Estabelecimento("Araujo", "luis@araujo.com","1234", "11.345.678/0001-90");
		EstabelecimentoRepo repo = new EstabelecimentoRepo();
		repo.salvar(est);
		

	}
}