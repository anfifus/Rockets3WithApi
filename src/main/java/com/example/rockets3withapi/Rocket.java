package com.example.rockets3withapi;
//https://stackoverflow.com/questions/3325387/infinite-recursion-with-jackson-json-and-hibernate-jpa-issue/18288939#18288939
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Rocket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String code;
    @OneToMany(mappedBy = "rocket")
    @JsonManagedReference
    private final List<Propellant> propellantList = new ArrayList<>();

    public Rocket() {
    }

    public Rocket(String code) throws Exception {
        checkCode(code);
        this.code = code;
    }

    private void checkCode(String code) throws Exception {
        if (code.length() != 8) throw new Exception("The format of the code is incorrect");
    }

    public String getCode() {
        return code;
    }

    public String getNumOfPropellantWithMaxPower() {
        StringBuilder resultToShow = new StringBuilder();
        for (Propellant currentPropellant : propellantList) {
            resultToShow.append(currentPropellant.getMaxPower()).append(" ");
        }
        return resultToShow.toString();
    }

    public int getThePowerOfAllPropellant() {
        int resultToShow = 0;
        for (Propellant currentPropellant : propellantList) {
            resultToShow+= currentPropellant.getActualPower();
        }
        return resultToShow;
    }

    public List<Propellant> addPropellant(Propellant propellant) throws Exception {
       // for (int currentPropellantPotency : propellant) {
            propellant.setRocket(this);
            propellantList.add(propellant);

      //  } 30,
        //        80
        return propellantList;
    }
    public void increasePower(){
        for (Propellant currentPropellant:propellantList) {
            currentPropellant.setRocket(this);
            currentPropellant.updateActualPower(10);
        }
    }
    public void decreasePower(){
        for (Propellant currentPropellant:propellantList) {
            currentPropellant.updateActualPower(-10);
        }
    }

    public Long getId() {
        return id;
    }

    public void setCode(String code) throws Exception {
        checkCode(code);
        this.code = code;
    }

    public List<Propellant> getPropellantList() {
        return propellantList;
    }
}
