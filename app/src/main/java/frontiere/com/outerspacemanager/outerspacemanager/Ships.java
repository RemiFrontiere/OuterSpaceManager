package frontiere.com.outerspacemanager.outerspacemanager;

import java.util.List;

/**
 * Created by rfrontiere on 23/01/2018.
 */

public class Ships {

    private double currentUserMinerals;
    private double currentUserGas;
    private Integer size;
    private List<Ship> ships;


    public Ships(double currentUserMinerals, double currentUserGas, Integer size, List<Ship> ships){
        this.currentUserGas = currentUserGas;
        this.currentUserMinerals  = currentUserMinerals;
        this.size = size;
        this.ships = ships;
    }


    public double getCurrentUserMinerals() {
        return currentUserMinerals;
    }

    public void setCurrentUserMinerals(double currentUserMinerals) {
        this.currentUserMinerals = currentUserMinerals;
    }

    public double getCurrentUserGas() {
        return currentUserGas;
    }

    public void setCurrentUserGas(double currentUserGas) {
        this.currentUserGas = currentUserGas;
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

class Ship{

    private Integer gasCost;
    private Integer life;
    private Integer maxAttack;
    private Integer minAttack;
    private Integer mineralCost;
    private String name;
    private Integer shield;
    private Integer shipId;
    private Integer spatioportLevelNeeded;
    private Integer speed;
    private  Integer timeToBuild;

    public Ship(
            Integer gasCost,
            Integer life,
            Integer maxAttack,
            Integer minAttack,
            Integer mineralCost,
            String name,
            Integer shield,
            Integer shipId,
            Integer spatioportLevelNeeded,
            Integer speed,
            Integer timeToBuild
    ){
        this.gasCost = gasCost;
        this.life = life;
        this.maxAttack = maxAttack;
        this.minAttack = minAttack;
        this.mineralCost = mineralCost;
        this.name = name;
        this.shield = shield;
        this.shipId = shipId;
        this.spatioportLevelNeeded = spatioportLevelNeeded;
        this.speed = speed;
        this.timeToBuild = timeToBuild;
    }




    public Integer getGasCost() {
        return gasCost;
    }

    public void setGasCost(Integer gasCost) {
        this.gasCost = gasCost;
    }

    public Integer getLife() {
        return life;
    }

    public void setLife(Integer life) {
        this.life = life;
    }

    public Integer getMaxAttack() {
        return maxAttack;
    }

    public void setMaxAttack(Integer maxAttack) {
        this.maxAttack = maxAttack;
    }

    public Integer getMinAttack() {
        return minAttack;
    }

    public void setMinAttack(Integer minAttack) {
        this.minAttack = minAttack;
    }

    public Integer getMineralCost() {
        return mineralCost;
    }

    public void setMineralCost(Integer mineralCost) {
        this.mineralCost = mineralCost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getShield() {
        return shield;
    }

    public void setShield(Integer shield) {
        this.shield = shield;
    }

    public Integer getShipId() {
        return shipId;
    }

    public void setShipId(Integer shipId) {
        this.shipId = shipId;
    }

    public Integer getSpatioportLevelNeeded() {
        return spatioportLevelNeeded;
    }

    public void setSpatioportLevelNeeded(Integer spatioportLevelNeeded) {
        this.spatioportLevelNeeded = spatioportLevelNeeded;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getTimeToBuild() {
        return timeToBuild;
    }

    public void setTimeToBuild(Integer timeToBuild) {
        this.timeToBuild = timeToBuild;
    }

}
