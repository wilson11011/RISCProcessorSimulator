package stages;

import java.util.concurrent.Callable;

import buffers.DecodeExecuteBuffer;
import buffers.ExecuteMemoryBuffer;
import components.ALU;

public class ExecutionStage implements Callable<ExecuteMemoryBuffer>
{
  private static boolean running = false;

  private final DecodeExecuteBuffer decodeExecuteBuffer;

  public ExecutionStage(DecodeExecuteBuffer decodeExecuteBuffer)
  {
    this.decodeExecuteBuffer = decodeExecuteBuffer;
  }

  @Override
  public ExecuteMemoryBuffer call() throws Exception
  {
    running = true;
    //TODO: Implement ExecutionStage
    ExecuteMemoryBuffer outBuffer = ExecuteMemoryBuffer.getInstance();

    //PC: Need to do offset
    int incrementedPc = decodeExecuteBuffer.readIncrementedPc();
    
    int signExtended = decodeExecuteBuffer.readSignExtendedBytes();
    incrementedPc = incrementedPc + (signExtended >> 2);
    
    outBuffer.writeIncrementedPcWithOffwrite(incrementedPc);
    //ALU Control


    //Call ALU: need to figure out how to ALUop1 and ALUop2
    if(decodeExecuteBuffer.readAluSrc()){
      outBuffer.writeAluResult(ALU.performALU( ,decodeExecuteBuffer.readRegReadValue1(), decodeExecuteBuffer.readSignExtendedBytes));
    }
    else{
      outBuffer.writeAluResult(ALU.performALU(, decodeExecuteBuffer.readRegReadValue1(), decodeExecuteBuffer.readRegReadValue1());
    }

    //Rt or Rd
    if(decodeExecuteBuffer.readRegDst()){
      outBuffer.writeDestinationRegisterAddress(decodeExecuteBuffer.readRd());
    }
    else{
      outBuffer.writeDestinationRegisterAddress(decodeExecuteBuffer.readRt());
      
    }
    running = false;
    return outBuffer;
  }

  public static boolean isRunning()
  {
    return running;
  }

  public static void setRunning(boolean running)
  {
    ExecutionStage.running = running;
  }
}
