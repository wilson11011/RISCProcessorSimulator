package components;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import buffers.DecodeExecuteBuffer;
import buffers.ExecuteMemoryBuffer;
import buffers.FetchDecodeBuffer;
import buffers.MemoryWriteBackBuffer;
import stages.DecodeStage;
import stages.ExecutionStage;
import stages.FetchStage;
import stages.MemoryStage;
import stages.WriteBackStage;

public class Cpu
{
  private static Memory       mem;
  private static RegisterFile reg;

  private static int clockCycleCount = 0;

  private static final String INSTRUCTIONS_FILE       = "src\\Instructions.code";
  private static final String DEBUG_INSTRUCTIONS_FILE = "src\\Test.code";

  private static boolean PCSrc = false;

  /**
   * get the CPU ready to begin executing instructions
   */
  public static void init()
  {
    mem = Memory.getInstance();

    //load instructions into the instruction memory
    mem.setInstructionMemory(loadInstructions());

    reg = RegisterFile.getInstance();
  }

  /**
   * Start processing instructions
   *
   * @throws ExecutionException
   * @throws InterruptedException
   */
  @SuppressWarnings("StatementWithEmptyBody")
  public static void execute() throws ExecutionException, InterruptedException
  {
    //execution is complete when the PC is larger than the number of instructions
    while (reg.getPc() != mem.getInstructionMemorySize())
    {
      switch (clockCycleCount++)
      {
        case 0:
          FetchStage.execute();
          break;
        case 1:
          DecodeStage.execute();
          HazardDetectionUnit.decodeHazard();

          FetchStage.execute();
          break;
        case 2:
          ExecutionStage.execute();

          DecodeStage.execute();
          HazardDetectionUnit.decodeHazard();

          FetchStage.execute();;
          break;
        case 3:
          MemoryStage.execute();
          ForwardingUnit.exeMemHazardCheck();
          ForwardingUnit.memWbHazardCheck();

          ExecutionStage.execute();


          DecodeStage.execute();
          HazardDetectionUnit.decodeHazard();

          FetchStage.execute();
          break;
        default:
          WriteBackStage.execute();

          MemoryStage.execute();
          ForwardingUnit.exeMemHazardCheck();
          ForwardingUnit.memWbHazardCheck();

          ExecutionStage.execute();

          DecodeStage.execute();
          HazardDetectionUnit.decodeHazard();

          FetchStage.execute();
          break;
      }
    }

    //finish out instructions currently in pipeline
    WriteBackStage.execute();

    MemoryStage.execute();
    ForwardingUnit.exeMemHazardCheck();
    ForwardingUnit.memWbHazardCheck();

    ExecutionStage.execute();

    DecodeStage.execute();
    HazardDetectionUnit.decodeHazard();


    WriteBackStage.execute();
    MemoryStage.execute();
    ForwardingUnit.exeMemHazardCheck();
    ForwardingUnit.memWbHazardCheck();

    ExecutionStage.execute();

    WriteBackStage.execute();
    MemoryStage.execute();

    WriteBackStage.execute();
  }

  private static ArrayList<Integer> loadInstructions()
  {

    ArrayList<Integer> instructions = new ArrayList<Integer>();
    try (BufferedReader br = new BufferedReader(new FileReader(INSTRUCTIONS_FILE)))
    {
      String line;
      while ((line = br.readLine()) != null)
      {
        instructions.add(Integer.parseInt(line, 2));
      }
    } catch (IOException e)
    {
      e.printStackTrace();
    }
    return instructions;
  }

  public static boolean isPCSrc()
  {
    return PCSrc;
  }

  public static void setPCSrc(boolean PCSrc)
  {
    Cpu.PCSrc = PCSrc;
  }
}
