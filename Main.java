import java.io.File;

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
            System.out.println("File doesn't exists!");
        }
    }
}