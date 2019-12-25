package baseDeDonnee.metier;

import java.util.Calendar;

import com.mindfusion.scheduling.model.Appointment;

public class Match extends Appointment
{
	private Joueur participant1;
	private Joueur participant2;
	private int equipeArbitre;
	private Calendar date;
	private Score score;

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
	
}
