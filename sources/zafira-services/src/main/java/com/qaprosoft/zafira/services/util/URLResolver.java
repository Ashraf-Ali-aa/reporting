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
package com.qaprosoft.zafira.services.util;

import com.qaprosoft.zafira.dbaccess.utils.TenancyContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class URLResolver {

    @Value("${zafira.multitenant}")
    private boolean isMultitenant;

    @Value("${zafira.url}")
    private String serviceURL;

    @Value("${zafira.web.url}")
    private String webURL;

    /**
     * In case if multitenancy will resolve current tenancy id into the URL pattern: http://demo.qaprosoft.com/zafira.
     *
     * @return Zafira web URL
     */
    public String buildWebURL() {
        return isMultitenant ? String.format(webURL, TenancyContext.getTenantName()) : webURL;
    }

    public String getServiceURL() {
        return isMultitenant ? String.format(serviceURL, TenancyContext.getTenantName()) : webURL;
    }
}
