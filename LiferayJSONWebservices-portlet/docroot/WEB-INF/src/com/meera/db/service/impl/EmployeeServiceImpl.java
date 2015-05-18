/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.meera.db.service.impl;

import com.meera.db.model.Employee;
import com.meera.db.service.EmployeeLocalService;
import com.meera.db.service.EmployeeLocalServiceUtil;
import com.meera.db.service.base.EmployeeServiceBaseImpl;

/**
 * The implementation of the employee remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.meera.db.service.EmployeeService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author E5410
 * @see com.meera.db.service.base.EmployeeServiceBaseImpl
 * @see com.meera.db.service.EmployeeServiceUtil
 */
public class EmployeeServiceImpl extends EmployeeServiceBaseImpl {
	public  Employee getEmployee(
			long emplyeeId)
			throws com.liferay.portal.kernel.exception.PortalException,
				com.liferay.portal.kernel.exception.SystemException {
			return EmployeeLocalServiceUtil.getEmployee(emplyeeId);
		}
}