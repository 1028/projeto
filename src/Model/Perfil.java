package Model;

import java.sql.SQLException;
import java.util.List;

import Model.dao.DaoFactory;
import Model.dao.PerfilDao;

// mudar para class TipoPassageiro?
public class Perfil {

	public Perfil() {
	}

	public List<PerfilTO> consultarPerfil() throws SQLException {
		DaoFactory factory = DaoFactory.getInstance();
		PerfilDao dao = (PerfilDao) factory.getPerfilDao();
		return dao.consultarPerfil();
	}
}
