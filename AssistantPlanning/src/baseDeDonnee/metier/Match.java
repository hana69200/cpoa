package baseDeDonnee.metier;

import java.util.Calendar;

import com.mindfusion.scheduling.model.Appointment;

public class Match extends Appointment
{

	/**
	 * durée d'un match en minutes
	 **/
	public static int DUREE_MATCH = 30;

	private Joueur participant1;
	private Joueur participant2;
	private int equipeArbitre;
	private Calendar date;
	private Score score;
	// TODOLATER equipes ramasseurs à ajouter

	/**
	 * @param participant1  : 1er participant
	 * @param participant2  : 2è participant
	 * @param equipeArbitre : equipe arbitrant le match
	 * @param date          : date et heure de début du match
	 * @param score         : score final du match
	 */
	public Match(Joueur participant1, Joueur participant2, int equipeArbitre, Calendar date, Score score)
	{
		this.participant1 = participant1;
		this.participant2 = participant2;
		this.equipeArbitre = equipeArbitre;
		this.date = date;
		this.score = score;
	}

	public Joueur getParticipant1()
	{
		return participant1;
	}

	public Joueur getParticipant2()
	{
		return participant2;
	}

	public int getEquipeArbitre()
	{
		return equipeArbitre;
	}

	public Calendar getDate()
	{
		return date;
	}

	public Score getScore()
	{
		return score;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + equipeArbitre;
		result = prime * result + ((participant1 == null) ? 0 : participant1.hashCode());
		result = prime * result + ((participant2 == null) ? 0 : participant2.hashCode());
		result = prime * result + ((score == null) ? 0 : score.hashCode());
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
		Match other = (Match) obj;
		if (date == null)
		{
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (equipeArbitre != other.equipeArbitre)
			return false;
		if (participant1 == null)
		{
			if (other.participant1 != null)
				return false;
		} else if (!participant1.equals(other.participant1))
			return false;
		if (participant2 == null)
		{
			if (other.participant2 != null)
				return false;
		} else if (!participant2.equals(other.participant2))
			return false;
		if (score == null)
		{
			if (other.score != null)
				return false;
		} else if (!score.equals(other.score))
			return false;
		return true;
	}

}
