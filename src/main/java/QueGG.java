
import core.CsvToTurtle;
import core.Prefixes;
import java.io.File;
import java.util.List;
import lombok.NoArgsConstructor;
import utils.FileUtils;

@NoArgsConstructor
public class QueGG {

    public static void main(String[] args) throws Exception  {
        String entityDir="dataset/english/";
        String entityFileType=".csv";
        List<File> files=FileUtils.getSpecificFiles(entityDir,Prefixes.ENTITY,entityFileType);
        for(File inputFile: files){
            File outputFile=new File(entityDir+"output_"+inputFile.getName().replace(".csv", ".ttl"));
            CsvToTurtle csvToTurtle=new CsvToTurtle(inputFile,outputFile);
        }
        //File inputFile=new File("dataset/english/entity_1.csv");
        
    }

}
