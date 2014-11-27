package components;

/**
 * Created by Matt on 11/6/2014.
 * Edited by Wilson 11/26/2014
 */
public class ALU
{
    public static int perormALU( int opcode, int funcCode, int Reg1, int Reg2)
    {
        if(opcode == "0000") {
            switch (funcCode) {
                case 001: //add
                    return Reg1 + Reg2;
                case 000: //sub
                    return Reg1 - Reg2;
            }
        }
        else{
            switch(funcCode) {

                case 000: //sll
                    return Reg1 << Reg2;

                case 001: //sra
                    return Reg1 >> Reg2;

                case 010: //srl
                    return Reg1 >>> Reg2;

                case 011: //and
                  return Reg1 & Reg2;

                case 110: //or
                  return Reg1 | Reg2;

                case 100: //xor
                  return Reg1 ^ Reg2;

                case 101: //slt
                    if (Reg1 < Reg2)
                        return 1;
        }
        }
    }
}
