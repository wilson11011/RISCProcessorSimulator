package stages;

import java.util.concurrent.Callable;

import buffers.FetchDecodeBuffer;
import components.Memory;
import components.RegisterFile;

public class FetchStage implements Callable<FetchDecodeBuffer>
{
  private static boolean running = false;
  private static RegisterFile reg;
  private static Memory mem;

  public FetchStage()
  {
  }

  @Override

  /*
    get register file instance
    write instruction to buffer
    write incremented PC to buffer
    return buffer
    */
  public FetchDecodeBuffer call() throws Exception
  {
    running = true;

    reg = RegisterFile.getInstance();
    mem = Memory.getInstance();
    int pc = reg.getPc();
    int instruction = mem.getInstruction(pc);

    FetchDecodeBuffer buffer = new FetchDecodeBuffer();

    buffer.writeInstruction(instruction);

    buffer.writeIncrementedPc(++pc);

    running = false;
    return buffer;
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
