package dao.entity;

public class CompetenceSponsor extends BaseEntity{
    public Integer sponsor;
    public Integer competence;

    public Integer getSponsor() {
        return sponsor;
    }

    public Integer getCompetence() {
        return competence;
    }
}