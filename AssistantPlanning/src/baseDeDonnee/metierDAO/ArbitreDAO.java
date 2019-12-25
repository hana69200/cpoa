package baseDeDonnee.metierDAO;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;

import baseDeDonnee.metier.Arbitre;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ArbitreDAO extends DAO
{
	public ArbitreDAO(Connection connection)
	{
		super(connection);
	}

	public List<Arbitre> getAllArbitritors() throws SQLException
	{
		List<Arbitre> liste = new ArrayList<Arbitre>();

		String sql = "select * from Arbitre";
		ResultSet rs = getRs(sql);
		while (rs.next())
		{
			liste.add(new Arbitre(rs.getInt("ID"), rs.getString("Nom"), rs.getString("Prenom"), rs.getInt("Nationalite"), rs.getInt("Equipe")));
		}
		return liste;
	}

	public Arbitre getArbitritorByID(int ID) throws SQLException
	{
		String sql = "select * from Arbitre where id = " + ID;
		ResultSet rs = getRs(sql);
		Arbitre j = null;
		if (rs.next())
		{
			j = new Arbitre(rs.getInt("ID"), rs.getString("Nom"), rs.getString("Prenom"), rs.getInt("Nationalite"), rs.getInt("Equipe"));
		}

		return j;
	}
}
