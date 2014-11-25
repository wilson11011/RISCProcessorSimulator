package stages;

import java.util.concurrent.Callable;

import buffers.DecodeExecuteBuffer;
import buffers.ExecuteMemoryBuffer;

public class ExecutionStage implements Callable<ExecuteMemoryBuffer>
{
  private static boolean running = false;

  private final DecodeExecuteBuffer decodeExecuteBuffer;

  public ExecutionStage(DecodeExecuteBuffer decodeExecuteBuffer)
  {
    this.decodeExecuteBuffer = decodeExecuteBuffer;
  }

  @Override
  public ExecuteMemoryBuffer call() throws Exception
  {
    running = true;
    //TODO: Implement ExecutionStage
    ExecuteMemoryBuffer outBuffer = ExecuteMemoryBuffer.getInstance();



    running = false;
    return outBuffer;
  }

  public static boolean isRunning()
  {
    return running;
  }

  public static void setRunning(boolean running)
  {
    ExecutionStage.running = running;
  }
}
