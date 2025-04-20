package utils;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.TransactionWork;
import org.neo4j.driver.Result;
import org.neo4j.driver.Value;

import static org.neo4j.driver.Values.parameters;

public class Neo4jExTrans implements AutoCloseable {
    private final Driver driver;

    public Neo4jExTrans(String uri, String user, String password) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    @Override
    public void close() throws Exception {
        driver.close();
    }

    public void createNode(String attribute,final String attributeValue) {
        try (Session session = driver.session()) {
            session.writeTransaction(new TransactionWork<Void>() {
                @Override
                public Void execute(Transaction tx) {
                    tx.run("CREATE (a:Person {"+attribute+": $"+attribute+"})", getParameter(attribute, attributeValue));
                    System.out.println("Node created with name: " + attributeValue);
                    return null;
                }

                private Value getParameter(String attribute, String value) {
                     return parameters(attribute,attributeValue); 
                }
            });
        }
    }

    public static void main(String[] args) {
        String attribute="name";
        try (Neo4jExTrans app = new Neo4jExTrans("bolt://localhost:7687", "neo4j", "password")) {
            app.createNode(attribute,"test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
