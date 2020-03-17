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
package com.zebrunner.reporting.persistence.dao.mysql.application;

import com.zebrunner.reporting.domain.db.Setting;

import java.util.List;

public interface SettingsMapper {
    void createSetting(Setting setting);

    Setting getSettingById(long id);

    Setting getSettingByName(String name);

    //List<Setting> getSettingsByTool(Tool tool);

    //List<Setting> getSettingsByEncrypted(boolean isEncrypted);

    List<Setting> getAllSettings();

    //List<Setting> getSettingsByIntegration(@Param("isIntegrationTool") boolean isIntegrationTool);

    //List<Tool> getTools();

    void updateSetting(Setting setting);

    void updateIntegrationSetting(Setting setting);

    void deleteSetting(Setting setting);

    void deleteSettingById(long id);

    String getPostgresVersion();
}
