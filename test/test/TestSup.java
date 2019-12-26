package test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestSup
{
	protected Connection getCon()
			throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		Class<?> c = Class.forName("com.mysql.cj.jdbc.Driver");
		Driver pilote = (Driver) c.newInstance();
		DriverManager.registerDriver(pilote);
		String protocole = "jdbc:mysql:";
		String ip = "iutdoua-web.univ-lyon1.fr";
		String port = "3306";
		String nomBase = "p1607863";
		String conString = protocole + "//" + ip + ":" + port + "/" + nomBase;
		String nomConnexion = "p1607863";
		String motDePasse = "270858";

		return DriverManager.getConnection(conString, nomConnexion, motDePasse);
	}

}
