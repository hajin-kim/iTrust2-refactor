package edu.ncsu.csc.itrust2.cucumber;

import edu.ncsu.csc.itrust2.common.DBUtils;

import javax.sql.DataSource;

import io.cucumber.java.Before;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest
@AutoConfigureMockMvc
public class CucumberTestContextConfiguration {

    @Autowired protected DataSource dataSource;

    @Before
    public void beforeTests() {
        DBUtils.resetDB(dataSource);
    }
}
