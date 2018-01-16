package frontiere.com.outerspacemanager.outerspacemanager;

/**
 * Created by rfrontiere on 16/01/2018.
 */

public final class Singleton {

    private static volatile Singleton instance = null;

    private String identifiant;
    private String password;
    private Integer score;

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

    public String getIdentifiant() {
        return this.identifiant;
    }

//    public Integer getScore() {
//        return this.score;
//    }
}