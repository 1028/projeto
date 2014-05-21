package View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Controller.GerenciaPassagem;

import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

public class TelaComprarPassagem extends JFrame implements ActionListener {
	private JTextField tfValorTotal, tfNomeTitular, tfCpf, tfNumeroCartao,
			tfCodSeguranca;
	private JMonthChooser mcMes;
	private JYearChooser ycAno;
	private JTextField tfNomeTitularCheque, tfCpfCheque, tfBanco, tfAgencia,
			tfConta;
	private JButton btnComprar;
	// private String vetFormaPagamento[], vetTipoCartao[];
	private JComboBox cbFormaPagamento, cbTipoCartao;
	private Container tela;
	private JPanel painelLabelCartaoCredito, painelLabelCheque,
			painelDadosCartaoCredito, painelDadosCheque;
	private ResourceBundle bn;

	public TelaComprarPassagem(ResourceBundle bn) {
		super(bn.getString("FrmComprarPassagem.titulo"));
		tela = getContentPane();
		setLayout(new BorderLayout(4, 4));
		this.bn = bn;

		JPanel painelPagamento = new JPanel(new GridLayout(2, 2, 4, 4));

		JLabel lFormaPagamento = new JLabel(bn.getString("rotulo.forma.pag"));
		painelPagamento.add(lFormaPagamento);

		String vetFormaPagamento[] = { bn.getString("forma.pagamento.cartao"),
				bn.getString("forma.pagamento.cheque") };

		cbFormaPagamento = new JComboBox(vetFormaPagamento);
		cbFormaPagamento.addActionListener(this);
		painelPagamento.add(cbFormaPagamento);

		JLabel lValorTotal = new JLabel(bn.getString("rotulo.valor.total"));
		painelPagamento.add(lValorTotal);

		tfValorTotal = new JTextField();
		painelPagamento.add(tfValorTotal);

		painelLabelCartaoCredito = new JPanel(new GridLayout(6, 1, 4, 4));

		JLabel lTipoCartao = new JLabel(bn.getString("rotulo.tipo.cartao"));
		painelLabelCartaoCredito.add(lTipoCartao);

		JLabel lNomeTitular = new JLabel(bn.getString("rotulo.nome.titular"));
		painelLabelCartaoCredito.add(lNomeTitular);

		JLabel lCpf = new JLabel(bn.getString("rotulo.cpf"));
		painelLabelCartaoCredito.add(lCpf);

		JLabel lNumeroCartao = new JLabel(bn.getString("rotulo.numero.cartao"));
		painelLabelCartaoCredito.add(lNumeroCartao);

		JLabel lDataValidade = new JLabel(bn.getString("rotulo.data.validade"));
		painelLabelCartaoCredito.add(lDataValidade);

		JLabel lCodSeguranca = new JLabel(bn.getString("rotulo.cod.seguranca"));
		painelLabelCartaoCredito.add(lCodSeguranca);

		painelDadosCartaoCredito = new JPanel(new GridLayout(6, 1, 4, 4));

		String vetTipoCartao[] = { "VISA", "MasterCARD", "American Express" };

		cbTipoCartao = new JComboBox(vetTipoCartao);
		painelDadosCartaoCredito.add(cbTipoCartao);

		tfNomeTitular = new JTextField(12);
		painelDadosCartaoCredito.add(tfNomeTitular);

		tfCpf = new JTextField(12);
		painelDadosCartaoCredito.add(tfCpf);

		tfNumeroCartao = new JTextField(12);
		painelDadosCartaoCredito.add(tfNumeroCartao);

		JPanel painelDataValidade = new JPanel(new GridLayout(1, 2, 4, 4));

		mcMes = new JMonthChooser();
		painelDataValidade.add(mcMes);

		ycAno = new JYearChooser();
		painelDataValidade.add(ycAno);

		painelDadosCartaoCredito.add(painelDataValidade);

		tfCodSeguranca = new JTextField(12);
		painelDadosCartaoCredito.add(tfCodSeguranca);

		painelLabelCheque = new JPanel(new GridLayout(5, 1, 4, 4));

		JLabel lNomeTitularCheque = new JLabel(
				bn.getString("rotulo.nome.titular"));
		painelLabelCheque.add(lNomeTitularCheque);

		JLabel lCpfCheque = new JLabel(bn.getString("rotulo.cpf"));
		painelLabelCheque.add(lCpfCheque);

		JLabel lBanco = new JLabel(bn.getString("rotulo.banco"));
		painelLabelCheque.add(lBanco);

		JLabel lAgencia = new JLabel(bn.getString("rotulo.agencia"));
		painelLabelCheque.add(lAgencia);

		JLabel lConta = new JLabel(bn.getString("rotulo.conta"));
		painelLabelCheque.add(lConta);

		painelDadosCheque = new JPanel(new GridLayout(5, 1, 4, 4));

		tfNomeTitularCheque = new JTextField("Nome C");
		painelDadosCheque.add(tfNomeTitularCheque);

		tfCpfCheque = new JTextField("CPF Cheque");
		painelDadosCheque.add(tfCpfCheque);

		tfBanco = new JTextField(12);
		painelDadosCheque.add(tfBanco);

		tfAgencia = new JTextField(12);
		painelDadosCheque.add(tfAgencia);

		tfConta = new JTextField(12);
		painelDadosCheque.add(tfConta);

		btnComprar = new JButton(
				bn.getString("FrmComprarPassagem.botao.comprar"));
		btnComprar.setMnemonic(bn.getString(
				"FrmComprarPassagem.botao.comprar.mnemonic").charAt(0));
		btnComprar.addActionListener(this);

		tela.add(painelPagamento, BorderLayout.NORTH);
		tela.add(painelLabelCartaoCredito, BorderLayout.WEST);
		tela.add(painelDadosCartaoCredito, BorderLayout.CENTER);
		tela.add(btnComprar, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent evento) {
		// System.out.println("" + getSize());
		String sFormaPagamento = cbFormaPagamento.getSelectedItem().toString();

		if (evento.getSource() == cbFormaPagamento) {
			if (sFormaPagamento.equals(bn.getString("forma.pagamento.cartao"))) {
				tela.remove(painelLabelCheque);
				tela.remove(painelDadosCheque);
				tela.add(painelLabelCartaoCredito, BorderLayout.WEST);
				tela.add(painelDadosCartaoCredito, BorderLayout.CENTER);
				tela.validate();
				tela.repaint();
			} else if (sFormaPagamento.equals(bn
					.getString("forma.pagamento.cheque"))) {
				tela.remove(painelLabelCartaoCredito);
				tela.remove(painelDadosCartaoCredito);
				tela.add(painelLabelCheque, BorderLayout.WEST);
				tela.add(painelDadosCheque, BorderLayout.CENTER);
				tela.validate();
				tela.repaint();
			}
		}

		if (evento.getSource() == btnComprar) {
			Validacoes oValida = new Validacoes();
			Interfaces oInterface = new Interfaces();
			GerenciaPassagem oGPagamento = new GerenciaPassagem();

			String sNomeTitular, sCpf;

			if (sFormaPagamento.equals(bn.getString("forma.pagamento.cartao"))) {

				sNomeTitular = tfNomeTitular.getText();
				sCpf = tfCpf.getText();
				String sNumCartao = tfNumeroCartao.getText();
				String sCodSeguranca = tfCodSeguranca.getText();
				if (!(oValida.camposEmBranco(sNomeTitular, sCpf, sNumCartao,
						sCodSeguranca))) {
					String sData;

					try {
						oGPagamento.pagarCartao(sNomeTitular, sCpf,
								"2013-11-11", sFormaPagamento);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					oInterface.mensagemAviso(
							bn.getString("mensage.campos.branco"),
							bn.getString("FrmMensagem.titulo.atencao"));
				}
			} else {
				sNomeTitular = tfNomeTitularCheque.getText();
				sCpf = tfCpfCheque.getText();
				String sBanco = tfBanco.getText();
				String sAgencia = tfAgencia.getText();
				String sConta = tfConta.getText();
				if (!(oValida.camposEmBranco(sNomeTitular, sCpf, sBanco,
						sAgencia, sConta))) {

					try {
						oGPagamento.pagarCheque(sNomeTitular, sCpf,
								"2013-10-10", sFormaPagamento, sBanco,
								sAgencia, sConta);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					oInterface.mensagemAviso(
							bn.getString("mensage.campos.branco"),
							bn.getString("FrmMensagem.titulo.atencao"));
				}
			}

		}
	}
}
