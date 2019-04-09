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
package com.qaprosoft.zafira.services.services.application.integration.context;

import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.ldap.authentication.BindAuthenticator;
import org.springframework.security.ldap.search.FilterBasedLdapUserSearch;

public class LDAPContext extends AbstractContext {

    private LdapContextSource ldapContextSource;
    private BindAuthenticator bindAuthenticator;
    private FilterBasedLdapUserSearch filterBasedLdapUserSearch;

    public LDAPContext(String dn, String searchFilter, String url, String managerUser, String managerPassword, boolean enabled) {
        super(enabled);
        this.ldapContextSource = new LdapContextSource();
        this.ldapContextSource.setUrl(url);
        this.ldapContextSource.setUserDn(managerUser);
        this.ldapContextSource.setPassword(managerPassword);
        this.ldapContextSource.afterPropertiesSet();
        this.filterBasedLdapUserSearch = new FilterBasedLdapUserSearch(dn, searchFilter, ldapContextSource);
        this.filterBasedLdapUserSearch.setSearchSubtree(true);
        this.bindAuthenticator = new BindAuthenticator(this.ldapContextSource);
        this.bindAuthenticator.setUserSearch(this.filterBasedLdapUserSearch);
    }

    public LdapContextSource getLdapContextSource() {
        return ldapContextSource;
    }

    public void setLdapContextSource(LdapContextSource ldapContextSource) {
        this.ldapContextSource = ldapContextSource;
    }

    public BindAuthenticator getBindAuthenticator() {
        return bindAuthenticator;
    }

    public void setBindAuthenticator(BindAuthenticator bindAuthenticator) {
        this.bindAuthenticator = bindAuthenticator;
    }

    public FilterBasedLdapUserSearch getFilterBasedLdapUserSearch() {
        return filterBasedLdapUserSearch;
    }

    public void setFilterBasedLdapUserSearch(FilterBasedLdapUserSearch filterBasedLdapUserSearch) {
        this.filterBasedLdapUserSearch = filterBasedLdapUserSearch;
    }
}
