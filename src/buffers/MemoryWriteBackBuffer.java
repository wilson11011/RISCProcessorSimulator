package buffers;

public class MemoryWriteBackBuffer
{
  private int dataReadFromMemory;
  private int ALUResult;
  private int destinationRegisterAddress;

  //CONTROL SIGNALS
  //WB
  private boolean memToReg;
  private boolean regWrite;

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

  public boolean isMemToReg()
  {
    return memToReg;
  }

  public void setMemToReg(boolean memToReg)
  {
    this.memToReg = memToReg;
  }

  public boolean isRegWrite()
  {
    return regWrite;
  }

  public void setRegWrite(boolean regWrite)
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
