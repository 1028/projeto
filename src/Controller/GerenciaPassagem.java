package Controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import Model.Cheque;
import Model.Pagamento;
import Model.Passagem;

public class GerenciaPassagem {

	// Métodos para gerenciar o pagamento das passagens.
	public void pagarCartao(String sNomeTitular, String sCpf, String string,
			String sFormaPagamento) throws SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

		Pagamento oPagamento = new Pagamento(sNomeTitular, sCpf,
				(sdf.format(new Date())), sFormaPagamento);

		oPagamento.pagar();
	}

	public void pagarCheque(String sNomeTitular, String sCpf, String string,
			String sFormaPagamento, String sBanco, String sAgencia,
			String sConta) throws SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

		Cheque oCheque = new Cheque(sNomeTitular, sCpf,
				(sdf.format(new Date())), sFormaPagamento, sBanco, sAgencia,
				sConta);

		oCheque.pagar();
		oCheque.cadastrarCheque();
	}

	// Método para transferir passagem.
	public void transferirPassagem() {

	}

	// Métodos para gerenciar o cancelamento de passagens.
	public void cancelarPassagem() {

	}

	// Método para verificar o tempo restante para o voo e realizar o cálculo do valor a ser devolvido.
	public double calculaDevolucao(int iNumPassagem) {
		double totalValorDevolucao = -1.0;

		Passagem oPassagem = new Passagem(iNumPassagem);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");

		String sDiaVerificar = (sdf.format(new Date())).substring(0, 10);
		String sHoraVerificar = (sdf.format(new Date())).substring(11, 19);
		
		long diaAntesVoo = verificarDiasRestante(sDiaVerificar);
		int horaAntesVoo = verificarHorasRestante(sHoraVerificar);

		if (diaAntesVoo >= 1 || horaAntesVoo >= 2) {

			// substituir os 1001 pelo valor vinculado a passagem
			// pesquisada!
			if (diaAntesVoo >= 1) {
				totalValorDevolucao = 1001;
			} else {
				if (horaAntesVoo >= 12) {
					totalValorDevolucao = (1001 * 0.4);
				} else if (horaAntesVoo >= 6) {
					totalValorDevolucao = (1001 * 0.2);
				}
			}
		}
		return totalValorDevolucao;
	}

	public int verificarHorasRestante(String sHoraVerificar) {

		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		String sHoraAtual = (sdf.format(new Date()));

		String horas = "00";
		String minutos = "00";
		String segundos = "00";

		int sub = 0;
		int subHoras = 0;
		int subMinutos = 0;

		int segundos1 = (Integer.parseInt(sHoraVerificar.substring(0, 2)) * 3600)
				+ (Integer.parseInt(sHoraVerificar.substring(3, 5)) * 60)
				+ Integer.parseInt(sHoraVerificar.substring(6, 8));
		int segundos2 = (Integer.parseInt(sHoraAtual.substring(0, 2)) * 3600)
				+ (Integer.parseInt(sHoraAtual.substring(3, 5)) * 60)
				+ Integer.parseInt(sHoraAtual.substring(6, 8));

		if (segundos1 > segundos2) {
			sub = segundos1 - segundos2;
		} else if (segundos2 > segundos1) {
			sub = segundos2 - segundos1;
		} else {
			sub = 0;
		}

		if (sub >= 3600) {
			subHoras = (sub - (sub % 3600)) / 3600;
			sub = sub - (subHoras * 3600);
			if (subHoras < 10) {
				horas = "0" + Integer.toString(subHoras);
			} else {
				horas = Integer.toString(subHoras);
			}
		}

		if (sub >= 60) {
			subMinutos = (sub - (sub % 60)) / 60;
			sub = sub - (subMinutos * 60);
			if (subMinutos < 10) {
				minutos = "0" + Integer.toString(subMinutos);
			} else {
				minutos = Integer.toString(subMinutos);
			}
		}

		if (sub < 10) {
			segundos = "0" + Integer.toString(sub);
		} else {
			segundos = Integer.toString(sub);
		}

		 System.out.println("HH:MM" + horas + ":" + minutos + ":" + segundos);
		 
		return Integer.parseInt(horas);
	}

	public long verificarDiasRestante(String sDiaVerificar) {
		// Data inicial
		Calendar dataInicio = Calendar.getInstance();
		// Atribui a data de 10/FEV/2008
		dataInicio.set(2013, Calendar.OCTOBER, 17);
		/*
		 * dataInicio.set(Integer.parseInt(sDiaVerificar.substring(0, 4)),
		 * Integer.parseInt(sDiaVerificar.substring(5, 7)),
		 * Integer.parseInt(sDiaVerificar.substring(8, 10)));
		 */
		// Data de hoje
		Calendar dataFinal = Calendar.getInstance();
		// Calcula a diferença entre hoje e da data de inicio
		long diferenca = dataFinal.getTimeInMillis()
				- dataInicio.getTimeInMillis();
		// Quantidade de milissegundos em um dia
		int tempoDia = 1000 * 60 * 60 * 24;
		long diasDiferenca = diferenca / tempoDia;
		System.out.println("Entre a data inicial e final são " + diasDiferenca
				+ " dias de diferença.");

		return diasDiferenca;
	}

}
