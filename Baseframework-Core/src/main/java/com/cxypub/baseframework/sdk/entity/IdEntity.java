package com.cxypub.baseframework.sdk.entity;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.MappedSuperclass;

import edu.emory.mathcs.backport.java.util.Arrays;

/**
 * @ClassName: IdEntity
 * @Description: 仅包含主键列的实体基类
 * @author xufei
 * @date 2015-4-14 上午11:20:40
 *
 */
@MappedSuperclass
public abstract class IdEntity implements Serializable {

	private static final long serialVersionUID = 1127463836304741045L;

	/** id */
	protected String id;

	/**
	 * setter method
	 * @param id the id to set
	 */

	public void setId(String id) {
		if ("".equals(id)) {
			this.id = null;
		} else {
			this.id = id;
		}
	}

	/**
	 * Returns values of public fields of this Info object as a String.
	 * @return String
	 */
	@Override
	public String toString() {
		return _toMap().toString();
	}

	/**
	 * Returns values of all declared fields of this Info object as a Map.
	 * Subclasses can override this to exclude some fields, by applying
	 * .keySet().retainAll(list) on super.toMap(). To be used by toString(),
	 * equals() and hashCode() only
	 * 
	 * @return Map
	 */
	private final Map<String, Object> _toMap() {
		return _toMap(this);
	}

	/**
	 * Returns values of public fields of an Object as a Map.
	 * 
	 * @param source
	 *            The object to inspect
	 * @return Map
	 */
	@SuppressWarnings("unchecked")
	private static final Map<String, Object> _toMap(Object source) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		try {
			List<Field> list = Arrays.asList(source.getClass().getDeclaredFields());
			list.addAll(Arrays.asList(source.getClass().getSuperclass().getDeclaredFields()));
			for (Field field : list) {
				if (Modifier.isTransient(field.getModifiers())) {
					continue;
				}
				if (Modifier.isPrivate(field.getModifiers())) {
					field.setAccessible(true);
				}
				if (Modifier.isProtected(field.getModifiers())) {
					field.setAccessible(true);
				}

				map.put(field.getName(), field.get(source));
			}
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		}

		return map;
	}

	/**
	 * Explains an enum
	 * 
	 * @param value
	 *            the value to decode, appropriately wrapped in an Object
	 * @param clazz
	 *            the class with static final values
	 * @return String
	 */
	public static String explainEnum(Object value, Class<?> clazz) {
		List<String> l = decodeEnum(value, clazz);
		if (l.isEmpty()) {
			return String.valueOf(value);
		}
		return value + " " + l;
	}

	/**
	 * Explains an int enum
	 * 
	 * @param value
	 *            the int value to decode
	 * @param clazz
	 *            the class with static final values
	 * @return String
	 */
	public static String explainEnum(int value, Class<?> clazz) {
		return explainEnum(Integer.valueOf(value), clazz);
	}

	/**
	 * Decodes an enum
	 * 
	 * @param value
	 *            the value to decode, appropriately wrapped in an Object
	 * @param clazz
	 *            the class with static final values
	 * @return List of possible matches
	 */
	public static List<String> decodeEnum(Object value, Class<?> clazz) {
		List<String> l = new ArrayList<String>();
		Field[] f = clazz.getFields();
		try {
			for (int i = 0; i < f.length; ++i) {
				if (Modifier.isFinal(f[i].getModifiers()) && value.equals(f[i].get(null))) {
					l.add(f[i].getName());
				}
			}
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		}
		return l;
	}

	/**
	 * <p>Title: hashCode</p>
	 * <p>Description: </p>
	 * @return
	 * @see java.lang.Object#hashCode()
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	 * <p>Title: equals</p>
	 * <p>Description: </p>
	 * @param obj
	 * @return
	 * @see java.lang.Object#equals(java.lang.Object)
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		IdEntity other = (IdEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
