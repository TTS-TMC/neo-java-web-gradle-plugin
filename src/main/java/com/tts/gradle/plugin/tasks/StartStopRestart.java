package com.tts.gradle.plugin.tasks;

import java.util.ArrayList;
import java.util.List;

import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.TaskExecutionException;

import com.tts.gradle.plugin.CommandsAndParams;

public class StartStopRestart extends AbstractTask{

	private List<String> command = new ArrayList<>();
	
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
	
	//TODO implement --synchronous and think about --application-process-id
	@TaskAction
	public void start() {
		getLogger().info("Entering Start task..");
		command.add(CommandsAndParams.COMMAND_STOP);
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
		
		//Validation that all required properties are set
		boolean b = command.stream().noneMatch(s -> s == null || s.equals(""));
		if (!b) {
			throw new TaskExecutionException(this,
					new Throwable("Seems that not all required properties are set, please check your gradle build file"));
		}
		
		
		getLogger().info("Running command " + CommandsAndParams.COMMAND_STOP + " with params. " + command.toString());
		cliRunner(command);
		
	}
}
