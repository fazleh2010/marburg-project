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

import static org.neo4j.driver.Values.parameters;


/**
 *
 * @author melahi
 */
public class CSVToNeo4j implements AutoCloseable {
    private final Driver driver;

    public CSVToNeo4j(String uri, String user, String password) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    @Override
    public void close() {
        driver.close();
    }

    public void importCSV(String csvFilePath) throws IOException {
        try (
            FileReader reader = new FileReader(Paths.get(csvFilePath).toFile());
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
            Session session = driver.session()
        ) {
            for (CSVRecord record : csvParser) {
                String name = record.get("name");
                int age = Integer.parseInt(record.get("age"));
                String email = record.get("email");

                session.writeTransaction(tx -> {
                    tx.run(
                        "CREATE (p:Person {name: $name, age: $age, email: $email})",
                        parameters("name", name, "age", age, "email", email)
                    );
                    return null;
                });

                System.out.println("Created node for: " + name);
            }
        }
    }

    public static void main(String[] args) {
        String csvPath = "dataset/english/people.csv"; // path to your CSV file

        try (CSVToNeo4j importer = new CSVToNeo4j("bolt://localhost:7687", "neo4j", "password")) {
            importer.importCSV(csvPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

