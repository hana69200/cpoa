package baseDeDonnee.metier;

import java.util.Calendar;

public class Entrainement
{
	public static int DUREE_RESERVATION = 60;
	
	private Joueur joueur;
	private Calendar date;
	private int numeroCours;
	
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
