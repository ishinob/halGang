/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradeadmin;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author ishin
 */
public class ScoreData implements Serializable{
    private String ID;
    private String Name;
    private List<String> Score;
    
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

    public List<String> getScore() {
        return Score;
    }

    public void setScore(List<String> Score) {
        this.Score = Score;
    }

    @Override
    public String toString() {
        return "ScoreData{" + "ID=" + ID + ", Name=" + Name + ", Score=" + Score + '}';
    }

    
    
    
}
