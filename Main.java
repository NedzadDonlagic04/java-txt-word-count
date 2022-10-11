import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args)
    {
        File file = new File("./example.txt");

        if(file.exists())
        {
            System.out.println("File exists!");
        }
        else
        {
            printErrorMsg(file);

            createFile(file);
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