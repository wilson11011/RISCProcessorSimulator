package buffers;

public class ExecuteMemoryBuffer
{
  private int incrementedPcWithOffset;
  
  private int aluResult;
  private int aluZeroResult;

  private int regReadValue2;
  
  private int destinationRegisterAddress;
  
  //CONTROL SIGNALS
  //MEM
  private boolean branch;
  private boolean memWrite;
  private boolean memRead;

  //WB
  private boolean memToReg;
  private boolean regWrite;

  public int readIncrementedPcWithOffwrite()
  {
    return incrementedPcWithOffset;
  }

  public void writeIncrementedPcWithOffwrite(int incrementedPcWithOffset)
  {
    this.incrementedPcWithOffset = incrementedPcWithOffset;
  }

  public int readAluResult()
  {
    return aluResult;
  }

  public void writeAluResult(int aluResult)
  {
    this.aluResult = aluResult;
  }

  public int readAluZeroResult()
  {
    return aluZeroResult;
  }

  public void writeAluZeroResult(int aluZeroResult)
  {
    this.aluZeroResult = aluZeroResult;
  }

  public int readRegReadValue2()
  {
    return regReadValue2;
  }

  public void writeRegReadValue2(int regReadValue2)
  {
    this.regReadValue2 = regReadValue2;
  }

  public int readDestinationRegisterAddress()
  {
    return destinationRegisterAddress;
  }

  public void writeDestinationRegisterAddress(int destinationRegisterAddress)
  {
    this.destinationRegisterAddress = destinationRegisterAddress;
  }

  public boolean readBranch()
  {
    return branch;
  }

  public void writeBranch(boolean branch)
  {
    this.branch = branch;
  }

  public boolean readMemWrite()
  {
    return memWrite;
  }

  public void writeMemWrite(boolean memWrite)
  {
    this.memWrite = memWrite;
  }

  public boolean readMemRead()
  {
    return memRead;
  }

  public void writeMemRead(boolean memRead)
  {
    this.memRead = memRead;
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
}
