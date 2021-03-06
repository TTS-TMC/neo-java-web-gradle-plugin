package com.tts.gradle.plugin.tasks;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.PrintWriter;

import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;
import org.gradle.testkit.runner.TaskOutcome;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

public class InstallLocalTest {

	@Rule
	public final TemporaryFolder testProjectDir = new TemporaryFolder();

	//@Test
	public void installLocalWithDefaultValuesTest() throws Exception {
		setUpDefaultProject();
		BuildResult result = GradleRunner.create().withProjectDir(testProjectDir.getRoot()).withPluginClasspath()
				.withArguments("installSdk", "installLocal", "--info").forwardOutput().build();
		//System.out.println(result.getOutput());
		assertThat(result.task(":" + "installLocal").getOutcome(), equalTo(TaskOutcome.SUCCESS));
		File serverLocation = new File(testProjectDir.getRoot().getPath().concat(File.separator).concat(".sdk").concat(File.separator).concat("server"));
		assertThat("server was installed in default location",serverLocation.exists(),  equalTo(true));
	}
	
	//@Test
	public void installLocalWithCustomServerLocation() throws Exception {
		setUpCustomProjectServerlocation();
		BuildResult result = GradleRunner.create().withProjectDir(testProjectDir.getRoot()).withPluginClasspath()
				.withArguments("installSdk", "installLocal", "--info").build();
		//System.out.println(result.getOutput());
		assertThat(result.task(":" + "installLocal").getOutcome(), equalTo(TaskOutcome.SUCCESS));
		File serverLocation = new File(testProjectDir.getRoot().getPath().concat(File.separator).concat("test"));
		assertThat("server was installed in default location",serverLocation.exists(),  equalTo(true));
	}
	
	private void setUpDefaultProject() throws Exception {
		File buildFile = testProjectDir.newFile("build.gradle");
		PrintWriter printWriter = new PrintWriter(buildFile);
		printWriter.println("plugins { id 'com.tts.scp-neo-plugin' version '0.0.2' } ");
		printWriter.println("scpSettings { ");
		printWriter.println("sdkVersion = '3.39.10'");
		printWriter.println("serverLocation = \"${projectDir}"+ File.separator+ ".sdk" + File.separator + "server\"");
		printWriter.println("sdkLocation = \"${projectDir}"+ File.separator+ ".sdk\"");
		printWriter.println("}");
		printWriter.println("repositories { mavenCentral()	}");
		printWriter.flush();
		printWriter.close();
	}
	
	private void setUpCustomProjectServerlocation() throws Exception {
		File buildFile = testProjectDir.newFile("build.gradle");
		PrintWriter printWriter = new PrintWriter(buildFile);
		printWriter.println("plugins { id 'com.tts.scp-neo-plugin' version '0.0.2' } ");
		printWriter.println("scpSettings { ");
		printWriter.println("sdkVersion = '3.39.10'");
		printWriter.println("sdkLocation = \"${projectDir}"+ File.separator+ ".sdk\"");
		printWriter.println("serverLocation = \"${projectDir}"+ File.separator+ "test\"");
		printWriter.println("}");
		printWriter.println("repositories { mavenCentral()	}");
		printWriter.flush();
		printWriter.close();
	}
}
