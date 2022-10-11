/* Imports related to detecting if the file exists */
import java.io.File;
import java.io.IOException;
/* Imports related to reading values from the file */
import java.util.Scanner;
import java.io.FileNotFoundException;
/* Importing the HashMap class */
import java.util.HashMap;
/* Imports related to sorting a HashMap */
import java.util.List;
import java.util.ArrayList;
import java.util.Map.Entry;

public class Main {
    public static void main(String[] args)
    {
        /* Checks does the given file exists, if it doesn't it makes it, 
         * if it does exist create a new SortedHashMap instance and pass
         * it's HashMap object to the method for getting all the words and
         * their number of repetitions
         * After all this the sort method from the SortedHashMap class will
         * sort the HashMap, and from the same class we will call the output
         * method to output the sorted HashMap
         */
        File file = new File("./example.txt");

        if(file.exists())
        {   
            SortedHashMap words = new SortedHashMap();
            countTheWords(file, words.map);

            words.sort();
            words.output();
        }
        else
        {
            printErrorMsg(file);

            createFile(file);
        }
    }

    private static void countTheWords(File file, HashMap<String, Integer> map)
    {
        /* Using the scanner object we will try and read the .txt file
         * line by line, when a line is read it will be split and then
         * all the words will be lower cased and checked are they a part
         * of the HashMap already or not, if they are not they will be added
         * with their count being 1, if they are a part their count will
         * get incremented
         * At the end we will close the Scanner class instance
         */
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
        /* Method for outputting an error message
         * Thought it would be easier to deal with
         * if it was it's own method
         */
        System.out.println("File doesn't exists!");
        System.out.print("If you wish to use this program you need to input the text inside the created ");
        System.out.println("\"" + file.getName() + "\" file!");
    }

    private static void createFile(File newFile)
    {
        /* Method used for creating file if it does
         * not exist
         */
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

/* Class used for dealing with a sorted hash map */
class SortedHashMap {
    /* Declaring class properties */
    HashMap<String, Integer> map;
    List<Entry<String, Integer>> list;

    /* Constructor gives an instance of the HashMap class
     * to the map property
     */
    SortedHashMap()
    {
        this.map = new HashMap<>(); 
    }

    /* Giving the list property an instance of the ArrayList
     * class which will take as the constructor the values of
     * the array list
     * After which said array list will be sorted by comparing
     * the values
     */
    public void sort()
    {
        this.list = new ArrayList<>(this.map.entrySet());

        this.list.sort(Entry.comparingByValue());
    }

    /* Method used for outputting the sorted hash map
     * which is just a list with the hash map values sorted
     */
    public void output()
    {
        System.out.println("Key => Value");
        for(int i=this.list.size()-1; i>=0; i--)
        {
            System.out.println(this.list.get(i).getKey() + " => " + this.list.get(i).getValue());
        }
    }
}