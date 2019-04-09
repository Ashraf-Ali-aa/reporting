/*******************************************************************************
 * Copyright 2013-2018 QaProSoft (http://www.qaprosoft.com).
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
package com.qaprosoft.zafira.ws.controller.application;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.qaprosoft.zafira.models.db.Project;
import com.qaprosoft.zafira.models.db.TestRun;
import com.qaprosoft.zafira.services.exceptions.ServiceException;
import com.qaprosoft.zafira.services.services.application.ProjectService;
import com.qaprosoft.zafira.services.services.application.TestRunService;
import com.qaprosoft.zafira.services.services.application.VersionService;
import com.qaprosoft.zafira.services.services.application.integration.impl.JenkinsService;
import com.qaprosoft.zafira.services.services.application.integration.impl.JiraService;
import com.qaprosoft.zafira.services.services.application.integration.impl.SlackService;
import com.qaprosoft.zafira.ws.controller.AbstractController;
import com.qaprosoft.zafira.ws.swagger.annotations.ResponseStatusDetails;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import static com.qaprosoft.zafira.models.db.Setting.Tool.JENKINS;
import static com.qaprosoft.zafira.models.db.Setting.Tool.JIRA;
import static com.qaprosoft.zafira.models.db.Setting.Tool.SLACK;

@Controller
@Api(value = "Configuration API")
@CrossOrigin
@RequestMapping("api/config")
public class ConfigurationAPIController extends AbstractController {

    @Autowired
    private VersionService versionService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private JenkinsService jenkinsService;

    @Autowired
    private JiraService jiraService;

    @Autowired
    private SlackService slackService;

    @Autowired
    private TestRunService testRunService;

    @ResponseStatusDetails
    @ApiOperation(value = "Get version", nickname = "getVersion", httpMethod = "GET", response = Map.class)
    @ResponseStatus(HttpStatus.OK)
    @ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
    @RequestMapping(value = "version", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Map<String, Object> getVersion() throws ServiceException
    {
        Map<String, Object> config = new HashMap<>();
        config.put("service", versionService.getServiceVersion());
        config.put("client", versionService.getClientVersion());
        return config;
    }

    @ResponseStatusDetails
    @ApiOperation(value = "Get all projects", nickname = "getAllProjects", httpMethod = "GET", response = List.class)
    @ResponseStatus(HttpStatus.OK)
    @ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
    @RequestMapping(value = "projects", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Project> getAllProjects() throws ServiceException
    {
        return projectService.getAllProjects();
    }

    @ResponseStatusDetails
    @ApiOperation(value = "Get jenkins config", nickname = "getJenkinsConfig", httpMethod = "GET", response = Map.class)
    @ResponseStatus(HttpStatus.OK)
    @ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
    @RequestMapping(value = "jenkins", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Map<String, Object> getJenkinsConfig() throws ServiceException
    {
        Map<String, Object> config = new HashMap<>();
        config.put("connected", jenkinsService.isEnabledAndConnected(JENKINS));
        return config;
    }

    @ResponseStatusDetails
    @ApiOperation(value = "Get jira config", nickname = "getJiraConfig", httpMethod = "GET", response = Map.class)
    @ResponseStatus(HttpStatus.OK)
    @ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
    @RequestMapping(value = "jira", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Map<String, Object> getJiraConfig() throws ServiceException
    {
        Map<String, Object> config = new HashMap<>();
        config.put("connected", jiraService.isEnabledAndConnected(JIRA));
        return config;
    }

    @ResponseStatusDetails
    @ApiOperation(value = "Is slack available for test run", nickname = "isSlackAvailableForRun", httpMethod = "GET", response = Map.class)
    @ResponseStatus(HttpStatus.OK)
    @ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
    @RequestMapping(value = "slack/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Map<String, Object> isSlackAvailable(@PathVariable(value = "id") long id) throws ServiceException
    {
        Map<String, Object> config = new HashMap<>();
        TestRun tr = testRunService.getTestRunByIdFull(id);
        if (slackService.getWebhook() != null && StringUtils.isNotEmpty(tr.getSlackChannels()) && slackService.isEnabledAndConnected(SLACK))
        {
            config.put("available", true);
        }
        else
        {
            config.put("available", false);
        }
        return config;
    }
    
    @ResponseStatusDetails
    @ApiOperation(value = "Is slack available", nickname = "isSlackAvailable", httpMethod = "GET", response = Map.class)
    @ResponseStatus(HttpStatus.OK)
    @ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
    @RequestMapping(value = "slack", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Map<String, Object> isSlackAvailable() throws ServiceException
    {
        Map<String, Object> config = new HashMap<>();
        if (slackService.getWebhook() != null && slackService.isEnabledAndConnected(SLACK))
        {
            config.put("available", true);
        }
        else
        {
            config.put("available", false);
        }
        return config;
    }
}
