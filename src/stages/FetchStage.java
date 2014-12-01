package stages;

import java.util.concurrent.Callable;

import buffers.ExecuteMemoryBuffer;
import buffers.FetchDecodeBuffer;
import components.Cpu;
import components.Memory;
import components.RegisterFile;

public class FetchStage
{
  private static boolean running = false;
  private static int currentPc = 0;

  /*
    get register file instance
    write instruction to buffer
    write incremented PC to buffer
    return buffer
    */

  public static void execute()
  {
    running = true;

    RegisterFile reg = RegisterFile.getInstance();
    Memory mem = Memory.getInstance();

    if(Cpu.isPCSrc())
    {
      ExecuteMemoryBuffer memoryBuffer = ExecuteMemoryBuffer.getInstance();
      currentPc = memoryBuffer.readAluResult();
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
