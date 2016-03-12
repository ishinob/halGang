/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradeadmin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ishin
 */
public class ReadCSVData {
    private final File csvFile;
    private List<ScoreData> csvData;
    
    public ReadCSVData(File csvFile){
        this.csvFile = csvFile;
    }
    
    public List<ScoreData> readScoreData(){
        csvData = new ArrayList<>();
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, ",");
                
                String[] data = new String[7];
                ScoreData sd = new ScoreData();
                int i = 0;
                while (st.hasMoreTokens()) {
                    // 1行の各要素をタブ区切りで表示
                    sd.setID(line);
                    data[i] = st.nextToken();
                    i++;
                }

               
            }
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadCSVData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReadCSVData.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return csvData;
    }
}
