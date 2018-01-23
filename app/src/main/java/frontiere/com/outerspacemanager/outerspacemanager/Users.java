package frontiere.com.outerspacemanager.outerspacemanager;

import java.util.List;

/**
 * Created by rfrontiere on 23/01/2018.
 */

public class Users{

    private List<User> users;

    public Users(List<User> users){
        this.users = users;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}