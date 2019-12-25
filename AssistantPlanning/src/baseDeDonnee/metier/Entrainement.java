package baseDeDonnee.metier;

import java.util.Calendar;

public class Entrainement
{
	/**
	durée d'une réservation en minutes
	**/
	public static int DUREE_RESERVATION = 60;
	
	private Joueur joueur;
	private Calendar date;
	private int numeroCours;
	
	/**
	 * @param joueur : joueur reservant le cours d'entrainement
	 * @param date : date et heure de début de la réservation
	 * @param numeroCours : numeros du cours réservé
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
