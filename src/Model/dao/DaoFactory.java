package Model.dao;

import java.sql.SQLException;

import Model.dao.impl.AeronaveMysqlDaoImpl;
import Model.dao.impl.PassageiroMysqlDaoImpl;
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
}
