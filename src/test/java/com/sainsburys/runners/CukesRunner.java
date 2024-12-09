package com.sainsburys.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "html:target/cucumber-reports.html",
                "json:target/cucumber.json",
                "pretty",
                "rerun:target/rerun.txt",
                "me.jvt.cucumber.report.PrettyReports:target/cucumber"
        },
        features = "src/test/resources/features",
        glue = "com/sainsburys/stepDefs",
        dryRun = false,
        tags = "@login",
        publish = true // generating a  report link

)
public class CukesRunner {}