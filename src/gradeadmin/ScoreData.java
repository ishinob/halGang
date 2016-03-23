/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradeadmin;

import java.io.Serializable;
import java.util.Arrays;

/**
 *
 * @author ishin
 */
public class ScoreData implements Serializable{
    private String ID;
    private String Name;
    private String[] score;

    public ScoreData() {
        score = new String[5];
    }
    
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String[] getScore() {
        return score;
    }

    public void setScore(String[] score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "ScoreData{" + "ID=" + ID + ", Name=" + Name + ", score=" 
                + Arrays.toString(score) + '}';
    }
    
}
