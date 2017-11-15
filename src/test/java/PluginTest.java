import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.PrintWriter;

import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;
import org.gradle.testkit.runner.TaskOutcome;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;


public class PluginTest {

	  @Rule public final TemporaryFolder testProjectDir =
		       new TemporaryFolder();

		  @Test public void test() throws Exception {
			  setUpTestProject();
		    BuildResult result = GradleRunner.create()
		        .withProjectDir(testProjectDir.getRoot())
		        .withPluginClasspath()
		        .withArguments("installSdk", "--info")
		        .build();
		    assertThat(
		      result.task(":" + "installSdk").getOutcome(),
		         equalTo(TaskOutcome.SUCCESS));
		    //test that .sdk directory was created
		    File sdkLocation = new File(testProjectDir.getRoot().getPath().concat(File.separator).concat(".sdk"));

		    
		    assertThat("Sdk was downloaded and extracted to default location", sdkLocation.exists(), equalTo(true));
		    
		    
		  }
		  
		  private void setUpTestProject() throws Exception {
			  File buildFile = testProjectDir.newFile("build.gradle");
			  PrintWriter printWriter = new PrintWriter(buildFile);
			  printWriter.println("plugins { id 'scp-neo-plugin' } ");
			  printWriter.println("scpSettings { sdkVersion = '3.39.10' }");
			  printWriter.println("repositories { mavenCentral()	}");
			  printWriter.flush();
			  printWriter.close();
			}
    	
}
