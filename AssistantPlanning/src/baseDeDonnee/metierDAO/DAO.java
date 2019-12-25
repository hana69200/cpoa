package baseDeDonnee.metierDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO
{
	private Connection connection;

	protected DAO(Connection connection)
	{
		this.connection = connection;
	}

	protected Statement getStm() throws SQLException
	{
		//TODO getRs (return le result set directement)
		return connection.createStatement();
	}

	public Connection getConnection()
	{
		return connection;
	}
}
