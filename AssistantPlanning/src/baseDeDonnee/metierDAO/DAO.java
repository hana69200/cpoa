package baseDeDonnee.metierDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;

import baseDeDonnee.metier.Match;

/**
 * Classe m�re pour les classes accedant � la base de donn�es
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

	/**
	 * parse un objet Calendar en une chaine de caract�re utilisable dans une
	 * requete sql (pour le jour)
	 * 
	 * @param date : l'objet � parser
	 * @return la chaine de caratere
	 */
	protected String getDay(Calendar date)
	{
		return Integer.toString(date.get(Calendar.YEAR)) + "-" + Integer.toString(date.get(Calendar.MONTH) + 1) + "-"
				+ Integer.toString(date.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * parse un objet Calendar en une chaine de caract�re utilisable dans une
	 * requete sql (pour l'heure)
	 * 
	 * @param date : l'objet � parser
	 * @return la chaine de caratere
	 */
	protected String getTime(Calendar date, int marge)
	{
		date.add(Calendar.MINUTE, marge);
		String r = Integer.toString(date.get(Calendar.HOUR_OF_DAY)) + ":" + Integer.toString(date.get(Calendar.MINUTE))
				+ ":00.000";
		date.add(Calendar.MINUTE, -marge);
		return r;
	}
	
	/**
	 * renvoie un objet Calendar avec la date et l'heure stocker dans la base de donn�
	 * 
	 * @param rs : le ResultSet ou est recup�rer la date et l'heure
	 * @param columnName : le nom de la colone ou est stock� la date et l'heure
	 * @return l'objet Calendar
	 * @throws SQLException
	 */
	protected Calendar getDateTime(ResultSet rs, String columnName) throws SQLException
	{
		Calendar cal = new GregorianCalendar();
		Calendar cal2 = new GregorianCalendar();
		cal.setTime(rs.getDate(columnName));
		cal2.setTime(rs.getTime(columnName));
		
		cal.set(Calendar.HOUR_OF_DAY, cal2.get(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal2.get(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal2.get(Calendar.SECOND));
		
		return cal;
	}	
}
