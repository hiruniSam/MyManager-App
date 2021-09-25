package com.example.mymanagerapp;

import java.util.Date;
import java.util.MissingResourceException;

public class ShortGoal {

    private String goalDes;
    private String dateS;


    public ShortGoal() {
    }

    public ShortGoal(String goalDes, String dateS) {
        this.goalDes = goalDes;
        this.dateS = dateS;
    }

    public String getGoalDes() {
        return goalDes;
    }

    public String getDateS() {
        return dateS;
    }

    public void setGoalDes(String goalDes) {
        this.goalDes = goalDes;
    }

    public void setDateS(String dateS) {
        this.dateS = dateS;
    }




}
