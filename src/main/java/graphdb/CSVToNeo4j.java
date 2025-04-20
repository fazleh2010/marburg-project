/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphdb;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.neo4j.driver.*;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;

import static org.neo4j.driver.Values.parameters;

/**
 *
 * @author melahi
 */
public class CSVToNeo4j implements AutoCloseable {

    private static String uri = "bolt://localhost:7687"; // This points to the Docker-exposed Bolt port
    private static String user = "neo4j";
    private static String password = "password";
    private static Driver driver;
    private final String NAME = "name";
    private final String AGE = "age";
    private final String EMAIL = "email";

    public CSVToNeo4j() {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    public void createNodeFromCsv(String csvFilePath, String NodeType) throws IOException {
        try (
                FileReader reader = new FileReader(Paths.get(csvFilePath).toFile()); CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader()); Session session = driver.session()) {
            for (CSVRecord record : csvParser) {
                String nameValue = record.get(NAME);
                int ageValue = Integer.parseInt(record.get(AGE));
                String emailValue = record.get(EMAIL);

                session.writeTransaction(tx -> {
                    tx.run("CREATE (p:" + NodeType + " {" + NAME + ": $" + NAME + ", " + AGE + ": $" + AGE + ", " + EMAIL + ": $" + EMAIL + "})",
                            parameters(NAME, nameValue, AGE, ageValue, EMAIL, emailValue)
                    );
                    return null;
                });

                System.out.println("Created node for: " + nameValue);
            }
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

    @Override
    public void close() {
        driver.close();
    }
}
