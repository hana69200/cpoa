package baseDeDonnee.metierDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe mère pour les classes accedant à la base de données
 *
 */
public class DAO
{
	private Connection connection;

	protected DAO(Connection connection)
	{
		this.connection = connection;
	}

	protected ResultSet getRs(String sql) throws SQLException
	{
		Statement smt = connection.createStatement();
		return smt.executeQuery(sql);
	}

	public Connection getConnection()
	{
		return connection;
	}
}
