package com.tts.gradle.plugin.tasks;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskExecutionException;

import com.tts.gradle.plugin.CommandsAndParams;
import com.tts.gradle.plugin.NeoJavaWebExtension;

/**
 * This class represents the most commonly used Task with attributes. Standard
 * mandatory attributes are normally:
 * 
 * <pre>
 * Required
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
 * </pre>
 * 
 * So this class takes care of building the basic commandline runner and
 * verifying it. This class is meant to be subclassed for further task specific
 * attributes/actions
 * 
 * @author mathias maerker
 *
 */
public class CommonTask extends DefaultTask {

	/**
	 * Builds the default arguments for the neo cli. It add either neo.sh or neo.bat at the beginning depending on OS  
	 * Further more we check if the neccesary prerequisits are fullfilled e.g. neo
	 * sdk is installed
	 * 
	 * @param command the command to be inserted see {@link CommandsAndParams}
	 * @return the default cli arguments
	 * @throws Throwable if a mandatory property is missing
	 */
	protected List<String> baseCommandlineArguments(String command) throws Throwable {
//		getLogger().info("Validating");
//		isSdkInstalled();
//		
//		getLogger().info("Start building default commandline Arguments..");
		List<String> commands = getNeoExecutable();
//
//		if (getNeoExecutable().endsWith(".sh")) {
//			getLogger().info("adding sh for shell execution");
//			commands.add("sh");
//		}
//		commands.add(getNeoExecutable());
//		getLogger().info("neo exectuable is set to " + getNeoExecutable());
		
		commands.add(command);
		getLogger().info("adding command " + command);
		
		commands.add(CommandsAndParams.PARAM_ACCOUNT);
		getLogger().info("adding account");
		commands.add(getExtension().getAccount());
		getLogger().info("adding application");
		commands.add(CommandsAndParams.PARAM_APPLICATION);
		commands.add(getExtension().getApplicationName());
		getLogger().info("adding host");
		commands.add(CommandsAndParams.PARAM_HOST);
		commands.add(getExtension().getHost());
		getLogger().info("adding user");
		commands.add(CommandsAndParams.PARAM_USER);
		commands.add(getExtension().getUser());
		getLogger().info("adding pasword");
		commands.add(CommandsAndParams.PARAM_PASSWORD);
		commands.add(getExtension().getPassword());
		getLogger().info("Leaving default commandline argument builder");

		return commands;
	}

	/**
	 * Tests if the neo sdk is installed
	 * 
	 * @return true if sdkLocation exists, is a directory and isn't empty
	 * @throws Throwable if no neo sdk is found
	 */
	protected boolean isSdkInstalled() throws Throwable {
		// TODO probably we need to test more stuff here? f.e. if neo.sh/bat is present?
		File sdk = new File(getExtension().getSdkLocation());

		if (sdk.exists() && sdk.isDirectory() && sdk.list().length > 0) {
			return true;
		}
		return false;
	}

	protected NeoJavaWebExtension getExtension() {
		return getProject().getExtensions().findByType(NeoJavaWebExtension.class);
	}


	protected List<String> getNeoExecutable() throws Throwable {
		StringBuilder builder = new StringBuilder(getExtension().getSdkLocation());
		builder.append(File.separator);
		builder.append("tools");
		builder.append(File.separator);
		if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
			builder.append("neo.bat");
		} else {
			builder.append("neo.sh");
		}
		if (!new File(builder.toString()).exists()) {
			throw new Throwable("neo.sh or neo.bat executable file is missing");
		}
		String neoExecutable =  builder.toString();
		
		getLogger().info("Validating");
		isSdkInstalled();
		
		getLogger().info("Start building default commandline Arguments..");
		List<String> commands = new ArrayList<>();

		if (neoExecutable.endsWith(".sh")) {
			getLogger().info("adding sh for shell execution");
			commands.add("sh");
		}
		commands.add(neoExecutable);
		getLogger().info("neo exectuable is set to " + neoExecutable);
		
		return commands;
	}

	protected void cliRunner(List<String> commands) {
		try {
			ProcessBuilder builder = new ProcessBuilder(commands);
			getLogger().info("Processbuilder about to start for class " + getClass().getName());
			Process p = builder.start();
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
			int exitCode = p.waitFor();
			if (exitCode != 0) {
				throw new TaskExecutionException(this, new Throwable("There where some errors, please check the log files"));
			}
		} catch (IOException | InterruptedException e) {
			throw new TaskExecutionException(this, e);
		} 
	}
}
