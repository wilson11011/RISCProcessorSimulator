package stages;

import java.util.concurrent.Callable;

import buffers.MemoryWriteBackBuffer;

public class WriteBackStage implements Callable<Void>
{
  private static boolean running = false;

  private final MemoryWriteBackBuffer memoryWriteBackBuffer;

  public WriteBackStage(MemoryWriteBackBuffer memoryWriteBackBuffer)
  {
    this.memoryWriteBackBuffer = memoryWriteBackBuffer;
  }

  @Override
  public Void call() throws Exception
  {
    running = true;
    //TODO: Implement WriteBackStage




    running = false;
    return null;
  }

  public static boolean isRunning()
  {
    return running;
  }

  public static void setRunning(boolean running)
  {
    WriteBackStage.running = running;
  }
}
