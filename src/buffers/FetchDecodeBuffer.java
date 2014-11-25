package buffers;


/**
 * The fetch stage writes data to the FetchDecodeBuffer to be read by the decode stage.
 * This class has been made a singleton to allow for forwarding.
 */
public class FetchDecodeBuffer
{
  private int instruction;
  private int incrementedPc;

  private static FetchDecodeBuffer instance;

  public static FetchDecodeBuffer getInstance()
  {
    if(instance == null)
    {
      instance = new FetchDecodeBuffer();
    }
    return instance;
  }

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
