package Model.dao;

import java.sql.SQLException;

import Model.dao.impl.PassageiroMysqlDaoImpl;

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
}
