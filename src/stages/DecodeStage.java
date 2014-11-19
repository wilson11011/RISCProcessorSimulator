package stages;

import java.util.concurrent.Callable;

import buffers.DecodeExecuteBuffer;
import buffers.FetchDecodeBuffer;

public class DecodeStage implements Callable<DecodeExecuteBuffer>
{
  private static boolean running = false;

  private final FetchDecodeBuffer fetchDecodeBuffer;

  public DecodeStage(FetchDecodeBuffer fetchDecodeBuffer)
  {
    this.fetchDecodeBuffer = fetchDecodeBuffer;
  }

  @Override
  public DecodeExecuteBuffer call() throws Exception
  {
    running = true;
    //TODO: Implement DecodeStage




    running = false;
    return null;
  }

  public static boolean isRunning()
  {
    return running;
  }

  public static void setRunning(boolean running)
  {
    DecodeStage.running = running;
  }
}
