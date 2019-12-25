package baseDeDonnee.metierDAO;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;

import baseDeDonnee.metier.Joueur;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JoueurDAO extends DAO
{
	public JoueurDAO(Connection connection)
	{
		super(connection);
	}

	/**
	 * @return la liste de tout les joueurs
	 * @throws SQLException
	 */
	public List<Joueur> getAllPlayers() throws SQLException
	{
		List<Joueur> liste = new ArrayList<Joueur>();

		String sql = "select * from Joueur";
		ResultSet rs = getRs(sql);
		while (rs.next())
		{
			liste.add(new Joueur(rs.getInt("ID"), rs.getString("Nom"), rs.getString("Prenom"), rs.getInt("Nationalite")));
		}
		return liste;
	}

	/**
	 * @param ID : ID du joueur recherché
	 * @return le Joueur s'il existe, null sinon
	 * @throws SQLException
	 */
	public Joueur getPlayerByID(int ID) throws SQLException
	{
		String sql = "select * from Joueur where id = " + ID;
		ResultSet rs = getRs(sql);
		Joueur j = null;
		if (rs.next())
		{
			j = new Joueur(rs.getInt("ID"), rs.getString("Nom"), rs.getString("Prenom"), rs.getInt("Nationalite"));
		}

		return j;
	}
}
