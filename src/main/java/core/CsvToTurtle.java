/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core;

import java.io.File;
import java.util.List;
import utils.CsvFile;

/**
 *
 * @author elahi
 */
public class CsvToTurtle {
    
    public CsvToTurtle(File qaldFile){
        CsvFile csvFile=new CsvFile(qaldFile);
        List<String []>rows=csvFile.getRows(qaldFile);
        String str="";
        for(String[] row:rows){
            Tripple tripple=new Tripple(row);
            System.out.println(tripple.getSubject()+".."+tripple.getProperty()+".."+tripple.getObject()+".."+tripple.getDatatype());
                    System.out.println(tripple.getTripleElement());
            String line=tripple.getTripleElement()+"\n";
            str+=line;
        }
       System.out.println(str);
        
    }
    
}
