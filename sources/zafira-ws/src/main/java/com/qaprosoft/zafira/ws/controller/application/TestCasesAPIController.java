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

import com.qaprosoft.zafira.dbaccess.dao.mysql.application.search.SearchResult;
import com.qaprosoft.zafira.dbaccess.dao.mysql.application.search.TestCaseSearchCriteria;
import com.qaprosoft.zafira.models.db.Project;
import com.qaprosoft.zafira.models.db.TestCase;
import com.qaprosoft.zafira.models.db.TestMetric;
import com.qaprosoft.zafira.models.dto.TestCaseType;
import com.qaprosoft.zafira.services.exceptions.ServiceException;
import com.qaprosoft.zafira.services.services.application.ProjectService;
import com.qaprosoft.zafira.services.services.application.TestCaseService;
import com.qaprosoft.zafira.services.services.application.TestMetricService;
import com.qaprosoft.zafira.ws.controller.AbstractController;
import com.qaprosoft.zafira.ws.swagger.annotations.ResponseStatusDetails;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ArrayUtils;
import org.dozer.Mapper;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Api("Test cases API")
@RequestMapping(path = "api/tests/cases", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class TestCasesAPIController extends AbstractController {

    @Autowired
    private Mapper mapper;

    @Autowired
    private TestCaseService testCaseService;

    @Autowired
    private TestMetricService testMetricService;

    @Autowired
    private ProjectService projectService;

    @ResponseStatusDetails
    @ApiOperation(value = "Search test cases", nickname = "searchTestCases", httpMethod = "POST", response = SearchResult.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", paramType = "header")})
    @PostMapping("/search")
    public SearchResult<TestCase> searchTestCases(@Valid @RequestBody TestCaseSearchCriteria sc) throws ServiceException {
        return testCaseService.searchTestCases(sc);
    }

    @ResponseStatusDetails
    @ApiOperation(value = "Get test metrics by test case id", nickname = "getTestMetricsByTestCaseId", httpMethod = "GET", response = Map.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", paramType = "header")})
    @GetMapping("/{id}/metrics")
    public Map<String, List<TestMetric>> getTestMetricsByTestCaseId(@PathVariable("id") Long id) throws ServiceException {
        return testMetricService.getTestMetricsByTestCaseId(id);
    }

    @ResponseStatusDetails
    @ApiOperation(value = "Create test case", nickname = "createTestCase", httpMethod = "POST", response = TestCaseType.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", paramType = "header")})
    @PostMapping()
    public TestCaseType createTestCase(
            @RequestBody @Valid TestCaseType testCase,
            @RequestHeader(value = "Project", required = false) String projectName
    ) throws ServiceException, MappingException, ExecutionException {
        TestCase tc = mapper.map(testCase, TestCase.class);
        tc.setProject(projectService.getProjectByName(projectName));
        return mapper.map(testCaseService.createOrUpdateCase(tc), TestCaseType.class);
    }

    @ResponseStatusDetails
    @ApiOperation(value = "Create multiple test cases", nickname = "createTestCases", httpMethod = "POST", response = TestCaseType[].class)
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", paramType = "header")})
    @PostMapping("/batch")
    public TestCaseType[] createTestCases(@RequestBody @Valid TestCaseType[] tcs, @RequestHeader(value = "Project", required = false) String projectName) throws ServiceException, ExecutionException {
        if (!ArrayUtils.isEmpty(tcs)) {
            Project project = projectService.getProjectByName(projectName);
            TestCase[] testCases = new TestCase[tcs.length];
            for (int i = 0; i < tcs.length; i++) {
                testCases[i] = mapper.map(tcs[i], TestCase.class);
                testCases[i].setProject(project);
            }
            testCases = testCaseService.createOrUpdateCases(testCases);
            for (int i = 0; i < testCases.length; i++) {
                tcs[i] = mapper.map(testCases[i], TestCaseType.class);
            }
            return tcs;
        } else {
            return new TestCaseType[0];
        }
    }

}