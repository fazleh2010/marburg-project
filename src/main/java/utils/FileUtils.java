/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author elahi
 */
public class FileUtils {

    public static List<File> getSpecificFiles(String propertyInputDir, String fileType,String fileExtension) {
        List<File> properties = new ArrayList<File>();
        File file = new File(propertyInputDir);
        String[] propertyFiles = file.list();

        for (String propertyFile : propertyFiles) {
            if (propertyFile.contains(fileType)&&propertyFile.contains(fileExtension)) {
                properties.add(new File(propertyInputDir+propertyFile));
            }
        }
        return properties;

    }

    public static void stringToFile(String content, String fileName)
            throws IOException {
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(content);
        writer.close();

    }

    public static String fileToString(String fileName) {
        InputStream is;
        String fileAsString = null;
        try {
            is = new FileInputStream(fileName);
            BufferedReader buf = new BufferedReader(new InputStreamReader(is));
            String line = buf.readLine();
            StringBuilder sb = new StringBuilder();
            while (line != null) {
                sb.append(line).append("\n");
                line = buf.readLine();
            }
            fileAsString = sb.toString();
            //System.out.println("Contents : " + fileAsString);
        } catch (Exception ex) {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

        return fileAsString;
    }

    public static Set<String> getSetFromFiles(String propertyFile) {
        Set<String> results = new TreeSet<String>();
        BufferedReader reader;
        String line = "";
        File file = new File(propertyFile);

        try {
            reader = new BufferedReader(new FileReader(propertyFile));
            while ((line = reader.readLine()) != null) {
                line = reader.readLine();
                results.add(line);
            }

            reader.close();

        } catch (Exception ex) {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

        return results;
    }
    
    
    public static Map<String, String> getHashFromFiles(String propertyFile) {
        Map<String, String> results = new TreeMap<String, String>();
        BufferedReader reader;
        String line = "";
        File file = new File(propertyFile);

        try {
            reader = new BufferedReader(new FileReader(propertyFile));
            while ((line = reader.readLine()) != null) {
                line = reader.readLine();
                 if (line!=null) {
                     ;
                 }
                 else 
                     continue;
                if (line.contains("=")) {
                    String[] info = line.split("=");
                    results.put(info[0], info[1]);
                }

            }

            reader.close();

        } catch (Exception ex) {
            System.out.println("need property file for making grep files!!");
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

        return results;
    }
    
    public static <T> Set<T> findCommonElements(List<T> first, List<T> second) {
        Set<T> common = new HashSet<>(first);
        common.retainAll(second);
        return common;
    }


}
