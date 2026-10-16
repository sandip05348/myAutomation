package myAutomation;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import java.io.File;

public class SetupDockerGrid {
	

//	@BeforeSuite
//	public void startDockerGrid() throws Exception {
//	    new ProcessBuilder("cmd", "/c", "startDockerGrid.bat").inheritIO().start().waitFor();
//
//	    java.net.http.HttpClient client = java.net.http.HttpClient.newHttpClient();
//	    java.net.http.HttpRequest req = java.net.http.HttpRequest.newBuilder(
//	            java.net.URI.create("http://localhost:4444/status")).build();
//
//	    long end = System.currentTimeMillis() + 90_000;
//	    while (System.currentTimeMillis() < end) {
//	        try {
//	            String body = client.send(req, java.net.http.HttpResponse.BodyHandlers.ofString()).body();
//	            if (body.contains("\"ready\": true") || body.contains("\"ready\":true")) return;
//	        } catch (Exception ignored) { }
//	        Thread.sleep(2000);
//	    }
//	    throw new RuntimeException("Selenium Grid did not become ready in 90s");
//	}
//	private static final AtomicBoolean stopped = new AtomicBoolean(false);
//	@SuppressWarnings("deprecation")
//	@AfterSuite
//	public void stopDockerGrid() throws InterruptedException, IOException {
//		if (stopped.compareAndSet(false, true)) {
//	        new ProcessBuilder("cmd", "/c", "stopDockerGrid.bat")
//	                .inheritIO().start().waitFor();
//	    }
//		// Thread.sleep(20000);
//	     //   Runtime.getRuntime().exec("taskkill /f /im cmd.exc");
//	}
	
	private void runBat(String bat, String logName) throws IOException, InterruptedException {
	    new ProcessBuilder("cmd", "/c", bat)
	            .redirectErrorStream(true)
	            .redirectOutput(new File("target/" + logName))
	            .start()
	            .waitFor();
	}

	@BeforeSuite
	public void startDockerGrid() throws Exception {
	    runBat("startDockerGrid.bat", "docker-start.log");
	    Thread.sleep(20000);
	}

	@AfterSuite
	public void stopDockerGrid() throws Exception {
	    runBat("stopDockerGrid.bat", "docker-stop.log");
	}

}
