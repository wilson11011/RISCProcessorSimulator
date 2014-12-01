package stages;

import java.util.concurrent.Callable;

import buffers.ExecuteMemoryBuffer;
import buffers.MemoryWriteBackBuffer;
import components.Cpu;
import components.Memory;

public class MemoryStage
{
  private static boolean running = false;

  private static ExecuteMemoryBuffer executeMemoryBuffer;

  public static void execute()
  {
    running = true;

    executeMemoryBuffer = ExecuteMemoryBuffer.getInstance();
    MemoryWriteBackBuffer outBuffer = MemoryWriteBackBuffer.getInstance();

    System.out.println("MEM");

    Memory memory = Memory.getInstance();

    if (executeMemoryBuffer.readMemRead())
    {
      //read from the memory location calculated by the ALU
      outBuffer.writeDataReadFromMemory(memory.getMemory(executeMemoryBuffer.readAluResult()));
    }
    else
    {
      //read from "random" memory location
      outBuffer.writeDataReadFromMemory(0);
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
    outBuffer.writeAluResult(executeMemoryBuffer.readAluResult());
    outBuffer.writeDestinationRegisterAddress(executeMemoryBuffer.readDestinationRegisterAddress());

    running = false;
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
