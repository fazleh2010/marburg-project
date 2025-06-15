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

public class Neo4j implements AutoCloseable {

    private final Driver driver;

    public Neo4j(String uri, String user, String password) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    @Override
    public void close() {
        driver.close();
    }

    public void createNodeWithProperties(String nodeType, Map<String, Object> properties) {
        try (Session session = driver.session()) {
            session.writeTransaction((TransactionWork<Void>) tx -> {
                String cypher = String.format("CREATE (n:%s) SET n += $props", nodeType);
                tx.run(cypher, parameters("props", properties));
                return null;
            });
            System.out.println("Node created with label: " + nodeType + " and properties: " + properties);
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

    public void createRelationship(String objectID,String name1, String name2, String relationshipType) {
        try (Session session = driver.session()) {
            session.writeTransaction((TransactionWork<Void>) tx -> {
                tx.run(
                        "MATCH (a:Book {"+objectID+": $name1}), (b:Painting {"+objectID+": $name2}) "
                        + "MERGE (a)-[r:" + relationshipType + "]->(b)",
                        parameters("name1", name1, "name2", name2)
                );
                return null;
            });
        }
    }
    /*
    public void createRelationship(String attributeName,String value1, String value2, String node1, String node2,String relationshipType) {
        try (Session session = driver.session()) {
            session.writeTransaction((TransactionWork<Void>) tx -> {
                tx.run("MATCH (a:"+node1+" {"+attributeName+": $name1}), (b:"+node2+" {"+attributeName+": $name2}) "
                        + "MERGE (a)-[r:" + relationshipType + "]->(b)",
                        parameters("name1", value1, "name2", value2)
                );
                return null;
            });
        }
    }

    */

    public static void main(String[] args) {
        String csvPath = "dataset/german/entity_1.csv"; // path to your CSV file
        Map<String, Object> properties = new HashMap<>();

        Entity entity = new Entity(csvPath);
        properties = entity.getProperties();
        System.out.println(entity);
        Neo4j app = new Neo4j("bolt://localhost:7687", "neo4j", "password");
        app.createNodeWithProperties(entity.getNodeType(), properties);
        //app.createRelationship("KUNFFY_LAJOS", "BECKMANN_MAX", "KNOWS");
    }

}
