package View;

import java.awt.Container;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class TelaIdioma extends JFrame implements ActionListener {
	private JButton espanhol, ingles, portugues;
	private ResourceBundle bn = null;

	public TelaIdioma() {
		super("");
		Container tela = getContentPane();
		tela.setLayout(new GridLayout(1, 3));
		bn = ResourceBundle.getBundle("Idiomas/idioma");
		setTitle(bn.getString("FrmIdioma.titulo"));

		ImageIcon iconeEs = new ImageIcon("src/Imagens/es.jpg");
		espanhol = new JButton(iconeEs);
		espanhol.addActionListener(this);

		ImageIcon iconeUSA = new ImageIcon("src/Imagens/usa.jpg");
		ingles = new JButton(iconeUSA);
		ingles.addActionListener(this);

		ImageIcon iconeBr = new ImageIcon("src/Imagens/pt_br.jpg");
		portugues = new JButton(iconeBr);
		portugues.addActionListener(this);

		tela.add(espanhol);
		tela.add(ingles);
		tela.add(portugues);

	}

	public void actionPerformed(ActionEvent evento) {
		if (evento.getSource() == espanhol) {
			bn = ResourceBundle.getBundle("Idiomas/idioma", new Locale(
					"es", "ES"));
			telaLogin(bn);

		}
		if (evento.getSource() == ingles) {
			bn = ResourceBundle.getBundle("Idiomas/idioma", Locale.US);
			telaLogin(bn);
		}
		if (evento.getSource() == portugues) {
			bn = ResourceBundle.getBundle("Idiomas/idioma", new Locale(
					"pt", "BR"));
			telaLogin(bn);
		}
		// System.out.print("" + getSize());
	}

	private void telaLogin(ResourceBundle bn) {
		TelaLogin oLog = new TelaLogin(bn);
		oLog.setSize(230, 120);
		oLog.setVisible(true);
		oLog.setResizable(false);
		oLog.setLocationRelativeTo(null);
		/*
		 * SistemaPassagensAereas oSpa = new SistemaPassagensAereas(bn);
		 * oSpa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 * oSpa.setSize(500, 500); oSpa.setVisible(true);
		 * oSpa.setResizable(false); oSpa.setLocationRelativeTo(null);
		 */
		dispose();
	}
}
