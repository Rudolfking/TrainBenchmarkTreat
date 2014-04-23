package hu.bme.mit.trainbenchmark.benchmark.treat.testcases.xform;

import hu.bme.mit.trainbenchmark.benchmark.emf.EMFModification;
import hu.bme.mit.trainbenchmark.benchmark.testcase.TestCaseWithModify;
import hu.bme.mit.trainbenchmark.benchmark.treat.testcases.PosLength;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;

public class PosLengthXForm extends PosLength implements TestCaseWithModify {

	@Override
	public void modify() {
		EMFModification.modifyEMFmodelPosLengthRepair(pack, bmr, Util.calcModify(bc, bc.getModificationConstantPosLength(), bmr), invalids);
	}
}
