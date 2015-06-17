package tests;

import translation.Int;
import translation.MemAddr;
import translation.OpCode;
import translation.Register;
import translation.Spril;
import translation.Operator;
import translation.Target;

import org.junit.Test;

import static org.junit.Assert.*;

public class SprilTest {

	@Test
	public void doTest() {
		Spril cnst = new Spril(OpCode.CONST, new Int(5), Register.A);
		assertEquals("Const 5 RegA", cnst.toString());

		Spril load = new Spril(OpCode.LOAD, MemAddr.direct(1234),
				Register.C);
		assertEquals("Load (Addr 1234) RegC", load.toString());

		Spril compA = new Spril(OpCode.COMPUTE, Operator.ADD, Register.A,
				Register.ZERO, Register.PC);
		assertEquals("Compute Add RegA Zero PC", compA.toString());

		Spril compB = new Spril(OpCode.COMPUTE, Operator.LSHIFT,
				Register.SP, Register.SPID, Register.E);
		assertEquals("Compute LShift SP SPID RegE", compB.toString());

		Spril branch = new Spril(OpCode.BRANCH, Target.relative(-7),
				Register.B);
		assertEquals("Branch (Rel -7) RegB", branch.toString());

		Spril write = new Spril(OpCode.WRITE, Register.ZERO,
				MemAddr.deref(Register.SPID));
		assertEquals("Write Zero (Deref SPID)", write.toString());

		try {
			Spril err = new Spril(OpCode.LOAD, new Int(42)); //Wrong operand type
			fail(err.toString());
		} catch (IllegalArgumentException e) {
			// expected
		}
		try {
			Spril err = new Spril(OpCode.RECEIVE, new Int(42)); // Wrong operand type
			fail(err.toString());
		} catch (IllegalArgumentException e) {
			// expected
		}
		try {
			Spril err = new Spril(OpCode.STORE, Register.A,
					Target.absolute(9876543)); // Wrong operand type
			fail(err.toString());
		} catch (IllegalArgumentException e) {
			// expected
		}
		try {
			Spril err = new Spril(OpCode.PUSH); // Too few arguments
			fail(err.toString());
		} catch (IllegalArgumentException e) {
			// expected
		}
		try {
			Spril err = new Spril(OpCode.POP, Register.A, Register.B); // Too many arguments
			fail(err.toString());
		} catch (IllegalArgumentException e) {
			// expected
		}
	}

}
