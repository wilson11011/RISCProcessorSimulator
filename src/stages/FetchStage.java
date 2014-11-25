package stages;

import java.util.concurrent.Callable;

import buffers.FetchDecodeBuffer;
import components.Cpu;
import components.Memory;
import components.RegisterFile;

public class FetchStage implements Callable<FetchDecodeBuffer>
{
  private static boolean running = false;
  private static int currentPc = 0;

  private RegisterFile reg;
  private Memory mem;

  public FetchStage()
  {
  }

  /*
    get register file instance
    write instruction to buffer
    write incremented PC to buffer
    return buffer
    */
  @Override
  public FetchDecodeBuffer call() throws Exception
  {
    running = true;

    reg = RegisterFile.getInstance();
    mem = Memory.getInstance();

    if(Cpu.isPCSrc())
    {
      //set to branch address and flush decode and execute stages
    }

    int instruction = mem.getInstruction(currentPc);

    System.out.println("FET: " + currentPc + "\t\t\t" + String.format("%16s", Integer.toBinaryString
            (instruction)).replace(' ', '0'));

    FetchDecodeBuffer buffer = FetchDecodeBuffer.getInstance();

    buffer.writeInstruction(instruction);

    ++currentPc;
    reg.writePc(currentPc);
    buffer.writeIncrementedPc(currentPc);

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
