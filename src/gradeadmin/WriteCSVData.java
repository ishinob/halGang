/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradeadmin;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ishin
 */
public class WriteCSVData {

    public void writeScoreData(List<ScoreData> csvData) {

        try {
            FileWriter fw = new FileWriter("data.csv", false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            for (int i = 0; i < csvData.size(); i++) {
                pw.print(csvData.get(i).getID());
                pw.print(",");
                pw.print(csvData.get(i).getName());
                pw.print(",");
                
                for (int j = 0; j < 5; j++) {
                    pw.print(csvData.get(i).getScore()[j]);
                    if (j == 4) {
                        pw.println();
                    } else {
                        pw.print(",");
                    }
                }

            }
            pw.close();
        } catch (IOException ex) {
            Logger.getLogger(WriteCSVData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
