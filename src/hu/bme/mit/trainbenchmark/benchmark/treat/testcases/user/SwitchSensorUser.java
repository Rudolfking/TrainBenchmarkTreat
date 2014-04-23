package hu.bme.mit.trainbenchmark.benchmark.treat.testcases.user;

import hu.bme.mit.trainbenchmark.benchmark.emf.EMFModification;
import hu.bme.mit.trainbenchmark.benchmark.testcase.TestCaseWithModify;
import hu.bme.mit.trainbenchmark.benchmark.treat.testcases.SwitchSensor;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;

public class SwitchSensorUser extends SwitchSensor implements TestCaseWithModify {
	
	@Override
	public void modify() {
		EMFModification.modifyEMFmodelSwitchSensor(pack, bmr, Util.calcModify(bc, bc.getModificationConstantSwitchSensor(), bmr));
	}
}
