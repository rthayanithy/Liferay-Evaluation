/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.meera.jersey.services.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.meera.jersey.services.model.Person;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Person in entity cache.
 *
 * @author meera
 * @see Person
 * @generated
 */
public class PersonCacheModel implements CacheModel<Person>, Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{personId=");
		sb.append(personId);
		sb.append(", personName=");
		sb.append(personName);
		sb.append(", personAddress=");
		sb.append(personAddress);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Person toEntityModel() {
		PersonImpl personImpl = new PersonImpl();

		personImpl.setPersonId(personId);

		if (personName == null) {
			personImpl.setPersonName(StringPool.BLANK);
		}
		else {
			personImpl.setPersonName(personName);
		}

		if (personAddress == null) {
			personImpl.setPersonAddress(StringPool.BLANK);
		}
		else {
			personImpl.setPersonAddress(personAddress);
		}

		personImpl.resetOriginalValues();

		return personImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		personId = objectInput.readLong();
		personName = objectInput.readUTF();
		personAddress = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(personId);

		if (personName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(personName);
		}

		if (personAddress == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(personAddress);
		}
	}

	public long personId;
	public String personName;
	public String personAddress;
}