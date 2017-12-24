package com.tts.gradle.plugin.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.TaskExecutionException;

import com.tts.gradle.plugin.CommandsAndParams;

public class Deploy extends AbstractTask{

	private List<String> command = new ArrayList<>();
	// commandLine neo(), 'deploy', '--account', account, '--application',
	// application, '--host', host, '--password', password, '--user', user,
	// '--source', war.archivePath, '--ev', destinationName, '-V',
	// '-Dspring.profiles.active=production', '--runtime-version', '3', '--runtime',
	// 'neo-java-web'
	@TaskAction
	public void deploy() {
		getLogger().info("Entering deploy task class");
		command.add(CommandsAndParams.COMMAND_DEPLOY);
		command.add(CommandsAndParams.PARAM_ACCOUNT);
		command.add(getExtension().getAccount()); 	
		command.add(CommandsAndParams.PARAM_APPLICATION);
		command.add(getExtension().getApplicationName());
		command.add(CommandsAndParams.PARAM_HOST);
		command.add(getExtension().getHost());
		command.add(CommandsAndParams.PARAM_USER);
		command.add(getExtension().getUser());
		command.add(CommandsAndParams.PARAM_PASSWORD);
		command.add(getExtension().getPassword());
		command.add(CommandsAndParams.PARAM_SOURCE);
		command.add(getExtension().getSourceFileLocation());
		command.add(CommandsAndParams.PARAM_RUNTIME_VERSION);
		command.add(getExtension().getRuntimeVersion());
		command.add(CommandsAndParams.PARAM_RUNTIME);
		command.add(getExtension().getRuntime());
		

		//Validation that all required properties are set
		boolean b = command.stream().noneMatch(s -> s == null || s.equals(""));
		if (!b) {
			throw new TaskExecutionException(this,
					new Throwable("Seems that not all required properties are set, please check your gradle build file"));
		}
		
		//Now we add optional parameters
		if (getExtension().getEnviromentVariables() != null && !getExtension().getEnviromentVariables().isEmpty()) {
			getLogger().info("Adding enviroment Variables");
			Set<Entry<String,String>> set = getExtension().getEnviromentVariables().entrySet();
			for (Entry<String, String> entry : set) {
				command.add(CommandsAndParams.PARAM_ENV_VARS);
				command.add(entry.getKey() + "=" + entry.getValue());
			}
			
		}
		
		//jvm arguments
		if (getExtension().getJvmArgs() != null && !getExtension().getJvmArgs().isEmpty()) {
			getLogger().info("Adding jvm args");
			for (String jvmArg : getExtension().getJvmArgs()) {
				command.add(CommandsAndParams.PARAM_JVM_ARGS);
				command.add(jvmArg);
			}
		}
		// Delta Deploy
		if (getExtension().isDelta()) {
			getLogger().info("Adding delta deploy");
			command.add(CommandsAndParams.PARAM_DELTA);
		}
		
		getLogger().info("Running command " + CommandsAndParams.COMMAND_DEPLOY + " with params. " + command.toString());
		cliRunner(command);
		
		
	}
}
