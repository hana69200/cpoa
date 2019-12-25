package baseDeDonnee.metierDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

import baseDeDonnee.metier.Joueur;

public class UtilisateurDAO extends DAO
{
	public UtilisateurDAO(Connection connection)
	{
		super(connection);
	}

	/**
	 * verifie si un utilisateur existe
	 * @param nomUser : le nom à tester
	 * @return true s'il existe false sinon
	 * @throws SQLException
	 */
	public boolean isUserOK(String nomUser) throws SQLException
	{
		String sql = "select * from Utilisateur where Nom = '" + nomUser + "'";
		ResultSet rs = getRs(sql);
		return rs.next();
	}

	/**
	 * verifie si le mot de passe est correct
	 * @param nomUser : nom de l'utilisateur
	 * @param password : le mot de passe testé
	 * @return true si l'utilisateur existe et que le mot de passe est correct, false sinon
	 * @throws SQLException
	 */
	public boolean isPasswordOK(String nomUser, String password) throws SQLException
	{
		String sql = "select * from Utilisateur where Nom = '" + nomUser + "'";
		ResultSet rs = getRs(sql);
		if (rs.next())
			if (password.equals(rs.getString("MotDePasse")))
				return true;
		return false;
	}

	/**
	 * Renvoie le joueur associer au nom d'utilisateur
	 * @param nomUser : nom d'utilisateur
	 * @return le Joueur s'il existe, null sinon
	 * @throws SQLException
	 */
	public Joueur getJoueur(String nomUser) throws SQLException
	{
		String sql = "select * from Utilisateur where Nom = '" + nomUser + "'";
		ResultSet rs = getRs(sql);
		if (rs.next())
		{
			JoueurDAO jDAO = new JoueurDAO(this.getConnection());
			return jDAO.getPlayerByID(rs.getInt("IDJoueur"));
		}
		return null;
	}

	/**
	 * renvoie les autorisation associer à un utilisateur
	 * @param nomUser nom d'utilisateur
	 * @return table des autorisation, null si le l'utilisateur n'existe pas
	 * @throws SQLException
	 */
	public Hashtable<String, Boolean> getAutorisation(String nomUser) throws SQLException
	{
		Hashtable<String, Boolean> table = new Hashtable<String, Boolean>();

		String sql = "select * from Utilisateur where Nom = '" + nomUser + "'";
		ResultSet rs = getRs(sql);
		if (rs.next())
		{
			sql = "select * from Autorisation where Type = " + rs.getInt("TypeAutorisation");
			rs = getRs(sql);
			if (rs.next())
			{
				table.put("ReservationEntr", rs.getBoolean("ReservationEntr"));
				table.put("AjoutMatch", rs.getBoolean("AjoutMatch"));
				table.put("ModifMatch", rs.getBoolean("ModifMatch"));
				table.put("AjoutResult", rs.getBoolean("AjoutResult"));
				return table;
			}
		}
		return null;
	}
}
