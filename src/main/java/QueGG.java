
import utils.CsvToTurtle;
import core.Prefixes;
import graphdb.Entity;
import graphdb.Neo4jExecution;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import lombok.NoArgsConstructor;
import utils.FileFolderUtils;

@NoArgsConstructor
public class QueGG {

    private static String uri = "bolt://localhost:7687"; // This points to the Docker-exposed Bolt port
    private static String user = "neo4j";
    private static String password = "password";

    public static void main(String[] args) {
        String menu = Tasks.CREATE;
        String csvPath = "dataset/german/entity_1.csv"; // path to your CSV file
        Neo4jExecution app = new Neo4jExecution(uri, user, password);

        try {
            if (menu.contains(Tasks.CREATE)) {
                try {
                    Entity entity = new Entity(csvPath);
                    Map<String, Object> properties = entity.getProperties();
                    System.out.println(entity);
                    app.createNodeWithProperties(entity.getNodeType(), properties);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            } else if (menu.contains(Tasks.DELETE)) {
                app.deleteAll();
            } else if (menu.contains(Tasks.CHECK)) {
                app.listNodes();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
