/**
 * Created by Matt on 11/6/2014.
 */
public class Memory
{
  private static Memory instance    = null;
  private final  int    MEMORY_SIZE = 32;
  private int[] memory;

  private Memory()
  {
    memory = new int[MEMORY_SIZE];
    for (int i = 0; i < MEMORY_SIZE; i++)
    {
      memory[i] = 0x00;
    }
  }

  //Singleton to maintain only one RegisterFile
  public Memory getInstance()
  {
    if (instance == null)
    {
      instance = new Memory();
    }
    return instance;
  }

  public void setMemory(int index, int value)
  {
    if (index >= MEMORY_SIZE || index < 0)
    {
      throw new ArrayIndexOutOfBoundsException("Attempting to write to memory that does not exist" +
                                               ".");
    }
    memory[index] = value;
  }

  public int getMemory(int index)
  {
    if (index >= MEMORY_SIZE || index < 0)
    {
      throw new ArrayIndexOutOfBoundsException("Attempting to read memory that does not exist.");
    }
    return memory[index];
  }
}
