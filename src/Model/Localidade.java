package Model;

import java.sql.SQLException;
import Model.LocalidadeTO;
import Model.dao.DaoFactory;
import Model.dao.LocalidadeDao;
import java.util.List;

public class Localidade {
	
	private LocalidadeTO dadosLocalidade;
	
	public Localidade(LocalidadeTO dadosTO) {
		dadosLocalidade = dadosTO;
	}
		
	public List<LocalidadeTO> consultar() throws SQLException {
		DaoFactory factory = DaoFactory.getInstance();
		LocalidadeDao dao = factory.getLocalidadeDao();
		return dao.consultar();
	}

}
