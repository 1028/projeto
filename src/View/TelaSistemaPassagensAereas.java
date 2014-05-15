package View;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class TelaSistemaPassagensAereas extends JFrame implements
		ActionListener {
	private JMenuItem sobre, sair;
	private JMenuItem comprar, cancelar, transferir;
	private JMenuItem checkin;
	private JMenuItem cadastrarVoo, consultarVoo, alterarVoo, excluirVoo;
	private JMenuItem cadastrarAeronave, consultarAeronave, alterarAeronave,
			excluirAeronave;
	private ResourceBundle bn;

	public TelaSistemaPassagensAereas(ResourceBundle bn, int iTipoUsuario) {
		super(bn.getString("FrmSistemaPassagensAereas.titulo"));
		Container tela = getContentPane();
		setLayout(new BorderLayout(4, 4));
		this.bn = bn;

		JMenuBar barra = new JMenuBar();

		// Menu Sistema e seus submenus.
		JMenu sistema = new JMenu(
				bn.getString("FrmSistemaPassagensAereas.menu.sistema"));
		sistema.setMnemonic(bn.getString(
				"FrmSistemaPassagensAereas.menu.sistema.mnemonic").charAt(0));

		sobre = new JMenuItem(
				bn.getString("FrmSistemaPassagensAereas.submenu.sobre"));
		sobre.setMnemonic(bn.getString(
				"FrmSistemaPassagensAereas.submenu.sobre.mnemonic").charAt(0));
		sobre.addActionListener(this);

		sair = new JMenuItem(
				bn.getString("FrmSistemaPassagensAereas.submenu.sair"));
		sair.setMnemonic(bn.getString(
				"FrmSistemaPassagensAereas.submenu.sair.mnemonic").charAt(0));
		sair.addActionListener(this);

		sistema.add(sobre);
		sistema.add(sair);
		barra.add(sistema);

		// Menu Passagem e seus submenus.
		JMenu passagem = new JMenu(
				bn.getString("FrmSistemaPassagensAereas.menu.passagem"));
		passagem.setMnemonic(bn.getString(
				"FrmSistemaPassagensAereas.menu.passagem.mnemonic").charAt(0));

		comprar = new JMenuItem(
				bn.getString("FrmSistemaPassagensAereas.submenu.comprar"));
		comprar.setMnemonic(bn.getString(
				"FrmSistemaPassagensAereas.submenu.comprar.mnemonic").charAt(0));
		comprar.addActionListener(this);

		cancelar = new JMenuItem(
				bn.getString("FrmSistemaPassagensAereas.submenu.cancelar"));
		cancelar.setMnemonic(bn.getString(
				"FrmSistemaPassagensAereas.submenu.cancelar.mnemonic")
				.charAt(0));
		cancelar.addActionListener(this);

		transferir = new JMenuItem(bn.getString("transferir"));
		transferir.setMnemonic(bn.getString("transferir.mnemonic").charAt(0));
		transferir.addActionListener(this);

		passagem.add(comprar);
		passagem.add(cancelar);
		passagem.add(transferir);
		barra.add(passagem);

		// Submenu de realizar Check-In.
		checkin = new JMenuItem(
				bn.getString("FrmSistemaPassagensAereas.submenu.checkin"));
		checkin.setMnemonic(bn.getString(
				"FrmSistemaPassagensAereas.submenu.checkin.mnemonic").charAt(0));
		checkin.addActionListener(this);
		barra.add(checkin);

		// Menu Voo e seus submenus.
		JMenu voo = new JMenu(
				bn.getString("FrmSistemaPassagensAereas.menu.voo"));
		voo.setMnemonic(bn.getString(
				"FrmSistemaPassagensAereas.menu.voo.mnemonic").charAt(0));

		consultarVoo = new JMenuItem(bn.getString("consultar"));
		consultarVoo.setMnemonic(bn.getString("consultar.mnemonic").charAt(0));
		consultarVoo.addActionListener(this);

		barra.add(voo);

		if (!(iTipoUsuario == 1)) {
			cadastrarVoo = new JMenuItem(bn.getString("cadastrar"));
			cadastrarVoo.setMnemonic(bn.getString("cadastrar.mnemonic").charAt(
					0));
			cadastrarVoo.addActionListener(this);

			alterarVoo = new JMenuItem(bn.getString("alterar"));
			alterarVoo.setMnemonic(bn.getString("alterar.mnemonic").charAt(0));
			alterarVoo.addActionListener(this);

			excluirVoo = new JMenuItem(bn.getString("excluir"));
			excluirVoo.setMnemonic(bn.getString("excluir.mnemonic").charAt(0));
			excluirVoo.addActionListener(this);

			voo.add(cadastrarVoo);
			voo.add(consultarVoo);
			voo.add(alterarVoo);
			voo.add(excluirVoo);

			// Menu Aeronave e seus submenus.
			JMenu aeronave = new JMenu(
					bn.getString("FrmSistemaPassagensAereas.menu.aeronave"));
			aeronave.setMnemonic(bn.getString(
					"FrmSistemaPassagensAereas.menu.aeronave.mnemonic").charAt(
					0));

			cadastrarAeronave = new JMenuItem(bn.getString("cadastrar"));
			cadastrarAeronave.setMnemonic(bn.getString("cadastrar.mnemonic")
					.charAt(0));
			cadastrarAeronave.addActionListener(this);

			consultarAeronave = new JMenuItem(bn.getString("consultar"));
			consultarAeronave.setMnemonic(bn.getString("consultar.mnemonic")
					.charAt(0));
			consultarAeronave.addActionListener(this);

			alterarAeronave = new JMenuItem(bn.getString("alterar"));
			alterarAeronave.setMnemonic(bn.getString("alterar.mnemonic")
					.charAt(0));
			alterarAeronave.addActionListener(this);

			excluirAeronave = new JMenuItem(bn.getString("excluir"));
			excluirAeronave.setMnemonic(bn.getString("excluir.mnemonic")
					.charAt(0));
			excluirAeronave.addActionListener(this);

			aeronave.add(cadastrarAeronave);
			aeronave.add(consultarAeronave);
			aeronave.add(alterarAeronave);
			aeronave.add(excluirAeronave);

			barra.add(aeronave);
		} else {
			voo.add(consultarVoo);
		}

		tela.add(barra, BorderLayout.NORTH);
	}

	public void actionPerformed(ActionEvent evento) {
		// Ação dos submenus do menu Sistema.
		Interfaces oInterface = new Interfaces();
		if (evento.getSource() == sobre) {
			oInterface.mensagemInformacao(bn.getString("mensagem.sobre"),
					bn.getString("FrmSistemaPassagensAereas.submenu.sobre"));
		}
		if (evento.getSource() == sair) {
			oInterface.mensagemInformacao(bn.getString("mensagem.sair"),
					bn.getString("FrmSistemaPassagensAereas.submenu.sair"));
			System.exit(0);
		}

		// Ação dos submenus do menu Passagem.
		if (evento.getSource() == comprar) {
			TelaCadastrarPassageiro oCadPassageiro;
			try {
				oCadPassageiro = new TelaCadastrarPassageiro(
						bn);
				oCadPassageiro.setSize(410, 240);
				oCadPassageiro.setVisible(true);
				oCadPassageiro.setResizable(false);
				oCadPassageiro.setLocationRelativeTo(null);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if (evento.getSource() == cancelar) {
			TelaCancelarPassagem oCanPass = new TelaCancelarPassagem(bn);
			oCanPass.setSize(310, 120);
			oCanPass.setVisible(true);
			oCanPass.setResizable(false);
			oCanPass.setLocationRelativeTo(null);
		}
		if (evento.getSource() == transferir) {
			TelaTransferirPassagem oTPassagem = new TelaTransferirPassagem(bn);
			oTPassagem.setSize(400, 400);
			oTPassagem.setVisible(true);
			//oTPassagem.setResizable(false);
			oTPassagem.setLocationRelativeTo(null);
		}

		// Ação do submenu Check-in.
		if (evento.getSource() == checkin) {
			TelaEfetuarCheckin oEc = new TelaEfetuarCheckin(bn);
			oEc.setSize(400, 400);
			oEc.setVisible(true);
			oEc.setResizable(false);
			oEc.setLocationRelativeTo(null);
		}

		// Ação dos submenus do menu Voo.
		if (evento.getSource() == cadastrarVoo) {
			Object oTelaVoo = null;
			try {
				oTelaVoo = instanciaTelaVoo();
				((TelaVoo) oTelaVoo).bloquearBotoes("Cadastrar");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		if (evento.getSource() == consultarVoo) {
			Object oTelaVoo = null;
			try {
				oTelaVoo = instanciaTelaVoo();
				((TelaVoo) oTelaVoo).bloquearBotoes("Consultar");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		if (evento.getSource() == alterarVoo) {
			Object oTelaVoo = null;
			try {
				oTelaVoo = instanciaTelaVoo();
				((TelaVoo) oTelaVoo).bloquearBotoes("Alterar");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		if (evento.getSource() == excluirVoo) {
			Object oTelaVoo = null;
			try {
				oTelaVoo = instanciaTelaVoo();
				((TelaVoo) oTelaVoo).bloquearBotoes("Excluir");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
			
		}

		// Ação dos submenus do menu Aeronave.
		if (evento.getSource() == cadastrarAeronave) {
			Object oTelaAeronave = instanciaTelaAeronave();
			((TelaAeronave) oTelaAeronave).bloquearBotoes("Cadastrar");
		}

		if (evento.getSource() == consultarAeronave) {
			Object oTelaAeronave = instanciaTelaAeronave();
			((TelaAeronave) oTelaAeronave).bloquearBotoes("Consultar");
		}

		if (evento.getSource() == alterarAeronave) {
			Object oTelaAeronave = instanciaTelaAeronave();
			((TelaAeronave) oTelaAeronave).bloquearBotoes("Alterar");
		}

		if (evento.getSource() == excluirAeronave) {
			Object oTelaAeronave = instanciaTelaAeronave();
			((TelaAeronave) oTelaAeronave).bloquearBotoes("Excluir");
		}
	}

	private Object instanciaTelaVoo() throws ParseException, SQLException {
		TelaVoo oCadVoo = new TelaVoo(bn);
		oCadVoo.setSize(800, 290);
		oCadVoo.setVisible(true);
		oCadVoo.setResizable(false);
		oCadVoo.setLocationRelativeTo(null);
		return oCadVoo;
	}

	private Object instanciaTelaAeronave() {
		TelaAeronave oCadAeronave = new TelaAeronave(bn);
		oCadAeronave.setSize(410, 160);
		oCadAeronave.setVisible(true);
		oCadAeronave.setResizable(false);
		oCadAeronave.setLocationRelativeTo(null);
		return oCadAeronave;
	}
}
