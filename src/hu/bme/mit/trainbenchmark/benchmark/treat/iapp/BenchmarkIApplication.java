package hu.bme.mit.trainbenchmark.benchmark.treat.iapp;


import hu.bme.mit.trainbenchmark.benchmark.treat.Main;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

public class BenchmarkIApplication implements IApplication {

	@Override
	public Object start(IApplicationContext context) throws Exception {
		String[] args = (String[])context.getArguments().get("application.args");
		Main.main(args);
		return IApplication.EXIT_OK;
	}

	@Override
	public void stop() {
	}

}
