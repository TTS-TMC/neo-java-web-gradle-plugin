package com.tts.gradle.plugin.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.TaskExecutionException;

public class TestTask extends CommonTask {
	
	
	
	@TaskAction
	private void pub() {
		getLogger().info(getExtension().toString());
		try {
			getLogger().info("USER from gradle user home: " + getExtension().getUser() );
		} catch (Throwable e) {
			throw new TaskExecutionException(this, e);
		}
	}
	

}
