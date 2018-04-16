package frontiere.com.outerspacemanager.outerspacemanager;

import java.util.List;

/**
 * Created by rfrontiere on 23/01/2018.
 */

public class Users{

    private List<UserG> users;

    public Users(List<UserG> users){
        this.users = users;
    }

    public List<UserG> getUsers() {
        return this.users;
    }

    public void setUsers(List<UserG> users) {
        this.users = users;
    }

}