package hu.bme.mit.trainbenchmark.benchmark.treat;

import java.io.IOException;

import org.apache.commons.cli.ParseException;

public class Main {

	/**
	 * @param args
	 * @throws ParseException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws ParseException, IOException
	{
		TreatBenchmarkLogic benchmarkLogic = new TreatBenchmarkLogic(args);
		benchmarkLogic.runBenchmarks();
	}

}
