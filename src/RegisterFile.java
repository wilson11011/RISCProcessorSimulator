/**
 * Created by Matt on 11/6/2014.
 */
public class RegisterFile
{
  private static RegisterFile instance           = null;
  private final  int          REGISTER_FILE_SIZE = 8;
  private int[] registers;

  private int pc;

  private RegisterFile()
  {
    registers = new int[REGISTER_FILE_SIZE];
    for (int i = 0; i < REGISTER_FILE_SIZE; i++)
    {
      registers[i] = 0x00;
    }
  }

  //Singleton to maintain only one RegisterFile
  public RegisterFile getInstance()
  {
    if (instance == null)
    {
      instance = new RegisterFile();
    }
    return instance;
  }

  public void setRegister(int index, int value)
  {
    if (index == 0)
    {
      throw new IllegalArgumentException("You cannot change the value of the zero register.");
    }
    if (index >= REGISTER_FILE_SIZE || index < 0)
    {
      throw new ArrayIndexOutOfBoundsException("Attempting to write to register that does not " +
                                               "exist" +
                                               ".");
    }
    registers[index] = value;
  }

  public int getRegister(int index)
  {
    if (index >= REGISTER_FILE_SIZE || index < 0)
    {
      throw new ArrayIndexOutOfBoundsException("Attempting to read from register that does not " +
                                               "exist" +
                                               ".");
    }
    return registers[index];
  }

  public int incrementPc()
  {
    return ++pc;
  }

  public int getPc()
  {
    return pc;
  }

  public void setPc(int pc)
  {
    this.pc = pc;
  }
}
