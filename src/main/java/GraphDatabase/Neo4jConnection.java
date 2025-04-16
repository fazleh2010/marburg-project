/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GraphDatabase;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;

/**
 *
 * @author elahi
 */
public class Neo4jConnection {

    public static void main(String[] args) {
        // Create a driver instance
        Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("username", "password"));
        try (Session session = driver.session()) {
            // Your database operations go here
            example(session);
        }
        driver.close();
    }

    public static void example(Session session) {
String query = "MATCH (n) RETURN n LIMIT 10";
Result result = session.run(query);
while (result.hasNext()) {
    Record record = (Record) result.next();
    System.out.println(record.get("n"));
}
    }
}
