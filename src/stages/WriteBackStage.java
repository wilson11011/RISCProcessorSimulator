package stages;

import java.util.concurrent.Callable;

import buffers.MemoryWriteBackBuffer;
import components.RegisterFile;

public class WriteBackStage
{
  private static boolean running = false;

  public static void execute()
  {
    running = true;
    MemoryWriteBackBuffer memoryWriteBackBuffer = MemoryWriteBackBuffer.getInstance();
    System.out.println("WB");
    if (memoryWriteBackBuffer.readRegWrite())
    {
      RegisterFile registerFile = RegisterFile.getInstance();
      if (memoryWriteBackBuffer.readMemToReg())
      {
        //write from memory to register
        registerFile.setRegister(memoryWriteBackBuffer.readDestinationRegisterAddress(),
                                 memoryWriteBackBuffer.readDataReadFromMemory());
      }
      else
      {
        //write from ALU to register
        registerFile.setRegister(memoryWriteBackBuffer.readDestinationRegisterAddress(),
                                 memoryWriteBackBuffer.readALUResult());
      }
    }
    running = false;
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
