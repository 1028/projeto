package View;

import javax.swing.JOptionPane;

public class Interfaces {

	public Interfaces() {

	}

	public void mensagemInformacao(String mensagem, String titulo) {
		JOptionPane.showMessageDialog(null, mensagem, titulo,
				JOptionPane.INFORMATION_MESSAGE);
	}

	public void mensagemErro(String mensagem, String titulo) {
		JOptionPane.showMessageDialog(null, mensagem, titulo,
				JOptionPane.ERROR_MESSAGE);
	}

	public void mensagemAviso(String mensagem, String titulo) {
		JOptionPane.showMessageDialog(null, mensagem, titulo,
				JOptionPane.WARNING_MESSAGE);
	}
}
