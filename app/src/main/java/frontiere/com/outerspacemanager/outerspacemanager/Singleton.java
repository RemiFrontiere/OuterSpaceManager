package frontiere.com.outerspacemanager.outerspacemanager;

import java.util.List;

/**
 * Created by rfrontiere on 16/01/2018.
 */

public final class Singleton {

    private static volatile Singleton instance = null;

    private String identifiant;
    private String password;
    private String mail;
    private Integer score;

    private Integer myGaz;
    private Integer myMineral;

    private UserGet myUser;

    private String myToken = "";
    private List<Building> myBuildings;
    private List<User> myUsers;


    //private User targetUser;
    public int espionValueForAttack = 0;
    public int destroyerValueForAttack = 0;
    public int chasseurLegerValueForAttack = 0;
    public int chasseurLourdValueForAttack = 0;
    public int etoileMortLourdValueForAttack = 0;


//    private time


    private Singleton() {
        super();
    }

    public final static Singleton getInstance() {
        if (Singleton.instance == null) {
            synchronized(Singleton.class) {
                if (Singleton.instance == null) {
                    Singleton.instance = new Singleton();
                }
            }
        }
        return Singleton.instance;
    }

    public void setUser(String identifiant, String password) {
        if(identifiant != null && password != null){
            this.identifiant = identifiant;
            this.password = password;
            this.score = score;
        }
    }
    public void setUser(String identifiant, String password, String mail) {
        if(identifiant != null && password != null){
            this.identifiant = identifiant;
            this.password = password;
            this.score = score;
            this.mail = mail;
        }
    }

    public void setMyToken(String token) {
        if(token != null){
            this.myToken = token;
        }
    }
    public Integer getMyMineral() {
        return this.myMineral;
    }


    public void setMyMineral(Integer mineral) {
        if(mineral != null){
            this.myMineral = myMineral;
        }
    }

    public UserGet getMyUser() {
        return this.myUser;
    }


    public void setMyUser(UserGet user) {
        if(user != null){
            this.myUser = user;
        }
    }

    public Integer getMygaz() {
        return this.myGaz;
    }

    public void setMygaz(Integer gaz) {
        if(gaz != null){
            this.myGaz = gaz;
        }
    }
    public String getMyToken() {
        return this.myToken;
    }

    public String getIdentifiant() {
        return this.identifiant;
    }


    public List<Building> getMyBuildings() {
        return myBuildings;
    }

    public void setMyBuildings(List<Building> myBuildings) {
        this.myBuildings = myBuildings;
    }


    public List<User> getMyUsers() {
        return myUsers;
    }

    public void setMyUsers(List<User> myUsers) {
        this.myUsers = myUsers;
    }


}