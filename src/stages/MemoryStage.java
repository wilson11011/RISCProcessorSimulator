package stages;

import java.util.concurrent.Callable;

import buffers.ExecuteMemoryBuffer;
import buffers.MemoryWriteBackBuffer;
import components.Cpu;
import components.Memory;

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
    MemoryWriteBackBuffer outBuffer = MemoryWriteBackBuffer.getInstance();

    Memory memory = Memory.getInstance();
    if (executeMemoryBuffer.readMemRead())
    {
      //read from the memory location calculated by the ALU
      outBuffer.writeDataReadFromMemory(memory.getMemory(executeMemoryBuffer.readAluResult()));
    }

    if (executeMemoryBuffer.readMemWrite())
    {
      //write value from register to memory
      memory.setMemory(executeMemoryBuffer.readAluResult(),
                       executeMemoryBuffer.readRegReadValue2());
    }

    //set PCSrc: Branch & ALUZero
    Cpu.setPCSrc(executeMemoryBuffer.readBranch() && executeMemoryBuffer.readAluZeroResult());

    //pass write back stage signals on
    outBuffer.writeMemToReg(executeMemoryBuffer.readMemToReg());
    outBuffer.writeRegWrite(executeMemoryBuffer.readRegWrite());

    running = false;
    return outBuffer;
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
