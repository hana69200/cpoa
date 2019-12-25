package baseDeDonnee.metier;

import com.mindfusion.scheduling.model.Appointment;

public class Match extends Appointment
{
	private Joueur participant1;
	private Joueur participant2;
	private int equipeArbitre;
	private Score score;

	public Match(Joueur participant1, Joueur participant2, int equipeArbitre, Score score)
	{
		this.participant1 = participant1;
		this.participant2 = participant2;
		this.equipeArbitre = equipeArbitre;
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

	public Score getScore()
	{
		return score;
	}
	
}
