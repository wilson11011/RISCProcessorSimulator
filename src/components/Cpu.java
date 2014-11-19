package components;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
  private static Memory mem;
  private static RegisterFile reg;

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
   * @throws ExecutionException
   * @throws InterruptedException
   */
  public static void execute() throws ExecutionException, InterruptedException
  {

    ExecutorService pool = Executors.newFixedThreadPool(5);
    Future<FetchDecodeBuffer> fetchFuture = null;
    Future<DecodeExecuteBuffer> decodeFuture = null;
    Future<ExecuteMemoryBuffer> executeFuture = null;
    Future<MemoryWriteBackBuffer> memoryFuture = null;
    Future<Void> writeBackFuture = null;

    //execution is complete when the PC is larger than the number of instructions
    while(reg.getPc() != mem.getInstructionMemorySize())
    {

      //Each stage is submitted to the thread pool. The future is then saved and passed to the next
      //thread. This means that the next thread will now wait for the result of previous thread
      // before it executes.
      //The empty while loop is to make sure that thread is not currently executing before
      // executing the next instruction

      while(FetchStage.isRunning()){}
      fetchFuture = pool.submit(new FetchStage());
      while(DecodeStage.isRunning()){}
      decodeFuture = pool.submit(new DecodeStage(fetchFuture.get()));
      while(ExecutionStage.isRunning()){}
      executeFuture = pool.submit(new ExecutionStage(decodeFuture.get()));
      while(MemoryStage.isRunning()){}
      memoryFuture = pool.submit(new MemoryStage(executeFuture.get()));
      while(WriteBackStage.isRunning()){}
      writeBackFuture = pool.submit(new WriteBackStage(memoryFuture.get()));
      writeBackFuture.get();
    }
  }

  private static ArrayList<Integer> loadInstructions(){

    ArrayList<Integer> instructions = new ArrayList<Integer>();
    try (BufferedReader br = new BufferedReader(new FileReader("src\\Instructions.code")))
    {
      String line;
      while ((line = br.readLine()) != null) {
        instructions.add(Integer.parseInt(line, 2));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return instructions;
  }

}
