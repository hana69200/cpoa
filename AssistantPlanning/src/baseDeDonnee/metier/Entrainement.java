package baseDeDonnee.metier;

import java.util.Calendar;

public class Entrainement
{
	/**
	dur�e d'une r�servation en minutes
	**/
	public static int DUREE_RESERVATION = 60;
	
	private Joueur joueur;
	private Calendar date;
	private int numeroCours;
	
	/**
	 * @param joueur : joueur reservant le cours d'entrainement
	 * @param date : date et heure de d�but de la r�servation
	 * @param numeroCours : numeros du cours r�serv�
	 */
	public Entrainement(Joueur joueur, Calendar date, int numeroCours)
	{
		this.joueur = joueur;
		this.date = date;
		this.numeroCours = numeroCours;
	}

	public Joueur getJoueur()
	{
		return joueur;
	}

	public Calendar getDate()
	{
		return date;
	}

	public int getNumeroCours()
	{
		return numeroCours;
	}

	
}
