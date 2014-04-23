package hu.bme.mit.trainbenchmark.benchmark.treat.testcases;

import hu.bme.mit.inf.lookaheadmatcher.impl.LookaheadMatching;
import hu.bme.mit.inf.treatengine.LookaheadMatcherTreat;
import hu.bme.mit.inf.treatengine.TreatRegistrarImpl;
import hu.bme.mit.trainbenchmark.benchmark.incquery.util.SignalNeighborQuerySpecification;
import hu.bme.mit.trainbenchmark.emf.FileBroker;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.incquery.runtime.api.AdvancedIncQueryEngine;
import org.eclipse.incquery.runtime.exception.IncQueryException;

import com.google.common.collect.Multiset;

import Concept.IndividualContainer;
import Concept.Route;

public class SignalNeighbor extends TreatTestCase
{
	protected List<Route> invalids;
	//protected IQDeltaMonitor<Segment> iqDeltaMonitor;
	protected LookaheadMatcherTreat lmt;

	@Override
	public String getName() {
		return "SignalNeighbor";
	}

	@Override
	public void load() {
		bmr.startStopper();
		
		URI resourceURI = FileBroker.getEMFUri(bc.getInstanceModelPath() + "/" + fileName);
		resourceSet = new ResourceSetImpl();
		
		try {
			//IncQueryCommon.setEIQOptions(getIQBC());
			engine = AdvancedIncQueryEngine.createUnmanagedEngine(resourceSet);
			
			if (TreatRegistrarImpl.LookaheadToEngineConnector.GetLookaheadMatcherTreat(engine) == null)
			{
				LookaheadMatcherTreat lmt_local = new LookaheadMatcherTreat(engine);
				TreatRegistrarImpl.LookaheadToEngineConnector.Connect(engine, lmt_local);
				lmt = TreatRegistrarImpl.LookaheadToEngineConnector.GetLookaheadMatcherTreat(engine);
			}
			else
				lmt = TreatRegistrarImpl.LookaheadToEngineConnector.GetLookaheadMatcherTreat(engine);
			lmt.PowerTreatUp(SignalNeighborQuerySpecification.instance());
			
		} catch (IncQueryException e) {
			e.printStackTrace();
		}
		resource = resourceSet.getResource(resourceURI, true);
		if (resource.getContents().size() > 0 && resource.getContents().get(0) instanceof IndividualContainer) {
			pack = (IndividualContainer) resource.getContents().get(0);
		}

		// NOW you can match
		try {
			lmt.matchThePattern(SignalNeighborQuerySpecification.instance());
		} catch (IncQueryException e) {
			e.printStackTrace();
		}
		
		bmr.setReadTime();
	}

	@Override
	public void check() {
		bmr.startStopper();
		Multiset<LookaheadMatching> res;
		try {
			// this will match the pattern surely ... once upon a time
			res = lmt.matchThePattern(SignalNeighborQuerySpecification.instance());
			invalids = new ArrayList<Route>();
			for(LookaheadMatching itRes : res.elementSet())//.toArrayList(false))
				invalids.add((Route)itRes.get(0));
		} catch (IncQueryException e) {
			e.printStackTrace();
		}
		bmr.addInvalid(invalids.size());
		bmr.addCheckTime();
	}

}
