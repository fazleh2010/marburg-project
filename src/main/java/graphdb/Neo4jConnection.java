/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphdb;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Value;
import static org.neo4j.driver.Values.parameters;
import org.neo4j.driver.exceptions.ClientException;

/**
 *
 * @author elahi
 */
public class Neo4jConnection {

    public static void main(String[] args) {
        String uri = "bolt://localhost:7687"; // This points to the Docker-exposed Bolt port
        String user = "neo4j";
        String password = "password";
        Driver driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
        createNode(driver);

    }

    public static void createNode(Driver driver) {
        // Connect to Neo4j database

        try (Session session = driver.session()) {
            // Cypher query to add a node
            String cypher = "CREATE (p:Person {name: $name, age: $age}) RETURN p";
            Value value=parameters("name", "Bob", "age", 42);

            // Execute the query with parameters
            Result result = session.run(cypher, value);

            // Optional: print the result
            org.neo4j.driver.Record record = result.single();
            System.out.println("Created node: " + record.get("p"));
        }
        catch(ClientException exception) {
             System.out.println("Failed to add node!! " + exception.getMessage());
        }
        
        finally {
            driver.close();
        }
    }

}
