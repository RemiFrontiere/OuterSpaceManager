package frontiere.com.outerspacemanager.outerspacemanager;

/**
 * Created by rfrontiere on 16/04/2018.
 */



class UserG {
    private String username;
    private Double points;

    public UserG(String username, Double points) {
        this.username = username;
        this.points = points;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getPoints() {
        return this.points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

}
