/*******************************************************************************
 * Copyright 2013-2019 Qaprosoft (http://www.qaprosoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.qaprosoft.zafira.config;

import org.apache.commons.lang3.StringUtils;

/**
 * Simple configuration bean that represent CI configuration properties used to initialize Zafira test runs.
 *
 * @author akhursevich
 */
public class CIConfig {

    private String ciRunId;
    private String ciUrl;
    private Integer ciBuild;
    private BuildCase ciBuildCause;
    private String ciParentUrl;
    private Integer ciParentBuild;

    private String gitBranch;
    private String gitCommit;
    private String gitUrl;

    public enum BuildCase {
        UPSTREAMTRIGGER,
        TIMERTRIGGER,
        MANUALTRIGGER,
        SCMTRIGGER
    }

    public String getCiRunId() {
        return ciRunId;
    }

    public void setCiRunId(String ciRunId) {
        this.ciRunId = ciRunId;
    }

    public String getCiUrl() {
        return ciUrl;
    }

    public void setCiUrl(String ciUrl) {
        this.ciUrl = StringUtils.removeEnd(ciUrl, "/");
    }

    public Integer getCiBuild() {
        return ciBuild;
    }

    public void setCiBuild(String ciBuild) {
        this.ciBuild = StringUtils.isEmpty(ciBuild) ? 0 : Integer.valueOf(ciBuild);
    }

    public BuildCase getCiBuildCause() {
        return ciBuildCause;
    }

    public void setCiBuildCause(BuildCase ciBuildCause) {
        this.ciBuildCause = ciBuildCause;
    }

    public void setCiBuildCause(String ciBuildCause) {
        if (ciBuildCause != null) {
            // HotFix for 'BuildCase.UPSTREAMTRIGGER,UPSTREAMTRIGGER,UPSTREAMTRIGGER'
            this.ciBuildCause = BuildCase.valueOf(ciBuildCause.toUpperCase().split(",")[0]);
        }
    }

    public String getCiParentUrl() {
        return ciParentUrl;
    }

    public void setCiParentUrl(String ciParentUrl) {
        this.ciParentUrl = StringUtils.removeEnd(ciParentUrl, "/");
    }

    public Integer getCiParentBuild() {
        return ciParentBuild;
    }

    public void setCiParentBuild(String ciParentBuild) {
        this.ciParentBuild = StringUtils.isEmpty(ciParentBuild) ? 0 : Integer.valueOf(ciParentBuild);
    }

    public String getGitBranch() {
        return gitBranch;
    }

    public void setGitBranch(String gitBranch) {
        this.gitBranch = gitBranch;
    }

    public String getGitCommit() {
        return gitCommit;
    }

    public void setGitCommit(String gitCommit) {
        this.gitCommit = gitCommit;
    }

    public String getGitUrl() {
        return gitUrl;
    }

    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
    }
}