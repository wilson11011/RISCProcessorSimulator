package buffers;

public class FetchDecodeBuffer
{
  private int incrementedPc;

  public int readInstruction()
  {
    return instruction;
  }

  public void writeInstruction(int instruction)
  {
    this.instruction = instruction;
  }

  public int writeIncrementedPc()
  {
    return incrementedPc;
  }

  public void readIncrementedPc(int incrementedPc)
  {
    this.incrementedPc = incrementedPc;
  }

  private int instruction;
}
