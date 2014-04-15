package hu.bme.mit.trainbenchmark.benchmark.treat.testcases;

import hu.bme.mit.inf.lookaheadmatcher.impl.LookaheadMatching;
import hu.bme.mit.inf.treatengine.LookaheadMatcherTreat;
import hu.bme.mit.inf.treatengine.TreatRegistrarImpl;
import hu.bme.mit.trainbenchmark.benchmark.incquery.util.PosLengthQuerySpecification;
import hu.bme.mit.trainbenchmark.emf.FileBroker;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.incquery.runtime.api.AdvancedIncQueryEngine;
import org.eclipse.incquery.runtime.exception.IncQueryException;

import com.google.common.collect.Multiset;

import Concept.IndividualContainer;
import Concept.Segment;

public class PosLength extends TreatTestCase
{
	protected List<Segment> invalids;
	//protected IQDeltaMonitor<Segment> iqDeltaMonitor;
	protected LookaheadMatcherTreat lmt;
	
	@Override
	public String getName() {
		return "PosLength";
	}
	
	@Override
	public void load() {
		bmr.startStopper();
		
		URI resourceURI = FileBroker.getEMFUri(bc.getInstanceModelPath() + "/" + fileName);
		resourceSet = new ResourceSetImpl();
		
		try {
			//IncQueryCommon.setEIQOptions(getIQBC());
			engine = AdvancedIncQueryEngine.createUnmanagedEngine(resourceSet);
			//PosLengthMatcher.on(engine);
			if (TreatRegistrarImpl.LookaheadToEngineConnector.GetLookaheadMatcherTreat(engine) == null)
			{
				LookaheadMatcherTreat lmt_local = new LookaheadMatcherTreat(engine);
				TreatRegistrarImpl.LookaheadToEngineConnector.Connect(engine, lmt_local);
				lmt = TreatRegistrarImpl.LookaheadToEngineConnector.GetLookaheadMatcherTreat(engine);
			}
			else
				TreatRegistrarImpl.LookaheadToEngineConnector.Connect(engine, new LookaheadMatcherTreat(engine));
				lmt = TreatRegistrarImpl.LookaheadToEngineConnector.GetLookaheadMatcherTreat(engine);
			lmt.PowerTreatUp(PosLengthQuerySpecification.instance());
			
			// PosLengthMatcher patternMatcher = PosLengthMatcher.on(engine);
			//iqDeltaMonitor = new IQDeltaMonitor<Segment>(patternMatcher, "Source");
		} catch (IncQueryException e) {
			e.printStackTrace();
		}
		resource = resourceSet.getResource(resourceURI, true);
		if (resource.getContents().size() > 0 && resource.getContents().get(0) instanceof IndividualContainer) {
			pack = (IndividualContainer) resource.getContents().get(0);
		}
		
		bmr.setReadTime();
	}

	@Override
	public void check() {
		bmr.startStopper();
		//invalids = new ArrayList<Segment>(iqDeltaMonitor.getMatching());
		Multiset<LookaheadMatching> res;
		try {
			// this will match the pattern surely ... once upon a time
			res = lmt.matchThePattern(PosLengthQuerySpecification.instance());
			invalids = new ArrayList<Segment>();
			for(LookaheadMatching itRes : res.elementSet())//.toArrayList(false))
				invalids.add((Segment)itRes.get("Source"));
			// System.out.println(res.size() + "(" + res.uniqueSize() + ")");
		} catch (IncQueryException e) {
			e.printStackTrace();
		}
		bmr.addInvalid(invalids.size());
		bmr.addCheckTime();
	}
}
