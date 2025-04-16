
import core.CsvToTurtle;
import java.io.File;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class QueGG {

    public static void main(String[] args) throws Exception {
        File inputFile=new File("dataset\\Paint_Kunffy.csv");
        File outputFile=new File("output\\"+inputFile.getName().replace(".csv", ".ttl"));
        CsvToTurtle csvToTurtle=new CsvToTurtle(inputFile,outputFile);
    }

}
