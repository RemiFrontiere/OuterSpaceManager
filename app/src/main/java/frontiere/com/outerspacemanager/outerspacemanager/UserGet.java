package frontiere.com.outerspacemanager.outerspacemanager;

import java.util.List;

/**
 * Created by rfrontiere on 13/03/2018.
 */


public class UserGet {
      private String username;
      private Integer points;
      private Double gas;
      private Double minerals;
      private Integer gazModifier;
      private Integer mineralsModifier;


      public UserGet(String userName, Integer pts, Double gazValue, Double mineralsValue, Integer gazModifierValue, Integer mineralsModifierValue){
          this.username = userName;
          this.points = pts;
          this.gas = gazValue;
          this.minerals = mineralsValue;
          this.gazModifier = gazModifierValue;
          this.mineralsModifier = mineralsModifierValue;
      }

    public String getUsername() {
        return this.username;
    }
    public void setUsername(String usernameValue) {
        this.username = usernameValue;
    }

    public Integer getPoints() {
        return this.points;
    }
    public void setPoints(Integer pointsValue) {
        this.points = pointsValue;
    }

    public Double getGas() {
        return this.gas;
    }
    public void setGas(Double gasValue) {
        this.gas = gasValue;
    }

    public Double getMinerals() {
        return this.minerals;
    }
    public void setMinerals(Double mineralsValue) {
        this.minerals = mineralsValue;
    }

    public Integer getGasModifier() {
        return this.gazModifier;
    }
    public void setGasModifier(Integer gasModifierValue) {
        this.gazModifier = gasModifierValue;
    }

    public Integer getMineralsModifier() {
        return this.mineralsModifier;
    }
    public void setMineralsModifier(Integer mineralsModifierValue ) {
        this.mineralsModifier = mineralsModifierValue;
    }

}
