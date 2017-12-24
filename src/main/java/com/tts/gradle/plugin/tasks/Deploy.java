package com.tts.gradle.plugin.tasks;

import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.TaskExecutionException;

import com.tts.gradle.plugin.CommandsAndParams;

public class Deploy extends CommonTask {

	// commandLine neo(), 'deploy', '--account', account, '--application',
	// application, '--host', host, '--password', password, '--user', user,
	// '--source', war.archivePath, '--ev', destinationName, '-V',
	// '-Dspring.profiles.active=production', '--runtime-version', '3', '--runtime',
	// 'neo-java-web'
	@TaskAction
	public void deploy() {
		getLogger().info("Entering deploy task class");
		List<String> commands = null;
		try {
			commands = super.baseCommandlineArguments();
			commands.add(0, CommandsAndParams.COMMAND_DEPLOY);
			
			commands.add(CommandsAndParams.PARAM_SOURCE);
			commands.add(getExtension().getSourceFileLocation());
			commands.add(CommandsAndParams.PARAM_RUNTIME_VERSION);
			commands.add(getExtension().getRuntimeVersion());
			commands.add(CommandsAndParams.PARAM_RUNTIME);
			commands.add(getExtension().getRuntime());

		} catch (Throwable e) {
			throw new TaskExecutionException(this, e);
		}

		// Now we add optional parameters
		if (getExtension().getEnviromentVariables() != null && !getExtension().getEnviromentVariables().isEmpty()) {
			getLogger().info("Adding enviroment Variables");
			Set<Entry<String, String>> set = getExtension().getEnviromentVariables().entrySet();
			for (Entry<String, String> entry : set) {
				commands.add(CommandsAndParams.PARAM_ENV_VARS);
				commands.add(entry.getKey() + "=" + entry.getValue());
			}

		}

		// jvm arguments
		if (getExtension().getJvmArgs() != null && !getExtension().getJvmArgs().isEmpty()) {
			getLogger().info("Adding jvm args");
			for (String jvmArg : getExtension().getJvmArgs()) {
				commands.add(CommandsAndParams.PARAM_JVM_ARGS);
				commands.add(jvmArg);
			}
		}
		// Delta Deploy
		if (getExtension().isDelta()) {
			getLogger().info("Adding delta deploy");
			commands.add(CommandsAndParams.PARAM_DELTA);
		}
		
		//FIXME password is visible
		getLogger()
				.info("Running command " + CommandsAndParams.COMMAND_DEPLOY + " with params. " + commands.toString());
		cliRunner(commands);

	}
}
