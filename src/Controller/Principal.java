package Controller;

import javax.swing.JFrame;

import View.TelaIdioma;

public class Principal {

	public static void main(String[] args) {
		TelaIdioma oIdi = new TelaIdioma();
		// Finaliza o programa quando o icone "x" for clicado.
		oIdi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Configura o tamanho do frame.
		oIdi.setSize(606, 162);
		// Exibi o frame.
		oIdi.setVisible(true);
		// Desabilita o aumento/diminuição do frame.
		oIdi.setResizable(false);
		// Centraliza o frame na tela.
		oIdi.setLocationRelativeTo(null);
	}

}
