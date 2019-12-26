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

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((joueur == null) ? 0 : joueur.hashCode());
		result = prime * result + numeroCours;
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entrainement other = (Entrainement) obj;
		if (date == null)
		{
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (joueur == null)
		{
			if (other.joueur != null)
				return false;
		} else if (!joueur.equals(other.joueur))
			return false;
		if (numeroCours != other.numeroCours)
			return false;
		return true;
	}
	
}
