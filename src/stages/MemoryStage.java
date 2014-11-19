package stages;

import java.util.concurrent.Callable;

import buffers.ExecuteMemoryBuffer;
import buffers.MemoryWriteBackBuffer;

public class MemoryStage implements Callable<MemoryWriteBackBuffer>
{
  private static boolean running = false;

  private final ExecuteMemoryBuffer executeMemoryBuffer;

  public MemoryStage(ExecuteMemoryBuffer executeMemoryBuffer)
  {
    this.executeMemoryBuffer = executeMemoryBuffer;
  }

  @Override
  public MemoryWriteBackBuffer call() throws Exception
  {
    running = true;
    //TODO: Implement MemoryStage




    running = false;
    return null;
  }

  public static boolean isRunning()
  {
    return running;
  }

  public static void setRunning(boolean running)
  {
    MemoryStage.running = running;
  }
}
