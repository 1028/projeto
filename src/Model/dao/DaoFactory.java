package Model.dao;

import java.sql.SQLException;

import Model.dao.impl.AeronaveMysqlDaoImpl;
import Model.dao.impl.FormaTratamentoMysqlImpl;
import Model.dao.impl.LoginMysqlImpl;
import Model.dao.impl.PassageiroMysqlDaoImpl;
import Model.dao.impl.PerfilMysqlImpl;
import Model.dao.impl.SituacaoMysqlImpl;
import Model.dao.impl.VooMysqlDaoImpl;

public class DaoFactory {

	private static DaoFactory factory = null;

	private DaoFactory() {

	}

	public static DaoFactory getInstance() {
		if (factory == null) {
			factory = new DaoFactory();
		}
		return factory;
	}

	public PassageiroMysqlDaoImpl getPassageiroDao() throws SQLException {
		return new PassageiroMysqlDaoImpl();
	}

	public AeronaveMysqlDaoImpl getAeronaveDao() {
		return new AeronaveMysqlDaoImpl();
	}
	
	public VooMysqlDaoImpl getVooDao(){
		return new VooMysqlDaoImpl();
	}
	
	public LoginMysqlImpl getLoginDao(){
		return new LoginMysqlImpl(); 
	}
	
	public SituacaoMysqlImpl getSituacaoDao(){
		return new SituacaoMysqlImpl();
	}
	
	public FormaTratamentoMysqlImpl getFormaTratamentoDao(){
		return new FormaTratamentoMysqlImpl();
	}
	
	public PerfilMysqlImpl getPerfilDao(){
		return new PerfilMysqlImpl();
	}
}
