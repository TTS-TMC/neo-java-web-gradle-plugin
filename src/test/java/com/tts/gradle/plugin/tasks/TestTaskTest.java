package com.tts.gradle.plugin.tasks;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;
import org.gradle.testkit.runner.TaskOutcome;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class TestTaskTest {
	@Rule
	public final TemporaryFolder testProjectDir = new TemporaryFolder();

	@Test
	public void testPositiveCustomLocation() throws Exception {
		setup();
		BuildResult result = GradleRunner.create().withProjectDir(testProjectDir.getRoot()).withPluginClasspath()
				.withArguments("info", "--info").forwardOutput().withDebug(true).build();
		// System.out.println(result.getOutput());

		assertThat(result.task(":" + "info").getOutcome(), equalTo(TaskOutcome.SUCCESS));

	}

	private void setup() throws IOException {
		File buildFile = testProjectDir.newFile("build.gradle");
		PrintWriter printWriter = new PrintWriter(buildFile);
		printWriter.println("plugins { id 'com.tts.scp-neo-plugin' version '0.0.2' } ");
		printWriter.println("scpSettings { ");
		printWriter.println("sdkVersion = '3.39.10'");
		printWriter.println("enviromentVariables = [test: 'test']");
		printWriter.println("}");
		printWriter.println("repositories { mavenCentral()	}");
		printWriter.flush();
		printWriter.close();
	}

}
