/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphdb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import static org.neo4j.driver.GraphDatabase.driver;
import org.neo4j.driver.Session;
import static org.neo4j.driver.Values.parameters;

/**
 *
 * @author melahi
 */
public class Entity {

    private final String Subject = "Subject";
    private final String Property = "Property";
    private final String Object = "Object";
    private final String SubjectType = "SubjectType";
    private final String PropertyTpe = "PropertyTpe";
    private final String PropertyCategory = "PropertyCategory";
    private final String ObjectType = "ObjectType";
    private String subject = null;
    private String nodeType = null;
    private LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
    //private List<Property> properties = new ArrayList<Property>();

    public Entity(String csvFilePath) throws FileNotFoundException, IOException {

        FileReader reader = new FileReader(Paths.get(csvFilePath).toFile());
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
        for (CSVRecord record : csvParser) {
            this.subject = record.get(Subject);
            this.nodeType = record.get(SubjectType);
            Property property = new Property(record.get(Property), record.get(Object), record.get(PropertyTpe),record.get(PropertyCategory), record.get(ObjectType));
            //String propertyJoin=property.getPropertyCategory()+"("+property.getProperty()+")";
            this.properties.put(property.getProperty(), property.getObject());
        }
        this.properties.put("name",this.subject);
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public String getSubject() {
        return subject;
    }

    public String getNodeType() {
        return nodeType;
    }

    @Override
    public String toString() {
        return "Entity{" + "subject=" + subject + ", nodeType=" + nodeType + ", properties=" + properties + '}';
    }

}
