package com.tts.gradle.plugin.tasks;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.gradle.api.DefaultTask;
import org.gradle.api.Task;
import org.gradle.api.tasks.TaskExecutionException;

import com.tts.gradle.plugin.NeoJavaWebExtension;

public abstract class AbstractTask extends DefaultTask {
	
	/**
	 * Tests if the neo sdk is installed
	 * @return true if sdkLocation exists, is a directory and isn't empty
	 */
	protected boolean isSdkInstalled() {
		//TODO probably we need to test more stuff here? f.e. if neo.sh/bat is present?
		File sdk = new File(getExtension().getSdkLocation());
		if (sdk.exists() && sdk.isDirectory() && sdk.list().length > 0) {
			return true;
		}
		return false;
	}

	protected NeoJavaWebExtension getExtension() {
		return getProject().getExtensions().findByType(NeoJavaWebExtension.class);
	}
	
	protected void validate(Task task) {
		getExtension().validate(task);
	}
	
	protected String getNeoExecutable() {
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
	
	protected List<String> commandbuilder(){
		return null;
	}

	protected void cliRunner(List<String> commands) {
		
		getExtension().validate(this);
		
		if (!isSdkInstalled()) {
			throw new TaskExecutionException(this,
					new Throwable("Seems that the Sdk is not installed, consider running task installSdk"));
		}
		
		//needed so .sh files are executed
		if (getNeoExecutable().endsWith(".sh")) {
			commands.add(0, "sh");
		}
		commands.add(1, getNeoExecutable());
		
		ProcessBuilder builder = new ProcessBuilder(commands);
		try { 
			getLogger().info("Processbuilder about to start for class " + getClass().getName());
			Process p = builder.start();
			//p.waitFor();
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
