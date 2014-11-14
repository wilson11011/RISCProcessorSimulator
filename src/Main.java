import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main
{

  public static void main(String[] args)
  {
    ArrayList<Integer> instructions = loadInstructions();


    for(Integer i : instructions) {
      System.out.println(i);
    }
  }

  private static ArrayList<Integer> loadInstructions(){

    ArrayList<Integer> instructions = new ArrayList<Integer>();
    try (BufferedReader br = new BufferedReader(new FileReader("src\\Instructions.code")))
    {

      String line;

      while ((line = br.readLine()) != null) {
        instructions.add(Integer.parseInt(line, 2));
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
    return instructions;
  }
}

//Matt Cooksey was here
