package buffers;

public class FetchDecodeBuffer
{
  private int instruction;
  private int incrementedPc;

  public int readInstruction()
  {
    return instruction;
  }

  public void writeInstruction(int instruction)
  {
    this.instruction = instruction;
  }

  public int readIncrementedPc()
  {
    return incrementedPc;
  }

  public void writeIncrementedPc(int incrementedPc)
  {
    this.incrementedPc = incrementedPc;
  }


}
