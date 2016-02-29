package com.aquatic.waterflow;

import static org.junit.Assert.*;
import io.dropwizard.testing.junit.DropwizardAppRule;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import com.aquatic.waterflow.config.ApplicationConfiguration;

/**
 * Unit test for {@link WaterFlowApplication} class
 */
public class WaterFlowApplicationTest {

	@ClassRule
    public static final DropwizardAppRule<ApplicationConfiguration> RULE =
            new DropwizardAppRule<ApplicationConfiguration>(WaterFlowApplication.class, "src/test/resources/config.yml");
	
	private String name;
	
	@Before
	public void setUp() throws Exception {
		name = RULE.getApplication().getName();
	}

	@Test
	public void testApplicationName() {
		assertFalse("Application name should not be empty", name.isEmpty());
	}

}
