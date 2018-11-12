package domain;

public class CompetenceSponsors extends BaseEntity{
    public int sponsor;
    public int competence;

    public int getSponsor() {
        return sponsor;
    }

    public int getCompetence() {
        return competence;
    }
}