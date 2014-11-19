package stages;

import java.util.concurrent.Callable;

import buffers.FetchDecodeBuffer;

public class FetchStage implements Callable<FetchDecodeBuffer>
{
  private static boolean running = false;

  public FetchStage()
  {
  }

  @Override
  public FetchDecodeBuffer call() throws Exception
  {
    running = true;
    //TODO: Implement FetchStage




    running = false;
    return null;
  }

  public static boolean isRunning()
  {
    return running;
  }

  public static void setRunning(boolean running)
  {
    FetchStage.running = running;
  }
}
