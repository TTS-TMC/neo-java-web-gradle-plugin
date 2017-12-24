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
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("test");
		list.add("a");
		list.add("bla");
		System.out.println(list.toString());
		Predicate<String> p = s -> s == null || s.equals("");
		
		boolean a = list.stream().noneMatch(s -> s == null || s.equals(""));
		System.out.println(a);
		
	}
}
