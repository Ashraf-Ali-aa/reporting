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
package com.qaprosoft.zafira.services.services.jmx.google.auth;

import com.google.api.services.sheets.v4.Sheets;
import com.qaprosoft.zafira.services.services.jmx.google.AbstractGoogleService;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GoogleSheetsAuthService extends AbstractGoogleService
{

	public static Sheets getService() throws IOException
	{
		return new Sheets.Builder(getHttpTransport(), getJsonFactory(), authorize())
				.setApplicationName(getApplicationName())
				.build();
	}
}
