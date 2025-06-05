
import utils.CsvToTurtle;
import core.Prefixes;
import graphdb.Entity;
import graphdb.Neo4jExecution;
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


    private static String uri = "bolt://localhost:7687"; // This points to the Docker-exposed Bolt port
    private static String user = "neo4j";
    private static String password = "password";
    //MATCH (n:Painting) RETURN n LIMIT 25;
    // RELATION

    public static void main(String[] args) {
        String menu = CREATE+"_"+RELATION;
        //menu = DELETE;
        //menu =RELATION;
        
        for (int i = 0; i < args.length; i++) {
            if(i==0){
               menu = args[0]; 
            }
        }
        
        String dir = "dataset/german/input/"; // path to your CSV file
        List<String> files = FileFolderUtils.getSpecificFiles(dir, "entity", ".csv");
        Neo4jExecution app = new Neo4jExecution(uri, user, password);

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
            app.createRelationship("KUNFFY_LAJOS", "BECKMANN_MAX", "Unknown");
        } if (menu.contains(DELETE)) {
            app.deleteAll();
        } if (menu.contains(CHECK)) {
            app.listNodes();
        }
        

    }

}
