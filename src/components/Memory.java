package components;

import java.util.ArrayList;
import java.util.List;


public class Memory
{
  private static Memory instance    = null;
  private final  int    MEMORY_SIZE = 512;
  private int[] memory;

  private List<Integer> instructionMemory = null;

  private Memory()
  {
    instructionMemory = new ArrayList<Integer>();

    memory = new int[MEMORY_SIZE];
    for (int i = 0; i < MEMORY_SIZE; ++i)
    {
      memory[i] = 0x00;
    }
  }

  public Integer getInstruction(int pc)
  {
    return instructionMemory.get(pc);
  }

  //Singleton to maintain only one components.Memory
  public static Memory getInstance()
  {
    if (instance == null)
    {
      instance = new Memory();
    }
    return instance;
  }

  public List<Integer> getInstructionMemory()
  {
    return instructionMemory;
  }

  public void setInstructionMemory(List<Integer> instructionMemory)
  {
    this.instructionMemory = instructionMemory;
  }

  public void setMemory(int index, int value)
  {
    if (index >= MEMORY_SIZE || index < 0)
    {
      throw new ArrayIndexOutOfBoundsException(
              "Attempting to write to memory that does not exist.");
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

  public int getMemorySize()
  {
    return MEMORY_SIZE;
  }

  public int getInstructionMemorySize()
  {
    return instructionMemory.size();
  }
}
