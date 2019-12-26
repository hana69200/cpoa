package baseDeDonnee.metier;

public class Score
{
	private int s1;
	private int s2;

	public Score(int s1, int s2)
	{
		this.s1 = s1;
		this.s2 = s2;
	}

	public Score(String score)
	{
		if (score.indexOf("-") != -1)
		{
			this.s1 = Integer.parseInt(score.substring(0, score.indexOf("-") - 1));
			this.s2 = Integer.parseInt(score.substring(score.indexOf("-") + 2));
		} else
		{
			s1 = 0;
			s2 = 0;
		}
	}

	public int getS1()
	{
		return s1;
	}

	public void setS1(int s1)
	{
		this.s1 = s1;
	}

	public int getS2()
	{
		return s2;
	}

	public void setS2(int s2)
	{
		this.s2 = s2;
	}

	@Override
	public String toString()
	{
		return s1 + " - " + s2;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + s1;
		result = prime * result + s2;
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
		Score other = (Score) obj;
		if (s1 != other.s1)
			return false;
		if (s2 != other.s2)
			return false;
		return true;
	}

}
