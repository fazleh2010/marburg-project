package core;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import static utils.CsvConstants.OBJECT;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author elahi
 */
public class Tripple implements Prefixes {

    private String subject = null;
    private String property = null;
    private String object = null;
    private String propertyObject = null;
    private String datatypeObject = null;
    private String tripleElement = null;

    static {
        Prefixes.prefixes.put(ENTITY, "<"+baseUri+ENTITY+"/"+"XXX>");
        Prefixes.prefixes.put("GND", "<"+baseUri+PROPERTY+"/GMD/"+"XXX>");
        Prefixes.prefixes.put("DNB", "<"+baseUri+PROPERTY+"/DNB/"+"XXX>");
        Prefixes.prefixes.put("xsd:string", "\"XXX\"^^xsd:string");
        Prefixes.prefixes.put("xsd:integer", "\"XXX\"^^xsd:integer");
        Prefixes.prefixes.put("xsd:gYear", "\"XXX\"^^xsd:gYear");
        Prefixes.prefixes.put("xsd:date", "\"XXX\"^^xsd:date");
        Prefixes.prefixes.put("anyURI","<XXX>" );
       
    }

    public Tripple(String[] row) {
        this.subject = this.addUriSyntax(ENTITY,ENTITY,this.removeSpace(row[0]));
        this.property = this.addUriSyntax(PROPERTY,this.removeSpace(row[2]), this.removeSpace(row[1]));
        this.object = this.addUriSyntax(OBJECT,this.removeSpace(row[4]), removeSpace(row[3]));
        this.tripleElement = this.subject + " " + this.property + " " + this.object + " .";

    }

    private String addUriSyntax(String type,String key, String value) {
        String prefixShort = key;
       
        if (type.contains(ENTITY) || type.contains(PROPERTY)) {
            if (Prefixes.prefixes.containsKey(prefixShort)) {
                prefixShort = Prefixes.prefixes.get(prefixShort);
            }
        }
        if (type.contains(OBJECT)) {
            if (Prefixes.prefixes.containsKey(prefixShort)) {
                prefixShort = Prefixes.prefixes.get(prefixShort);
            }
        }
        

        return   prefixShort.replace("XXX", value) ;
    }

    public String getSubject() {
        return subject;
    }

    public String getProperty() {
        return property;
    }

    public String getObject() {
        return object;
    }

    public String getDatatype() {
        return datatypeObject;
    }

    public String getTripleElement() {
        return tripleElement;
    }

    private String removeSpace(String string) {
        return string.replace(" ", "_");
    }

}
