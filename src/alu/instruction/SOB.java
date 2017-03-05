package alu.instruction;

import cpu.Registers;
import memory.MCU;
import util.EffectiveAddress;
import util.MachineFaultException;
import util.StringUtil;

public class SOB extends AbstractInstruction {

	@Override
	public void execute(String instruction, Registers registers, MCU mcu) throws MachineFaultException {
		// -----------------------------------
		// 016: SOB -> Subtract One and Branch
		// -----------------------------------
		int r = StringUtil.binaryToDecimal(instruction.substring(6, 8));
		registers.setRnByNum(r, (mcu.fetchFromMemory(registers.getRnByNum(r)) - 1));
		if (mcu.fetchFromMemory(registers.getRnByNum(r)) > 0) {
			registers.setPC(util.EffectiveAddress.EA(instruction, mcu, registers));
		} else {
			registers.increasePCByOne();
		}
	}

	@Override
	public String getExecuteMessage() {
		// TODO Auto-generated method stub
		return null;
	}

}