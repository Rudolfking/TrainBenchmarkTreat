package hu.bme.mit.trainbenchmark.benchmark.treat.testcases.user;

import hu.bme.mit.trainbenchmark.benchmark.emf.EMFModification;
import hu.bme.mit.trainbenchmark.benchmark.testcase.TestCaseWithModify;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.benchmark.treat.testcases.PosLength;

public class PosLengthUser extends PosLength implements TestCaseWithModify
{
	@Override
	public void modify()
	{
		EMFModification.modifyEMFmodelPosLength(pack, bmr, Util.calcModify(bc, bc.getModificationConstantPosLength(), bmr));
	}
}
