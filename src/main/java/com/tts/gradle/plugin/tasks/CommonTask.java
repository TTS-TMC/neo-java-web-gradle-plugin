package com.tts.gradle.plugin.tasks;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.gradle.api.DefaultTask;
import org.gradle.api.Task;
import org.gradle.api.tasks.TaskExecutionException;

import com.tts.gradle.plugin.CommandsAndParams;
import com.tts.gradle.plugin.NeoJavaWebExtension;


/**
 * This class represents the most commonly used Task with attributes.
 * Standard mandatory attributes are normally:
 *<pre>Required
		-a, --account	Subaccount name
		Type: string (up to 30 characters; lowercase letters and numbers, starting with a letter)
		-b, --application	Application name
		Type: string (up to 30 characters; lowercase letters and numbers, starting with a letter)
		-h, --host	Enter a region host.
		Type: URL, for acceptable values see Regions.
		-p, --password	To protect your password, enter it only when prompted by the console client and not explicitly as a parameter in the properties file or the command line.
		Type: string
		-u, --user	Use your email, SAP ID or user name
		Type: string
		</pre>
 * So this class takes care of building the basic commandline runner and verifying it. This clas is ment to be 
 * subclassed for further task specific attributes/actions 
 * @author mathias maerker
 *
 */
public class CommonTask extends DefaultTask {
	
	/**
	 * builds the default arguments for te neo cli. please be aware that if you call this method in a subclass 
	 * at index 0 you have to insert the actual command
	 * @return the default cli arguments
	 * @throws Throwable if a mandatory property is missing
	 */
	protected List<String> baseCommandlineArguments() throws Throwable {
		getLogger().info("Start building default commandline Arguments..");
		List<String> commands = new ArrayList<>();
		
		commands.add(CommandsAndParams.PARAM_ACCOUNT);
		commands.add(getExtension().getAccount()); 	
		commands.add(CommandsAndParams.PARAM_APPLICATION);
		commands.add(getExtension().getApplicationName());
		commands.add(CommandsAndParams.PARAM_HOST);
		commands.add(getExtension().getHost());
		commands.add(CommandsAndParams.PARAM_USER);
		commands.add(getExtension().getUser());
		commands.add(CommandsAndParams.PARAM_PASSWORD);
		commands.add(getExtension().getPassword());
		
		return commands;
	}
	
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
