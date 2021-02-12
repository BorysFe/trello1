import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "C:/Users/Office/IdeaProjects/trello1/src/test/resources/features/login.feature",
        glue = "steps")

public class RunCucumberTest {
}
