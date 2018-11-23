package dao.entity;

public class CompetenceExpert extends BaseEntity {
    public Integer competence;
    public Integer expert;

    public Integer getCompetence() {
        return competence;
    }

    public Integer getExpert() {
        return expert;
    }
}