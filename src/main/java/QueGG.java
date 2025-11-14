
import utils.CsvToTurtle;
import core.Prefixes;
import graphdb.Entity;
import graphdb.Neo4j;
import java.io.File;
import java.io.IOException;
import static java.lang.System.exit;
import java.util.List;
import java.util.Map;
import lombok.NoArgsConstructor;
import utils.FileFolderUtils;

@NoArgsConstructor
public class QueGG {
    
    public static String CREATE = "CREATE";
    public static String DELETE = "DELETE";
    public static String RELATION = "RELATION";
    public static String CHECK = "CHECK";


      //MATCH (n:Painting) RETURN n LIMIT 25;
    // RELATION

    public static void main(String[] args) {
    // Default Neo4j connection info
    String uri = "bolt://neo4j:7687";
    String user = "neo4j";
    String password = "password";
    String dir = "dataset/german/input/"; // default CSV path
    String menu = "CREATE"; // default action

    // Parse arguments
    // args[0] = menu, args[1] = CSV dir, args[2] = URI, args[3] = user, args[4] = password
    if (args.length >= 1) menu = args[0];
    if (args.length >= 2) dir = args[1];
    if (args.length >= 3) uri = args[2];
    if (args.length >= 4) user = args[3];
    if (args.length >= 5) password = args[4];

    List<String> files = FileFolderUtils.getSpecificFiles(dir, "entity", ".csv");
    Neo4j app = new Neo4j(uri, user, password);

 

        if (menu.contains(CREATE)) {
            for (String csvPath : files) {
                if(csvPath.contains(".~lock.")){
                 continue;   
                }
                System.out.println(csvPath);
                Entity entity = new Entity(csvPath);
                Map<String, Object> properties = entity.getProperties();
                System.out.println(entity);
                app.createNodeWithProperties(entity.getNodeType(), properties);
            }

        } if (menu.contains(RELATION)) {
            app.createRelationship(Entity.OBJECT_ID,"Book_1", "Painting_2", "containsPainting");
        } if (menu.contains(DELETE)) {
            app.deleteAll();
        } if (menu.contains(CHECK)) {
            app.listNodes();
        }
        

    }

}
