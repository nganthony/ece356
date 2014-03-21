package com.ece356.dao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public abstract class BaseEntity {

	public void map(ResultSet rs) throws UnexpectedInputException,
			NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, SQLException,
			InstantiationException {
		Class<? extends BaseEntity> cls = getClass();
		Field[] fields = cls.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			if (Modifier.isTransient(fields[i].getModifiers())) {
				continue;
			} else {
				String name = fields[i].getName();
				String dbName = name.replaceAll("(.)([A-Z])", "$1_$2")
						.toLowerCase();
				name = Character.toUpperCase(name.charAt(0))
						+ name.substring(1);
				String fieldType = fields[i].getType().toString().toLowerCase();
				if (fieldType.contains("int")) {
					Method method = cls.getDeclaredMethod("set" + name,
							new Class[] { Integer.TYPE });
					method.invoke(this, rs.getInt(dbName));
				} else if (fieldType.endsWith("double")) {
					Method method = cls.getDeclaredMethod("set" + name,
							new Class[] { Double.TYPE });
					method.invoke(this, rs.getDouble(dbName));
				} else if (fieldType.endsWith("long")) {
					Method method = cls.getDeclaredMethod("set" + name,
							new Class[] { Long.TYPE });
					method.invoke(this, rs.getLong(dbName));
				} else if (fieldType.endsWith("boolean")) {
					Method method = cls.getDeclaredMethod("set" + name,
							new Class[] { Boolean.TYPE });
					method.invoke(this, rs.getBoolean(dbName));
				} else if (fieldType.endsWith("string")) {
					Method method = cls.getDeclaredMethod("set" + name,
							new Class[] { String.class });
					method.invoke(this, rs.getString(dbName));
				} else if (fieldType.endsWith("timestamp")) {
					Method method = cls.getDeclaredMethod("set" + name,
							new Class[] { Timestamp.class });
					method.invoke(this, rs.getInt(dbName));
				} else {
					throw new UnexpectedInputException(
							"The generic mapper does not deal with: "
									+ fieldType + " add it to BaseDao");
				}

			}
		}
	}
}

class UnexpectedInputException extends Exception {

	private static final long serialVersionUID = -6687341277840265375L;

	public UnexpectedInputException(String message) {
		super(message);
	}

}