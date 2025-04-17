/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphdb;

import org.neo4j.driver.Value;
import static org.neo4j.driver.Values.parameters;

/**
 *
 * @author melahi
 */
public class CypherQuery {

    private String query = null;
    private Value value = null;

    public void create() {
        this.query = "CREATE (p:Person {name: $name, age: $age}) RETURN p";
        this.value = parameters("name", "Bob", "age", 42);
    }

    public void delete() {
        this.query = "MATCH (p:Person {name: $name}) DELETE p";
        this.value = parameters("name", "Bob");
    }

    public String getCreateNodeStr() {
        return query;
    }

    public Value getCreateNodeValue() {
        return value;
    }

}
