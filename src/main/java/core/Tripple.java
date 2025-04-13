package core;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author elahi
 */
public class Tripple implements Prefixes{

    private String subject = null;
    private String property = null;
    private String object = null;
    private String datatype = null;
    private String tripleElement = null;
    
    
    public Tripple(String[] row) {
        this.subject = this.addUriSyntax(ENTITY,this.removeSpace(row[0]));
        this.property = this.addUriSyntax(PROPERTY,this.removeSpace(row[1]));
        this.object = this.removeSpace(row[2]);
        this.datatype = this.removeSpace(row[3]);
        if(this.datatype.contains("xsd:string")){
            String prefix="X";
            this.object=this.addUriSyntax(prefix,this.object);
        }
        this.tripleElement=this.subject+" "+this.property+" "+this.object+".";
            
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
    
    public String getTripleElement() {
        return tripleElement;
    }
   
    private String removeSpace(String string) {
        return string.replace(" ", "_");
    }

    private String addUriSyntax(String prefix,String value) {
        return "<"+prefix+value+">";
    }

}
