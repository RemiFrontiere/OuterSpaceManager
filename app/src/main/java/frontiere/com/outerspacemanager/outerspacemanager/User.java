package frontiere.com.outerspacemanager.outerspacemanager;

import java.io.Serializable;

/**
 * Created by rfrontiere on 23/01/2018.
 */


class User implements Serializable {
    private String email;
    private String username;
    private String password;
    private Integer score;

    public User(String username, Integer score) {
        this.username = username;
        this.score = score;
    }

    public User(String username, String password, String email) {
        this.email = email;
        this.username = username;
        this.password = password;
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Integer getScore() {
        return this.score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

}
