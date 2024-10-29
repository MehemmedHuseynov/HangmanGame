import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class WordDB {
    private HashMap<String,String[]>wordList;


    private ArrayList<String> categories;


    public WordDB() throws FileNotFoundException {
        try{
            wordList= new HashMap<>();
            categories = new ArrayList<>();


            String fileparth=getClass().getClassLoader().getResource(commonConstants.DATA_PATH).getPath();
            if(fileparth.contains("%20")) fileparth =fileparth.replaceAll("%20","");
            BufferedReader reader =new BufferedReader(new FileReader(fileparth));

            String line;
            while((line=reader.readLine()) !=null){
                String[] parts = (line.split(","));

                String category = parts[0];
                categories.add(category);

                String[] values = Arrays.copyOfRange(parts,1,parts.length);
                wordList.put(category,values);
            }
        } catch (IOException e) {
            System.out.println("Eror:" + e);
            throw new RuntimeException(e);
        }
    }

    public String[] loadChallenge(){
        Random rand = new Random();


        String category = categories.get(rand.nextInt(categories.size()));

        String[] categoryValues = wordList.get(category);
        String word = categoryValues[rand.nextInt(categoryValues.length)];
        return new String[]{category.toUpperCase()}
    }
}
