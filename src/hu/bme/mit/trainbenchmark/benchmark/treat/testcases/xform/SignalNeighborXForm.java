package hu.bme.mit.trainbenchmark.benchmark.treat.testcases.xform;

import hu.bme.mit.trainbenchmark.benchmark.emf.EMFModification;
import hu.bme.mit.trainbenchmark.benchmark.testcase.TestCaseWithModify;
import hu.bme.mit.trainbenchmark.benchmark.treat.testcases.SignalNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;

public class SignalNeighborXForm extends SignalNeighbor implements TestCaseWithModify {

	@Override
	public void modify() {
		EMFModification.modifyEMFmodelSignalNeighborRepair(pack, bmr, Util.calcModify(bc, bc.getModificationConstantSignalNeighbor(), bmr), invalids);
	}
}
