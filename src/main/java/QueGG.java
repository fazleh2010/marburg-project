
import core.CsvToTurtle;
import core.Tripple;
import java.io.File;
import java.util.List;
import lombok.NoArgsConstructor;
import utils.CsvFile;

@NoArgsConstructor
public class QueGG {

    public static void main(String[] args) throws Exception {
        File qaldFile=new File("dataset\\paint.csv");
        CsvToTurtle csvToTurtle=new CsvToTurtle(qaldFile);
    }

}
