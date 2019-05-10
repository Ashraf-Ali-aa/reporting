/*******************************************************************************
 * Copyright 2013-2019 Qaprosoft (http://www.qaprosoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.qaprosoft.zafira.services.services.application.emails;

import com.qaprosoft.zafira.models.db.*;
import com.qaprosoft.zafira.models.db.config.Argument;
import com.qaprosoft.zafira.models.db.config.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestRunResultsEmail implements IEmailMessage
{
	private static final String SUBJECT = "%s: %s";

	private Map<String, String> configuration = new HashMap<>();
	private TestRun testRun;
	private List<Test> tests;
	private String jiraURL;
	private boolean showOnlyFailures = false;
	private boolean showStacktrace = true;
	private int successRate;
	private String elapsed;

	public TestRunResultsEmail(Configuration config, TestRun testRun, List<Test> tests)
	{
		for (Argument arg : config.getArg())
		{
			configuration.put(arg.getKey(), arg.getValue());
		}
		this.testRun = testRun;
		this.tests = tests;
		if(testRun.getElapsed() != null)
		{
			int s = testRun.getElapsed() % 60;
			int m = (testRun.getElapsed() / 60) % 60;
			int h = (testRun.getElapsed() / (60 * 60)) % 24;
			this.elapsed = String.format("%02d:%02d:%02d", h,m,s);
		}
	}

	public Map<String, String> getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Map<String, String> configuration) {
		this.configuration = configuration;
	}

	public TestRun getTestRun()
	{
		return testRun;
	}

	public void setTestRun(TestRun testRun)
	{
		this.testRun = testRun;
	}

	public List<Test> getTests()
	{
		return tests;
	}

	public void setTests(List<Test> tests)
	{
		this.tests = tests;
	}

	public String getJiraURL()
	{
		return jiraURL;
	}

	public void setJiraURL(String jiraURL)
	{
		this.jiraURL = jiraURL;
	}

	public void setJiraURL(Setting setting)
	{
		this.jiraURL = setting != null ? setting.getValue() : "";
	}

	public boolean isShowOnlyFailures()
	{
		return showOnlyFailures;
	}

	public void setShowOnlyFailures(boolean showOnlyFailures)
	{
		this.showOnlyFailures = showOnlyFailures;
	}

	public boolean isShowStacktrace() {
		return showStacktrace;
	}

	public void setShowStacktrace(boolean showStacktrace) {
		this.showStacktrace = showStacktrace;
	}

	public int getSuccessRate()
	{
		return successRate;
	}

	public void setSuccessRate(int successRate)
	{
		this.successRate = successRate;
	}

	public String getElapsed()
	{
		return elapsed;
	}

	@Override
	public String getSubject()
	{
		String status = buildStatusText(testRun);
		return String.format(SUBJECT, status, testRun.getName(configuration));
	}

	public static String buildStatusText(TestRun testRun){
		return Status.PASSED.equals(testRun.getStatus()) && testRun.isKnownIssue() && ! testRun.isBlocker() ? "PASSED (known issues)"
				: testRun.isBlocker() ? "FAILED (BLOCKERS)" : testRun.getStatus().name();
	}

	@Override
	public EmailType getType() {
		return EmailType.TEST_RUN;
	}

	@Override
	public List<Attachment> getAttachments()
	{
		return null;
	}

	@Override
	public String getText()
	{
		return null;
	}

}
