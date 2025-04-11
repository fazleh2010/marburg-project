
import core.Tripple;
import java.io.File;
import java.util.List;
import lombok.NoArgsConstructor;
import utils.CsvFile;

@NoArgsConstructor
public class QueGG {

    public static void main(String[] args) throws Exception {
        File qaldFile=new File("dataset\\paint.csv");
        CsvFile csvFile=new CsvFile(qaldFile);
        List<String []>rows=csvFile.getRows(qaldFile);
        for(String[] row:rows){
            Tripple tripple=new Tripple(row);
            System.out.println(tripple.getSubject()+".."+tripple.getProperty()+".."+tripple.getObject()+".."+tripple.getDatatype());
        }

    }

}
