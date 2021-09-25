package com.example.mymanagerapp;

public class LongGoal {

    private String goalDesL;
    private String dateL;

    public LongGoal() {
    }

    public LongGoal(String goalDesL, String dateL) {
        this.goalDesL = goalDesL;
        this.dateL = dateL;
    }

    public String getGoalDesL() {
        return goalDesL;
    }

    public String getDateL() {
        return dateL;
    }

    public void setGoalDesL(String goalDesL) {
        this.goalDesL = goalDesL;
    }

    public void setDateL(String dateL) {
        this.dateL = dateL;
    }
}
