package View;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelaDialogoAssentos extends JDialog implements ActionListener {
	
	//vetor de assentos que ir� guardar o n�mero do assento
	private int[] vetAssentos;
	
	private int assEscolhido;
	
	//Essa classe tem o objetivo de mostrar os assentos dispon�veis do voo
	//Para realizar tal consulta, deve-se ter o n�mero do voo para localizar a aero nave.
	public TelaDialogoAssentos(int numeroVoo, JFrame fr)
	{
		
		super(fr,true);
		
		Container tela = getContentPane();
		setLayout(new BorderLayout());
		vetAssentos = new int[100];
		
		//panel externo
		//JPanel painelExterno = new JPanel(new BorderLayout(5,5));
		
		//Aqui ser� a tabela que os assentos ser�o inseridos
		//6 � o n�mero de colunas que definimos por padr�o
		//Portanto, a quantidade de linhas � definida por
		// l = qtdeAssentos/c (vetAssentos.length), sempre arredondando para cima, por isso l+1
		int l = (vetAssentos.length / 6)+1;
		
		JPanel painelInterno = new JPanel(new GridLayout(l,6));
		
		for(int i = 0; i < vetAssentos.length-1;i++)
		{
			vetAssentos[i] = i+1;
			JButton btn = new JButton(""+vetAssentos[i]);
			btn.addActionListener(this);
			painelInterno.add(btn);
		}
		
		tela.add(painelInterno, BorderLayout.CENTER);
		
		setSize(600, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	
	//M�todo que realiza os eventos
	public void actionPerformed(ActionEvent evento) {
		JButton btn = (JButton)evento.getSource();
		
		assEscolhido = Integer.parseInt(btn.getText());
	}
}
