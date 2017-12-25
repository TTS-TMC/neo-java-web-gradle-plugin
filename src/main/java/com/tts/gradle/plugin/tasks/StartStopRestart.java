package com.tts.gradle.plugin.tasks;

import java.util.List;

import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.TaskExecutionException;

import com.tts.gradle.plugin.CommandsAndParams;

public class StartStopRestart extends CommonTask{

	
	/**
	 * 
		Required
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
	 */
	
	//TODO implement --synchronous
	@TaskAction
	public void start() {
		getLogger().info("Entering Start task..");
		
		List<String> command;
		try {
			command = super.baseCommandlineArguments();
		} catch (Throwable e) {
			throw new TaskExecutionException(this, e);
		}
		
		command.add(0, CommandsAndParams.COMMAND_START);
		
		getLogger().info("Running command " + CommandsAndParams.COMMAND_START + " with params. " + command.toString());
		cliRunner(command);
		
	}
}
