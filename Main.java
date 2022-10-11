import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args)
    {
        File file = new File("./example.txt");

        if(file.exists())
        {   
            HashMap<String, Integer> words = new HashMap<>();
            countTheWords(file, words);
            outputHashMap(words);
        }
        else
        {
            printErrorMsg(file);

            createFile(file);
        }
    }

    private static void outputHashMap(HashMap<String, Integer> map)
    {
        System.out.println("Key => Value");
        map.entrySet().forEach(entry -> {
            System.out.println( entry.getKey() + " => " + entry.getValue() );
        });
    }

    private static void countTheWords(File file, HashMap<String, Integer> map)
    {
        try {
            Scanner scanner = new Scanner(file);

            while(scanner.hasNextLine())
            {
                String[] line = scanner.nextLine().split(" ");
                for(int i=0; i<line.length; i++)
                {
                    String gottenLine = line[i].toLowerCase();
                    if(map.containsKey(gottenLine))
                    {
                        int value = map.get(gottenLine) + 1;
                        map.replace(gottenLine, value);
                    }
                    else
                    {
                        map.put(gottenLine, 1);
                    }
                }
            }

            scanner.close();
        }
        catch(FileNotFoundException error)
        {
            error.printStackTrace();
        }
    }

    private static void printErrorMsg(File file)
    {
        System.out.println("File doesn't exists!");
        System.out.print("If you wish to use this program you need to input the text inside the created ");
        System.out.println("\"" + file.getName() + "\" file!");
    }

    private static void createFile(File newFile)
    {
        try {
            newFile.createNewFile();
        }
        catch(IOException error)
        {
            System.out.println("Error ocurred during file creation!");
            error.printStackTrace();
        }
    }
}