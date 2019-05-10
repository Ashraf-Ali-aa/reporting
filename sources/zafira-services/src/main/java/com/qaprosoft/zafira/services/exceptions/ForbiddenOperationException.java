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
package com.qaprosoft.zafira.services.exceptions;

public class ForbiddenOperationException extends ServiceException
{
	private static final long serialVersionUID = -1840720518398070678L;

	private boolean showMessage;

	public ForbiddenOperationException()
	{
		super();
	}

	public ForbiddenOperationException(String message)
	{
		super(message);
	}

	public ForbiddenOperationException(String message, boolean showMessage)
	{
		super(message);
		this.showMessage = showMessage;
	}

	public ForbiddenOperationException(Throwable cause)
	{
		super(cause);
	}

	public ForbiddenOperationException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ForbiddenOperationException(String message, Throwable cause, boolean writableStackTrace) {
		super(message, cause, writableStackTrace);
	}

	public boolean isShowMessage() {
		return showMessage;
	}
}