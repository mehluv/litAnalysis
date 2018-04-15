import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrelimAnalysis {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("SampleChapter.txt"));
        //TODO: Replace with map of person to list of dialogues spoken by them
        List<String> listOfDialogue = new ArrayList<>();
        //System.out.println(br.lines().filter(el -> el.equals("")).count());
        String line = br.readLine();
        while(line != null) {
            if(!line.equals("")) {
                Matcher m = Pattern.compile("\"[A-Za-z,.?!'\\- ]+\"").matcher(line);
                while (m.find()) {
                    for (int i = 0; i <= m.groupCount(); i++) {
                        listOfDialogue.add(m.group(i));
                    }
                }
            }
            line = br.readLine();
        }
        System.out.println(listOfDialogue);
    }
}
