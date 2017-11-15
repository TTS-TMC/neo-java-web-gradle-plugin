package com.tts.gradle.plugin.tasks;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.TaskExecutionException;

public class InstallLocal extends AbstractTask {

	@TaskAction
	public void installLocal() {
		if (!isSdkInstalled()) {
			throw new TaskExecutionException(this, new Throwable("Seems that the Sdk is not installed, consider running task installSdk"));
		}
		
		String command = getNeoExecutable() + " install-local " + "--location " + getExtension().getServerLocation();
		ProcessBuilder builder = new ProcessBuilder(command);
		try {
			Process p = builder.start();
			Scanner s = new Scanner( p.getInputStream() );
			System.out.println(s.nextLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String getNeoExecutable() {
		String neoPath = getExtension().getSdkLocation().concat(File.separator).concat("tools");
		if (System.getProperty("os.name").startsWith("Windows")) {
			neoPath = neoPath.concat(File.separator).concat("neo.bat");
		}else {
			neoPath = neoPath.concat(File.separator).concat("neo.sh");
		}
		return neoPath;
	}
}
