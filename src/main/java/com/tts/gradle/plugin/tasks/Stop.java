package com.tts.gradle.plugin.tasks;

import java.util.List;

import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.TaskExecutionException;

import com.tts.gradle.plugin.CommandsAndParams;

public class Stop extends CommonTask{

	
	/**
	 * Stops the application in the Sap Cloud Platform account
	 */
	
	//TODO implement --synchronous
	@TaskAction
	public void start() {
		getLogger().info("Entering Stop task..");
		
		List<String> command;
		try {
			command = super.baseCommandlineArguments(CommandsAndParams.COMMAND_STOP);
		} catch (Throwable e) {
			throw new TaskExecutionException(this, e);
		}
		
		getLogger().info("Running command " + CommandsAndParams.COMMAND_STOP + " with params. " + command.toString());
		cliRunner(command);
		
	}
}
