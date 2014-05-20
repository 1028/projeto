package Model;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Model.dao.DaoFactory;
import Model.dao.VooDao;

public class Voo {

	VooTO dadosVoo = null;

	public Voo(VooTO dadosVoo) {
		this.dadosVoo = dadosVoo;
	}

	public void inserirVoo() throws SQLException {
		DaoFactory factory = DaoFactory.getInstance();
		VooDao dao = factory.getVooDao;
		dao.inserirVoo(dadosVoo);
	}

	public ArrayList getRetorno() {
		return retorno;
	}

	public void alterarVoo(int codigoAeronave, int localidade)
			throws SQLException {
		bd = new BancoDeDados();

		bd.alterarVoo(getOrigem(), getDestino(), getEscala(), getDateHora(),
				getValor(), codigoAeronave, localidade, getSituacao(),
				getCodigoVoo());
	}

	public int consultarVoo() throws SQLException {
		int qtd = 0;

		bd = new BancoDeDados();

		retorno = bd.consultarVoo(getCodigoVoo());
		qtd = retorno.size();

		if (qtd > 0) {
			for (int i = 0; i < 6; i += 6) {
				setCodigoVoo((Integer) retorno.get(i));
				setOrigem((String) retorno.get(i + 1));
				setDestino((String) retorno.get(i + 2));
				setEscala((String) retorno.get(i + 3));

				// formatando a data
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				setDateHora(format.format(retorno.get(i + 4))
						+ format.format(retorno.get(i + 5)));
				setValor((Double) retorno.get(i + 6));
				// setSituacao((Integer)retorno.get(i+6));
			}
		}

		return qtd;

	}

	public void excluirVoo() throws SQLException {
		bd = new BancoDeDados();

		bd.excluirVoo(getCodigoVoo());
	}

}