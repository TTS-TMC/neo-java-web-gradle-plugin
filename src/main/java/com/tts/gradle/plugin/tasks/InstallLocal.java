package com.tts.gradle.plugin.tasks;

import java.util.ArrayList;
import java.util.List;

import org.gradle.api.tasks.TaskAction;

import com.tts.gradle.plugin.CommandsAndParams;

public class InstallLocal extends AbstractTask {



	@TaskAction
	public void installLocal() {
		getLogger().info("Entering installLocal task class");


		getLogger().info("Preparing command " + CommandsAndParams.COMMAND_INSTALL_LOCAL);
		List<String> command = new ArrayList<>();

		command.add(CommandsAndParams.COMMAND_INSTALL_LOCAL);
		command.add(CommandsAndParams.PARAM_LOCATION);
		command.add(getExtension().getServerLocation());
		getLogger().info("Intstalling Server to: " + getExtension().getServerLocation() );
		
		
		getLogger().info("Running command " + CommandsAndParams.COMMAND_INSTALL_LOCAL + " with params. " + command.toString());
		cliRunner(command);
	}


}
