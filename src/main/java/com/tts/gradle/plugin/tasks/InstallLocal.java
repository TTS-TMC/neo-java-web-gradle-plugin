package com.tts.gradle.plugin.tasks;

import java.util.ArrayList;
import java.util.List;

import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.TaskExecutionException;

import com.tts.gradle.plugin.CommandsAndParams;

public class InstallLocal extends CommonTask {



	@TaskAction
	public void installLocal() {
		getLogger().info("Entering installLocal task class");


		getLogger().info("Preparing command " + CommandsAndParams.COMMAND_INSTALL_LOCAL);
		List<String> command = new ArrayList<>();
		
		command.add(CommandsAndParams.COMMAND_INSTALL_LOCAL);
		command.add(CommandsAndParams.PARAM_LOCATION);
		try {
			command.add(getExtension().getServerLocation());
			getLogger().info("Intstalling Server to: " + getExtension().getServerLocation() );
		} catch (Throwable e) {
			throw new TaskExecutionException(this, e);
		}
		
		
		getLogger().info("Running command " + CommandsAndParams.COMMAND_INSTALL_LOCAL + " with params. " + command.toString());
		cliRunner(command);
	}


}
