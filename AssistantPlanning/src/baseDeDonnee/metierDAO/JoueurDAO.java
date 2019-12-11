package baseDeDonnee.metierDAO;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;

import baseDeDonnee.metier.Joueur;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JoueurDAO
{
	private Connection connection;

	public JoueurDAO(Connection connection)
	{
		this.connection = connection;
	}

	public List<Joueur> getAllPlayers() throws SQLException
	{
		List<Joueur> liste = new ArrayList<Joueur>();

		String sql = "select * from Joueur";
		Statement smt = connection.createStatement();
		ResultSet rs = smt.executeQuery(sql);
		while (rs.next())
		{
			liste.add(new Joueur(rs.getInt("ID"), rs.getString("Nom"), rs.getString("Prenom"), rs.getInt("Nationalite")));
		}
		return liste;
	}

	public Joueur getPlayerByID(int ID) throws SQLException
	{
		String sql = "select * from Joueur where id = " + ID;
		Statement smt = connection.createStatement();
		ResultSet rs = smt.executeQuery(sql);
		Joueur j = null;
		if (rs.next())
		{
			j = new Joueur(rs.getInt("ID"), rs.getString("Nom"), rs.getString("Prenom"), rs.getInt("Nationalite"));
		}

		return j;
	}
}
