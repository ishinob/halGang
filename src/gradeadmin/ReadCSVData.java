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
            String str;
            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, ",");
                
                ScoreData sd = new ScoreData();
                List<String> data = new ArrayList<>();
                int i = 0;
                while (st.hasMoreTokens()) {
                    // 1行の各要素をタブ区切りで表示
                    str = st.nextToken();
                    switch(i){
                        case 0:
                            sd.setID(str);
                            break;
                        case 1:
                            sd.setName(str);
                            break;
                        default:
                            data.add(str);
                    }
                }
                sd.setScore(data);
                csvData.add(sd);
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
