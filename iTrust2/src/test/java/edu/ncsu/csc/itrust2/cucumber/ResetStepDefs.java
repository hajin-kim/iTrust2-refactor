package edu.ncsu.csc.itrust2.cucumber;

import io.cucumber.java.en.Given;
import org.junit.Assert;

public class ResetStepDefs extends CucumberTest {

    @Given("The database is empty")
    public void emptyDB() {
        // This is run once, to reset the DB and prepare for unit/API tests when
        // running with the JUnit runner locally
        Assert.assertTrue(true);
    }
}
