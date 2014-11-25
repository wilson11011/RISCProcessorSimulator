package buffers;


/**
 * The memory stage writes data to the MemoryWriteBackBuffer to be read by the write back stage.
 * This class has been made a singleton to allow for forwarding.
 */
public class MemoryWriteBackBuffer
{
  private int dataReadFromMemory;
  private int ALUResult;
  private int destinationRegisterAddress;

  //CONTROL SIGNALS
  //WB
  private boolean memToReg;
  private boolean regWrite;

  private static MemoryWriteBackBuffer instance;

  public static MemoryWriteBackBuffer getInstance()
  {
    if(instance == null)
    {
      instance = new MemoryWriteBackBuffer();
    }
    return instance;
  }

  public int readDataReadFromMemory()
  {
    return dataReadFromMemory;
  }

  public void writeDataReadFromMemory(int dataReadFromMemory)
  {
    this.dataReadFromMemory = dataReadFromMemory;
  }

  public int readALUResult()
  {
    return ALUResult;
  }

  public void writeALUResult(int ALUResult)
  {
    this.ALUResult = ALUResult;
  }

  public boolean readMemToReg()
  {
    return memToReg;
  }

  public void writeMemToReg(boolean memToReg)
  {
    this.memToReg = memToReg;
  }

  public boolean readRegWrite()
  {
    return regWrite;
  }

  public void writeRegWrite(boolean regWrite)
  {
    this.regWrite = regWrite;
  }

  public int readDestinationRegisterAddress()
  {
    return destinationRegisterAddress;
  }

  public void writeDestinationRegisterAddress(int destinationRegisterAddress)
  {
    this.destinationRegisterAddress = destinationRegisterAddress;
  }
}
