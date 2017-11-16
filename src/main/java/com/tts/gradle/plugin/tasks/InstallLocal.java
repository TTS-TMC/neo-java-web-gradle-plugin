package com.tts.gradle.plugin.tasks;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.TaskExecutionException;

public class InstallLocal extends AbstractTask {

	@TaskAction
	public void installLocal() {
		getLogger().info("Entering installLocal task class");
		if (!isSdkInstalled()) {
			throw new TaskExecutionException(this,
					new Throwable("Seems that the Sdk is not installed, consider running task installSdk"));
		}

		getLogger().info("Preparing command");
		List<String> command = new ArrayList<>();
		command.add("sh");
		command.add(getNeoExecutable());
		command.add("install-local");
		command.add("--location");
		command.add(getExtension().getServerLocation());
		getLogger().info("Intstalling Server to: " + getExtension().getServerLocation() );
		
		
		getLogger().info("Preparing processbuilder");
		ProcessBuilder builder = new ProcessBuilder(command);
		try {
			getLogger().info("Processbuilder about to start");
			Process p = builder.start();
			p.waitFor();
			String line;
			getLogger().info("Reading Processbuilder InputStream");
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = input.readLine()) != null) {
				System.out.println(line);
			}
			input.close();
			line = null;
			getLogger().info("Reading Processbuilder ErrorStream");
			input = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			while ((line = input.readLine()) != null) {
				System.out.println(line);
			}
			input.close();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	private String getNeoExecutable() {
		StringBuilder builder = new StringBuilder(getExtension().getSdkLocation());
		builder.append(File.separator);
		builder.append("tools");
		builder.append(File.separator);
		if (System.getProperty("os.name").startsWith("Windows")) {
			builder.append("neo.bat");
		} else {
			builder.append("neo.sh");
		}
		return builder.toString();
	}
}
