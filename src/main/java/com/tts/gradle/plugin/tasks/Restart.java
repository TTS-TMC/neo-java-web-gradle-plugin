package com.tts.gradle.plugin.tasks;

import java.util.List;

import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.TaskExecutionException;

import com.tts.gradle.plugin.CommandsAndParams;

public class Restart extends CommonTask{

	
	/**
	 * Restarts the application in the Sap Cloud Platform account
	 */
	
	//TODO implement --synchronous and restart single process id
	@TaskAction
	public void start() {
		getLogger().info("Entering Restart task..");
		
		List<String> command;
		try {
			command = super.baseCommandlineArguments(CommandsAndParams.COMMAND_RESTART);
		} catch (Throwable e) {
			throw new TaskExecutionException(this, e);
		}
		
		
		getLogger().info("Running command " + CommandsAndParams.COMMAND_RESTART + " with params. " + command.toString());
		cliRunner(command);
		
	}
}
