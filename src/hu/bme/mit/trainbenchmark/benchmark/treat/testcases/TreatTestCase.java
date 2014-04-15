package hu.bme.mit.trainbenchmark.benchmark.treat.testcases;

import hu.bme.mit.inf.treatengine.TreatRegistrarImpl;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.testcase.TestCase;
import hu.bme.mit.trainbenchmark.benchmark.treat.TreatBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.util.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.incquery.runtime.api.AdvancedIncQueryEngine;

import Concept.IndividualContainer;

public abstract class TreatTestCase implements TestCase
{
	protected String fileName;
	protected BenchmarkResult bmr;
	protected BenchmarkConfig bc;
	protected IndividualContainer pack;

	protected AdvancedIncQueryEngine engine;
	protected ResourceSet resourceSet;
	protected Resource resource;

	@Override
	public String getTool() {
		return "TREAT";
	}

	protected TreatBenchmarkConfig getTBC() {
		return (TreatBenchmarkConfig) bc;
	}
	
	@Override
	public void init(BenchmarkConfig bc, int size) {
		bmr = new BenchmarkResult(getTool(), getName());
		bmr.setBenchmarkConfig(bc);
		bmr.setSize(size);
		this.fileName = "railway" + bc.getVariant() + size + ".concept";
		bmr.setFileName(this.fileName);
		
		this.bc = bc;
		Util.runGC();
		if (bc.isBenchmarkMode()) {
			Util.freeCache(bc);
		}
	}

	@Override
	public void measureMemory() {
		Util.runGC();
		bmr.addMemoryBytes(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
	}
	@Override
	public void destroy() {
		TreatRegistrarImpl.LookaheadToEngineConnector.Clean();
		if (! engine.isManaged()) {
			engine.dispose();
		}
		engine = null;
		resource = null;
		resourceSet = null;
		Util.runGC();
	}
	@Override
	public BenchmarkResult getBMR() {
		return bmr;
	}
	
}
