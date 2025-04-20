/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphdb;

/**
 *
 * @author melahi
 */
public class Property {

    private String property = null;
    private String object = null;
    private String propertyTpe = null;
    private String objectType = null;

    public Property(String property, String object, String propertyTpe, String objectType) {
        this.property = property;
        this.object = object;
        this.propertyTpe = propertyTpe;
        this.objectType = objectType;
    }

    public String getProperty() {
        return property;
    }

    public String getObject() {
        return object;
    }

    public String getPropertyTpe() {
        return propertyTpe;
    }

    public String getObjectType() {
        return objectType;
    }

    @Override
    public String toString() {
        return "Property{" + "property=" + property + ", object=" + object + ", propertyTpe=" + propertyTpe + ", objectType=" + objectType + '}';
    }

}
