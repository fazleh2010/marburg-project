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
public class Neo4jExample implements Tasks {

    private static String uri = "bolt://localhost:7687"; // This points to the Docker-exposed Bolt port
    private static String user = "neo4j";
    private static String password = "password";

    public Neo4jExample() {
    }

    public static void main(String[] args) {
        String menu = Tasks.DELETE;
        CSVToNeo4j importer = new CSVToNeo4j();
        String csvPath = "dataset/english/people.csv"; // path to your CSV file

        try {
            if (menu.contains(Tasks.CREATE)) {
                String nodeType = "Person";
                importer.createNodeFromCsv(csvPath, nodeType);

            } else if (menu.contains(Tasks.DELETE)) {
                importer.deleteAll();
            }
            else if (menu.contains(Tasks.CHECK)) {
                importer.listNodes();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*else if (menu.contains(CHECK)) {
            neo4jExample.checkNodes();
        }*/
    }

    /*public void executeQuery(String menu, CypherQuery createCypher) {
        String cypherQuery = createCypher.getCreateNodeStr();
        Value value = createCypher.getCreateNodeValue();

        try (Session session = driver.session()) {
            // Execute the query with parameters
            Result result = session.run(cypherQuery, value);
            // Optional: print the result
            org.neo4j.driver.Record record = result.single();
            if (menu.contains(CREATE)) {
                System.out.println("Created node successfully!!!");
            }
            if (menu.contains(DELETE)) {
                System.out.println("Node deleted successfully!!!");
            }
        } catch (ClientException e) {
            System.out.println("Failed !! " + e.getMessage());
            e.printStackTrace();
        }

    }

    public void checkNodes() {

        try (Session session = driver.session()) {
            String cypherQuery = "MATCH (n) RETURN n";
            Result result = session.run(cypherQuery);

            while (result.hasNext()) {
                org.neo4j.driver.Record record = result.next();
                Value node = record.get("n");
                System.out.println(node.asNode().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    

}
