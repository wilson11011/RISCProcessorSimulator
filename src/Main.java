import java.util.concurrent.ExecutionException;

import components.Cpu;

public class Main
{

  public static void main(String[] args)
  {
    Cpu.init();

    try
    {
      Cpu.execute();
    } catch (ExecutionException | InterruptedException e)
    {
      e.printStackTrace();
    }
  }
}

//Matt Cooksey was here
//Wilson doesn't care
