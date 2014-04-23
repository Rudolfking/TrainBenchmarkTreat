package hu.bme.mit.trainbenchmark.benchmark.treat;

import org.apache.commons.cli.ParseException;

import hu.bme.mit.trainbenchmark.benchmark.scenarios.GenericBenchmarkLogic;
import hu.bme.mit.trainbenchmark.benchmark.testcase.TestCase;
import hu.bme.mit.trainbenchmark.benchmark.treat.testcases.user.PosLengthUser;
import hu.bme.mit.trainbenchmark.benchmark.treat.testcases.user.RouteSensorUser;
import hu.bme.mit.trainbenchmark.benchmark.treat.testcases.user.SignalNeighborUser;
import hu.bme.mit.trainbenchmark.benchmark.treat.testcases.user.SwitchSensorUser;
import hu.bme.mit.trainbenchmark.benchmark.treat.testcases.xform.PosLengthXForm;
import hu.bme.mit.trainbenchmark.benchmark.treat.testcases.xform.RouteSensorXForm;
import hu.bme.mit.trainbenchmark.benchmark.treat.testcases.xform.SignalNeighborXForm;
import hu.bme.mit.trainbenchmark.benchmark.treat.testcases.xform.SwitchSensorXForm;

public class TreatBenchmarkLogic extends GenericBenchmarkLogic{

	TreatBenchmarkConfig tbc;
	
	public TreatBenchmarkLogic(String[] args) throws ParseException
	{
		super(args);
		bc = tbc = new TreatBenchmarkConfig(args);
	}

	@Override
	public TestCase getTestCase(String testCase, String scenario)
	{
		switch (scenario) {
		case "User":
			switch (testCase) {
			case "PosLength":
				return new PosLengthUser();
			case "RouteSensor":
				return new RouteSensorUser();
			case "SignalNeighbor":
				return new SignalNeighborUser();
			case "SwitchSensor":
				return new SwitchSensorUser();
			default:
				throw new UnsupportedOperationException("Unknown test case: " + testCase);
			}
		case "XForm":
			switch (testCase) {
			case "PosLength":
				return new PosLengthXForm();
			case "RouteSensor":
				return new RouteSensorXForm();
			case "SignalNeighbor":
				return new SignalNeighborXForm();
			case "SwitchSensor":
				return new SwitchSensorXForm();
			default:
				throw new UnsupportedOperationException("Unknown test case: " + testCase);
			}
		case "Batch":
			switch (testCase) {
			case "PosLength":
				return new PosLengthXForm();
			case "RouteSensor":
				return new RouteSensorXForm();
			case "SignalNeighbor":
				return new SignalNeighborXForm();
			case "SwitchSensor":
				return new SwitchSensorXForm();
			default:
				throw new UnsupportedOperationException("Unknown test case: " + testCase);
			}
		default:
			throw new UnsupportedOperationException("Unknown scenario: " + scenario);
		}
	}
}
