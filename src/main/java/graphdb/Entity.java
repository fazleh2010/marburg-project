/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphdb;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.*;
import org.apache.commons.csv.*;

/**
 *
 * @author melahi
 */
public final class Entity {

    public static final String PROPERTY = "Property";
    public static  final String OBJECT = "text";
    public static  final String NAME = "Name";
    public static  final String NODE_TYPE = "nodeType";
    public static  final String TITEL = "Titel";
    public static  final String OBJECT_ID = "Objekt_ID";
    public static  final String Painting = "Painting";
    public static  final String Book = "Book";
    private static Integer INDEX = 1;
    private String object_id = null;
    private String subject = null;
    private String nodeType = null;
    private LinkedHashMap<String, Object> properties = new LinkedHashMap<>();

    public Entity(String csvFilePath) {
        this.findSubjectAndType(csvFilePath);
        try {
            FileReader reader = new FileReader(Paths.get(csvFilePath).toFile());
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
            for (CSVRecord record : csvParser) {
                Property property = new Property(record.get(PROPERTY), record.get(OBJECT));
                this.properties.put(property.getProperty(), property.getObject());
            }
            //System.out.println(this.subject+" "+this.nodeType+" "+this.object_id);
            //System.out.println( this.properties.keySet());
            this.properties.put(NAME, this.subject);
            this.properties.put(NODE_TYPE, this.nodeType);
            //this.properties.put(OBJECT_ID, this.object_id);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Entity.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Entity.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void findSubjectAndType(String csvFilePath) {
        String id = null;
        try {
            FileReader reader = new FileReader(Paths.get(csvFilePath).toFile());
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
            for (CSVRecord record : csvParser) {
                String property = record.get(PROPERTY);
                String object = record.get(OBJECT);

                if (property.contains(TITEL)) {
                    this.subject = object;
                } else if (property.contains(NODE_TYPE)) {
                    this.nodeType = object;
                    /*if (object.contains(Painting)) {
                        Integer number = INDEX + 1;
                        this.object_id = object + "_" + number.toString();
                        INDEX = number;
                    }*/

                }

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Entity.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Entity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getNodeType() {
        return nodeType;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public String getSubject() {
        return subject;
    }

    @Override
    public String toString() {
        return "Entity{" + "Property=" + PROPERTY + ", Object=" + OBJECT + ", name=" + NAME + ", subject=" + subject + ", properties=" + properties + '}';
    }

}
