package core;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author elahi
 */
public class Tripple {

    private String subject = null;
    private String property = null;
    private String object = null;
    private String datatype = null;

    public Tripple(String[] row) {
        this.subject = row[0];
        this.property = row[1];
        this.object = row[2];
        this.datatype = row[3];
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
        return datatype;
    }

}
