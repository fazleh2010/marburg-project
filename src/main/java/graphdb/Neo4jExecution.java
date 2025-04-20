/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphdb;

import java.io.IOException;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.TransactionWork;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.neo4j.driver.Result;
import org.neo4j.driver.Value;

import static org.neo4j.driver.Values.parameters;

public class Neo4jExecution implements AutoCloseable {

    private final Driver driver;
    //MATCH (n:Painting) RETURN n LIMIT 25;

    public Neo4jExecution(String uri, String user, String password) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    @Override
    public void close() {
        driver.close();
    }

    public void createNodeWithProperties(String label, Map<String, Object> properties) {
        try (Session session = driver.session()) {
            session.writeTransaction((TransactionWork<Void>) tx -> {
                String cypher = String.format("CREATE (n:%s) SET n += $props", label);
                tx.run(cypher, parameters("props", properties));
                return null;
            });
            System.out.println("Node created with label: " + label + " and properties: " + properties);
        }
    }

    public void deleteAll() {
        try (Session session = driver.session()) {
            session.writeTransaction(tx -> {
                tx.run("MATCH (n) DETACH DELETE n");
                return null;
            });
            System.out.println("All nodes and relationships deleted.");
        }
    }

    public void listNodes() {
        try (Session session = driver.session()) {
            Result result = session.run("MATCH (n) RETURN n");

            while (result.hasNext()) {
                org.neo4j.driver.Record record = result.next();
                Value nodeValue = record.get("n");
                Map<String, Object> properties = nodeValue.asNode().asMap();

                System.out.println("Node Label(s): " + nodeValue.asNode().labels());
                System.out.println("Properties: " + properties);
                System.out.println("------------");
            }
        }
    }

    public static void main(String[] args) {
        String csvPath = "dataset/german/entity_1.csv"; // path to your CSV file
        Map<String, Object> properties = new HashMap<>();

        try {
            Entity entity = new Entity(csvPath);
            properties = entity.getProperties();
            System.out.println(entity);
            Neo4jExecution app = new Neo4jExecution("bolt://localhost:7687", "neo4j", "password");
            app.createNodeWithProperties(entity.getNodeType(), properties);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
