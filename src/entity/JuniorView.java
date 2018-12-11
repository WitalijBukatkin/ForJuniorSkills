package entity;

public class JuniorView extends Junior {
    public String login;
    public String password;
    public String sponsors;
    public String competenceName;
    public String countryName;

    public String getCompetenceName() {
        return competenceName;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getSponsors() {
        return sponsors;
    }
}