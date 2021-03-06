package hu.bme.mit.trainbenchmark.benchmark.treat.testcases.user;

import hu.bme.mit.trainbenchmark.benchmark.emf.EMFModification;
import hu.bme.mit.trainbenchmark.benchmark.testcase.TestCaseWithModify;
import hu.bme.mit.trainbenchmark.benchmark.treat.testcases.RouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;

public class RouteSensorUser extends RouteSensor implements TestCaseWithModify
{
	@Override
	public void modify() {
		EMFModification.modifyEMFmodelRouteSensor(pack, bmr, Util.calcModify(bc, bc.getModificationConstantRouteSensor(), bmr));
	}
}
