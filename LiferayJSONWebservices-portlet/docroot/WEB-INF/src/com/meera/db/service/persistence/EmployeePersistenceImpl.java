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

package com.meera.db.service.persistence;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.meera.db.NoSuchEmployeeException;
import com.meera.db.model.Employee;
import com.meera.db.model.impl.EmployeeImpl;
import com.meera.db.model.impl.EmployeeModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the employee service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author E5410
 * @see EmployeePersistence
 * @see EmployeeUtil
 * @generated
 */
public class EmployeePersistenceImpl extends BasePersistenceImpl<Employee>
	implements EmployeePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link EmployeeUtil} to access the employee persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = EmployeeImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeModelImpl.FINDER_CACHE_ENABLED, EmployeeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeModelImpl.FINDER_CACHE_ENABLED, EmployeeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_EMPLYEENAME = new FinderPath(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeModelImpl.FINDER_CACHE_ENABLED, EmployeeImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByemplyeeName",
			new String[] { String.class.getName() },
			EmployeeModelImpl.EMPLYEENAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_EMPLYEENAME = new FinderPath(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByemplyeeName",
			new String[] { String.class.getName() });

	/**
	 * Returns the employee where emplyeeName = &#63; or throws a {@link com.meera.db.NoSuchEmployeeException} if it could not be found.
	 *
	 * @param emplyeeName the emplyee name
	 * @return the matching employee
	 * @throws com.meera.db.NoSuchEmployeeException if a matching employee could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Employee findByemplyeeName(String emplyeeName)
		throws NoSuchEmployeeException, SystemException {
		Employee employee = fetchByemplyeeName(emplyeeName);

		if (employee == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("emplyeeName=");
			msg.append(emplyeeName);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchEmployeeException(msg.toString());
		}

		return employee;
	}

	/**
	 * Returns the employee where emplyeeName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param emplyeeName the emplyee name
	 * @return the matching employee, or <code>null</code> if a matching employee could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Employee fetchByemplyeeName(String emplyeeName)
		throws SystemException {
		return fetchByemplyeeName(emplyeeName, true);
	}

	/**
	 * Returns the employee where emplyeeName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param emplyeeName the emplyee name
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching employee, or <code>null</code> if a matching employee could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Employee fetchByemplyeeName(String emplyeeName,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { emplyeeName };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_EMPLYEENAME,
					finderArgs, this);
		}

		if (result instanceof Employee) {
			Employee employee = (Employee)result;

			if (!Validator.equals(emplyeeName, employee.getEmplyeeName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_EMPLOYEE_WHERE);

			boolean bindEmplyeeName = false;

			if (emplyeeName == null) {
				query.append(_FINDER_COLUMN_EMPLYEENAME_EMPLYEENAME_1);
			}
			else if (emplyeeName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_EMPLYEENAME_EMPLYEENAME_3);
			}
			else {
				bindEmplyeeName = true;

				query.append(_FINDER_COLUMN_EMPLYEENAME_EMPLYEENAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindEmplyeeName) {
					qPos.add(emplyeeName);
				}

				List<Employee> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_EMPLYEENAME,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"EmployeePersistenceImpl.fetchByemplyeeName(String, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					Employee employee = list.get(0);

					result = employee;

					cacheResult(employee);

					if ((employee.getEmplyeeName() == null) ||
							!employee.getEmplyeeName().equals(emplyeeName)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_EMPLYEENAME,
							finderArgs, employee);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_EMPLYEENAME,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (Employee)result;
		}
	}

	/**
	 * Removes the employee where emplyeeName = &#63; from the database.
	 *
	 * @param emplyeeName the emplyee name
	 * @return the employee that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Employee removeByemplyeeName(String emplyeeName)
		throws NoSuchEmployeeException, SystemException {
		Employee employee = findByemplyeeName(emplyeeName);

		return remove(employee);
	}

	/**
	 * Returns the number of employees where emplyeeName = &#63;.
	 *
	 * @param emplyeeName the emplyee name
	 * @return the number of matching employees
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByemplyeeName(String emplyeeName) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_EMPLYEENAME;

		Object[] finderArgs = new Object[] { emplyeeName };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_EMPLOYEE_WHERE);

			boolean bindEmplyeeName = false;

			if (emplyeeName == null) {
				query.append(_FINDER_COLUMN_EMPLYEENAME_EMPLYEENAME_1);
			}
			else if (emplyeeName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_EMPLYEENAME_EMPLYEENAME_3);
			}
			else {
				bindEmplyeeName = true;

				query.append(_FINDER_COLUMN_EMPLYEENAME_EMPLYEENAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindEmplyeeName) {
					qPos.add(emplyeeName);
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_EMPLYEENAME_EMPLYEENAME_1 = "employee.emplyeeName IS NULL";
	private static final String _FINDER_COLUMN_EMPLYEENAME_EMPLYEENAME_2 = "employee.emplyeeName = ?";
	private static final String _FINDER_COLUMN_EMPLYEENAME_EMPLYEENAME_3 = "(employee.emplyeeName IS NULL OR employee.emplyeeName = '')";

	public EmployeePersistenceImpl() {
		setModelClass(Employee.class);
	}

	/**
	 * Caches the employee in the entity cache if it is enabled.
	 *
	 * @param employee the employee
	 */
	@Override
	public void cacheResult(Employee employee) {
		EntityCacheUtil.putResult(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeImpl.class, employee.getPrimaryKey(), employee);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_EMPLYEENAME,
			new Object[] { employee.getEmplyeeName() }, employee);

		employee.resetOriginalValues();
	}

	/**
	 * Caches the employees in the entity cache if it is enabled.
	 *
	 * @param employees the employees
	 */
	@Override
	public void cacheResult(List<Employee> employees) {
		for (Employee employee : employees) {
			if (EntityCacheUtil.getResult(
						EmployeeModelImpl.ENTITY_CACHE_ENABLED,
						EmployeeImpl.class, employee.getPrimaryKey()) == null) {
				cacheResult(employee);
			}
			else {
				employee.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all employees.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(EmployeeImpl.class.getName());
		}

		EntityCacheUtil.clearCache(EmployeeImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the employee.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Employee employee) {
		EntityCacheUtil.removeResult(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeImpl.class, employee.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(employee);
	}

	@Override
	public void clearCache(List<Employee> employees) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Employee employee : employees) {
			EntityCacheUtil.removeResult(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
				EmployeeImpl.class, employee.getPrimaryKey());

			clearUniqueFindersCache(employee);
		}
	}

	protected void cacheUniqueFindersCache(Employee employee) {
		if (employee.isNew()) {
			Object[] args = new Object[] { employee.getEmplyeeName() };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_EMPLYEENAME, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_EMPLYEENAME, args,
				employee);
		}
		else {
			EmployeeModelImpl employeeModelImpl = (EmployeeModelImpl)employee;

			if ((employeeModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_EMPLYEENAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { employee.getEmplyeeName() };

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_EMPLYEENAME,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_EMPLYEENAME,
					args, employee);
			}
		}
	}

	protected void clearUniqueFindersCache(Employee employee) {
		EmployeeModelImpl employeeModelImpl = (EmployeeModelImpl)employee;

		Object[] args = new Object[] { employee.getEmplyeeName() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_EMPLYEENAME, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_EMPLYEENAME, args);

		if ((employeeModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_EMPLYEENAME.getColumnBitmask()) != 0) {
			args = new Object[] { employeeModelImpl.getOriginalEmplyeeName() };

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_EMPLYEENAME, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_EMPLYEENAME, args);
		}
	}

	/**
	 * Creates a new employee with the primary key. Does not add the employee to the database.
	 *
	 * @param emplyeeId the primary key for the new employee
	 * @return the new employee
	 */
	@Override
	public Employee create(long emplyeeId) {
		Employee employee = new EmployeeImpl();

		employee.setNew(true);
		employee.setPrimaryKey(emplyeeId);

		return employee;
	}

	/**
	 * Removes the employee with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param emplyeeId the primary key of the employee
	 * @return the employee that was removed
	 * @throws com.meera.db.NoSuchEmployeeException if a employee with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Employee remove(long emplyeeId)
		throws NoSuchEmployeeException, SystemException {
		return remove((Serializable)emplyeeId);
	}

	/**
	 * Removes the employee with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the employee
	 * @return the employee that was removed
	 * @throws com.meera.db.NoSuchEmployeeException if a employee with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Employee remove(Serializable primaryKey)
		throws NoSuchEmployeeException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Employee employee = (Employee)session.get(EmployeeImpl.class,
					primaryKey);

			if (employee == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEmployeeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(employee);
		}
		catch (NoSuchEmployeeException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Employee removeImpl(Employee employee) throws SystemException {
		employee = toUnwrappedModel(employee);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(employee)) {
				employee = (Employee)session.get(EmployeeImpl.class,
						employee.getPrimaryKeyObj());
			}

			if (employee != null) {
				session.delete(employee);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (employee != null) {
			clearCache(employee);
		}

		return employee;
	}

	@Override
	public Employee updateImpl(com.meera.db.model.Employee employee)
		throws SystemException {
		employee = toUnwrappedModel(employee);

		boolean isNew = employee.isNew();

		Session session = null;

		try {
			session = openSession();

			if (employee.isNew()) {
				session.save(employee);

				employee.setNew(false);
			}
			else {
				session.merge(employee);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !EmployeeModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		EntityCacheUtil.putResult(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeImpl.class, employee.getPrimaryKey(), employee);

		clearUniqueFindersCache(employee);
		cacheUniqueFindersCache(employee);

		return employee;
	}

	protected Employee toUnwrappedModel(Employee employee) {
		if (employee instanceof EmployeeImpl) {
			return employee;
		}

		EmployeeImpl employeeImpl = new EmployeeImpl();

		employeeImpl.setNew(employee.isNew());
		employeeImpl.setPrimaryKey(employee.getPrimaryKey());

		employeeImpl.setEmplyeeId(employee.getEmplyeeId());
		employeeImpl.setEmplyeeName(employee.getEmplyeeName());
		employeeImpl.setEmployeeDesignation(employee.getEmployeeDesignation());

		return employeeImpl;
	}

	/**
	 * Returns the employee with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the employee
	 * @return the employee
	 * @throws com.meera.db.NoSuchEmployeeException if a employee with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Employee findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEmployeeException, SystemException {
		Employee employee = fetchByPrimaryKey(primaryKey);

		if (employee == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEmployeeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return employee;
	}

	/**
	 * Returns the employee with the primary key or throws a {@link com.meera.db.NoSuchEmployeeException} if it could not be found.
	 *
	 * @param emplyeeId the primary key of the employee
	 * @return the employee
	 * @throws com.meera.db.NoSuchEmployeeException if a employee with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Employee findByPrimaryKey(long emplyeeId)
		throws NoSuchEmployeeException, SystemException {
		return findByPrimaryKey((Serializable)emplyeeId);
	}

	/**
	 * Returns the employee with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the employee
	 * @return the employee, or <code>null</code> if a employee with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Employee fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		Employee employee = (Employee)EntityCacheUtil.getResult(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
				EmployeeImpl.class, primaryKey);

		if (employee == _nullEmployee) {
			return null;
		}

		if (employee == null) {
			Session session = null;

			try {
				session = openSession();

				employee = (Employee)session.get(EmployeeImpl.class, primaryKey);

				if (employee != null) {
					cacheResult(employee);
				}
				else {
					EntityCacheUtil.putResult(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
						EmployeeImpl.class, primaryKey, _nullEmployee);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
					EmployeeImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return employee;
	}

	/**
	 * Returns the employee with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param emplyeeId the primary key of the employee
	 * @return the employee, or <code>null</code> if a employee with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Employee fetchByPrimaryKey(long emplyeeId) throws SystemException {
		return fetchByPrimaryKey((Serializable)emplyeeId);
	}

	/**
	 * Returns all the employees.
	 *
	 * @return the employees
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Employee> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the employees.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.meera.db.model.impl.EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of employees
	 * @param end the upper bound of the range of employees (not inclusive)
	 * @return the range of employees
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Employee> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the employees.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.meera.db.model.impl.EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of employees
	 * @param end the upper bound of the range of employees (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of employees
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Employee> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<Employee> list = (List<Employee>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_EMPLOYEE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_EMPLOYEE;

				if (pagination) {
					sql = sql.concat(EmployeeModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Employee>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Employee>(list);
				}
				else {
					list = (List<Employee>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the employees from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (Employee employee : findAll()) {
			remove(employee);
		}
	}

	/**
	 * Returns the number of employees.
	 *
	 * @return the number of employees
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_EMPLOYEE);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the employee persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.meera.db.model.Employee")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Employee>> listenersList = new ArrayList<ModelListener<Employee>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Employee>)InstanceFactory.newInstance(
							getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(EmployeeImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_EMPLOYEE = "SELECT employee FROM Employee employee";
	private static final String _SQL_SELECT_EMPLOYEE_WHERE = "SELECT employee FROM Employee employee WHERE ";
	private static final String _SQL_COUNT_EMPLOYEE = "SELECT COUNT(employee) FROM Employee employee";
	private static final String _SQL_COUNT_EMPLOYEE_WHERE = "SELECT COUNT(employee) FROM Employee employee WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "employee.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Employee exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Employee exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(EmployeePersistenceImpl.class);
	private static Employee _nullEmployee = new EmployeeImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Employee> toCacheModel() {
				return _nullEmployeeCacheModel;
			}
		};

	private static CacheModel<Employee> _nullEmployeeCacheModel = new CacheModel<Employee>() {
			@Override
			public Employee toEntityModel() {
				return _nullEmployee;
			}
		};
}