import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrelimAnalysis {
    static final String TOM_CRUISE = "Tom Cruise";
    static final String MAGLU = "Maglu";
    static final String PUGSY = "Pugsy";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("SampleChapter.txt"));
        List<String> characters = new ArrayList<>();
        characters.add(TOM_CRUISE);
        characters.add(MAGLU);
        characters.add(PUGSY);
        Map<String, List<String>> mapOfDialogue = new HashMap<>();
        characters.forEach(character -> mapOfDialogue.put(character, new ArrayList<>()));
        //System.out.println(br.lines().filter(el -> el.equals("")).count());
        String line = br.readLine();
        Deque<String> lastTwoReferencedCharacters = new ArrayDeque<>();
        while (line != null) {
            if (!line.equals("")) {
                String referencedCharacter = null;
                //TODO: Make sure characters are recognized even if parts of their names are used
                //TODO: Accomodate nicknames
                for (String character : characters) {
                    if (line.contains(character)) {
                        referencedCharacter = character;
                        if(lastTwoReferencedCharacters.size() == 2) {
                            lastTwoReferencedCharacters.pop();
                        }
                        lastTwoReferencedCharacters.push(referencedCharacter);
                        break;
                    }
                }
                Matcher m = Pattern.compile("\"[A-Za-z,.?!'\\- ]+\"").matcher(line);
                while (m.find()) {
                    if(referencedCharacter == null) {
                        referencedCharacter = lastTwoReferencedCharacters.pop();
                        lastTwoReferencedCharacters.push(referencedCharacter);
                    }
                    mapOfDialogue.get(referencedCharacter).add(m.group());
                }
            }
            line = br.readLine();
        }
        mapOfDialogue.entrySet().forEach(entry -> {
            System.out.println("For character "+ entry.getKey());
            entry.getValue().forEach(dialogue -> System.out.println("dialogue = " + dialogue));
        });
    }
}
