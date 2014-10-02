//Classe de Domínio referente a tabela SITUACAO da base.

package Model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.dao.DaoFactory;
import Model.dao.SituacaoDao;


public class Situacao {
	
	SituacaoTO dadosSituacao = null;
	
	public Situacao(SituacaoTO dadosSituacao) {
		this.dadosSituacao = dadosSituacao;
	}
	
	public ArrayList<SituacaoTO> consultarSituacao() throws SQLException {
		DaoFactory factory = DaoFactory.getInstance();
		SituacaoDao dao = (SituacaoDao) factory.getSituacaoDao();
		return dao.consultarSituacao();
	}
}
