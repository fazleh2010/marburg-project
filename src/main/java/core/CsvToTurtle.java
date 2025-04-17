/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core;

import java.io.File;
import java.util.List;
import utils.CsvFile;
import utils.FileFolderUtils;

/**
 *
 * @author elahi
 */
public class CsvToTurtle {

    public CsvToTurtle(File inputFile, File outputFile) {
        CsvFile csvFile = new CsvFile(inputFile);
        List<String[]> rows = csvFile.getRows(inputFile);
        String str = "";
        Integer index = 0;
        for (String[] row : rows) {
            if (index == 0) {
                index = index + 1;
                continue;
            }

            Tripple tripple = new Tripple(row);
            //System.out.println(tripple.getSubject() + ".." + tripple.getProperty() + ".." + tripple.getObject() + ".." + tripple.getDatatype());
            //System.out.println(tripple.getTripleElement());
            String line = tripple.getTripleElement() + "\n";
            str += line;
        }
        FileFolderUtils.stringToFile(str, outputFile);
        System.out.println(str);

    }

}
