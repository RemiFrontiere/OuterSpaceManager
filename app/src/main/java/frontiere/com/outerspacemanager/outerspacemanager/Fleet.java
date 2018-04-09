package frontiere.com.outerspacemanager.outerspacemanager;

import java.util.List;

/**
 * Created by rfrontiere on 23/01/2018.
 */


public class Fleet {

    private int size;
    private List<Ship> ships;


    public Fleet(int sizeX, List<Ship> shipsX){
        this.size = sizeX;
        this.ships = shipsX;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

}
