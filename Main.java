import java.io.File;
import java.io.IOException;

import java.util.Scanner;
import java.io.FileNotFoundException;

import java.util.HashMap;

import java.util.List;
import java.util.ArrayList;
import java.util.Map.Entry;

public class Main {
    public static void main(String[] args)
    {
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

class SortedHashMap {
    HashMap<String, Integer> map;
    List<Entry<String, Integer>> list;

    SortedHashMap()
    {
        this.map = new HashMap<>(); 
    }

    public void sort()
    {
        this.list = new ArrayList<>(this.map.entrySet());

        this.list.sort(Entry.comparingByValue());
    }

    public void output()
    {
        System.out.println("Key => Value");
        for(int i=this.list.size()-1; i>=0; i--)
        {
            System.out.println(this.list.get(i).getKey() + " => " + this.list.get(i).getValue());
        }
    }
}