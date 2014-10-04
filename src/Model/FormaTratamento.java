package Model;

import java.sql.SQLException;
import java.util.List;

import Model.dao.DaoFactory;
import Model.dao.FormaTratamentoDao;


public class FormaTratamento {

	public FormaTratamento() {
		
	}
	
	public List<FormaTratamentoTO> consultarFormaTratamento() throws SQLException {
		DaoFactory factory = DaoFactory.getInstance();
		FormaTratamentoDao dao = (FormaTratamentoDao) factory.getFormaTratamentoDao();
		return dao.consultarFormaTratamento();
	}
}
