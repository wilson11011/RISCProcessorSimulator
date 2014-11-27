package components;

/**
 * Created by Matt on 11/6/2014.
 */
public class ALU
{
    public static int perormALU( int opcode int funcCode, int Reg1, int Reg2)
    {
        if(opcode == "0") {
            switch (funcCode) {
                case 1: //add
                    return Reg1 + Reg2;
                case 0: //sub
                    return Reg1 - Reg2;
            }
        }
        else{
            switch(funcCode) {

                case 0: //sll
                    return Reg1 << Reg2;

                case 1: //sra
                    return Reg1 >> Reg2;

                case 2: //srl
                    return Reg1 >>> Reg2;

                case 3: //and
                  return Reg1 & Reg2;
                
                case 4: //xor
                    return Reg1 ^ Reg2;

                case 5: //or
                  return Reg1 | Reg2;

                case 6: //slt
                    if (Reg1 < Reg2)
                        return 1;
        }
        }
    }
}
