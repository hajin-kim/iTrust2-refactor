package edu.ncsu.csc.itrust2.unit;

import edu.ncsu.csc.itrust2.TestConfig;
import edu.ncsu.csc.itrust2.forms.ICDCodeForm;
import edu.ncsu.csc.itrust2.models.ICDCode;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Class to test that ICDCode and ICDCodeForms are created from each other properly.
 *
 * @author Thomas
 */
@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest(classes = TestConfig.class)
public class ICDCodeTest {

    @Test
    @Transactional
    public void testCodes() {
        final ICDCodeForm form = new ICDCodeForm();
        form.setId(1L);
        form.setCode("T11");
        form.setDescription("Testing");
        final ICDCode base = new ICDCode("T11", "Testing");

        final ICDCode code = new ICDCode(form);
        assertEquals(code.getCode(), base.getCode());
        assertEquals(code.getDescription(), base.getDescription());

        final ICDCodeForm f2 = new ICDCodeForm(code);
        assertEquals(form, f2);

        assertEquals("T11", code.getCode());
        assertEquals(1L, (long) code.getId());
        assertEquals("Testing", code.getDescription());
    }

    @Test
    @Transactional
    public void testInvalidCodes() {
        final ICDCodeForm form = new ICDCodeForm();
        form.setCode("111");
        form.setDescription("Invalid");

        @SuppressWarnings("unused") // we want to do an assignment so that
        // the compiler doesn't optimize it away
        ICDCode code;
        try {
            code = new ICDCode(form);
            fail();
        } catch (final IllegalArgumentException e) {
            assertEquals("Code must begin with a capital letter: 111", e.getMessage());
        }
        form.setCode("AA1");
        try {
            code = new ICDCode(form);
            fail();
        } catch (final IllegalArgumentException e) {
            assertEquals("Second character of code must be a digit: AA1", e.getMessage());
        }
        form.setCode("A1.A");
        try {
            code = new ICDCode(form);
            fail();
        } catch (final IllegalArgumentException e) {
            assertEquals("Third character of code must be alphanumeric: A1.A", e.getMessage());
        }
        form.setCode("A11A");
        try {
            code = new ICDCode(form);
            fail();
        } catch (final IllegalArgumentException e) {
            assertEquals("Fourth character of code must be decimal: A11A", e.getMessage());
        }
        form.setCode("A11..");
        try {
            code = new ICDCode(form);
            fail();
        } catch (final IllegalArgumentException e) {
            assertEquals("Characters after decimal must be alphanumeric: A11..", e.getMessage());
        }
        form.setCode("A1");
        try {
            code = new ICDCode(form);
            fail();
        } catch (final IllegalArgumentException e) {
            assertEquals("Code must be at least three characters: A1", e.getMessage());
        }
        form.setCode("A11.1AA1A");
        try {
            code = new ICDCode(form);
            fail();
        } catch (final IllegalArgumentException e) {
            assertEquals(
                    "Code too long! Max 8 characters including decimal: A11.1AA1A", e.getMessage());
        }
        form.setCode("A11.111A");
        // valid
        code = new ICDCode(form);
    }
}
