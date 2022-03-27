package com.example.rockets3withapi;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
public class Propellant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int maxPower;
    private int actualPower;
    @ManyToOne
    @JsonBackReference
    private Rocket rocket;

    public Propellant() {
    }

    public Propellant(int maxPower) throws Exception {
        checkPower(maxPower);
        this.maxPower = maxPower;
        this.actualPower = 0;
    }

    private void checkPower(int maxPower) throws  Exception{
        if(maxPower <= 0) throw new Exception("The propellant must have maximum power");
    }

    public int getMaxPower() {
        return maxPower;
    }


    public void updateActualPower(int actualPower) {
        this.actualPower += actualPower;
        if(this.actualPower > maxPower){
            this.actualPower = maxPower;
        }
        else if(this.actualPower < 0){
            this.actualPower = 0;
        }
    }

    public int getActualPower() {
        return actualPower;
    }

    public Long getId() {
        return id;
    }

    public void setMaxPower(int maxPower) throws Exception {
        checkPower(maxPower);
        this.maxPower = maxPower;
    }
    public void setActualPower(int actualPower) {
        this.actualPower = actualPower;
    }

    public Rocket getRocket() {
        return rocket;
    }

    public void setRocket(Rocket rocket) {
        this.rocket = rocket;
    }
}
