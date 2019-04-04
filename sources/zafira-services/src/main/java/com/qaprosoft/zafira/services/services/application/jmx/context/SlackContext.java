/*******************************************************************************
 * Copyright 2013-2018 QaProSoft (http://www.qaprosoft.com).
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
 ******************************************************************************/
package com.qaprosoft.zafira.services.services.application.jmx.context;

import in.ashwanthkumar.slack.webhook.Slack;

public class SlackContext extends AbstractContext
{

    private Slack slack;

    public SlackContext(String webHook, String author, String picPath, boolean enabled)
    {
        super(enabled);
        this.slack = new Slack(webHook);
        this.slack = this.slack.displayName(author);
        this.slack = this.slack.icon(picPath);
    }

    public Slack getSlack()
    {
        return slack;
    }

    public void setSlack(Slack slack)
    {
        this.slack = slack;
    }
}
