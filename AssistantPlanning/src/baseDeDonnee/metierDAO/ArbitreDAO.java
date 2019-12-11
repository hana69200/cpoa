package baseDeDonnee.metierDAO;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;

import baseDeDonnee.metier.Arbitre;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ArbitreDAO
{
	private Connection connection;

	public ArbitreDAO(Connection connection)
	{
		this.connection = connection;
	}

	public List<Arbitre> getAllArbitritors() throws SQLException
	{
		List<Arbitre> liste = new ArrayList<Arbitre>();

		String sql = "select * from Arbitre";
		Statement smt = connection.createStatement();
		ResultSet rs = smt.executeQuery(sql);
		while (rs.next())
		{
			liste.add(new Arbitre(rs.getInt("ID"), rs.getString("Nom"), rs.getString("Prenom"), rs.getInt("Nationalite")));
		}
		return liste;
	}

	public Arbitre getArbitritorByID(int ID) throws SQLException
	{
		String sql = "select * from Arbitre where id = " + ID;
		Statement smt = connection.createStatement();
		ResultSet rs = smt.executeQuery(sql);
		Arbitre j = null;
		if (rs.next())
		{
			j = new Arbitre(rs.getInt("ID"), rs.getString("Nom"), rs.getString("Prenom"), rs.getInt("Nationalite"));
		}

		return j;
	}
}
