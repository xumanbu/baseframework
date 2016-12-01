package com.cxypub.baseframework.sdk.util;

/**
 * @Title: EntitySetting.java
 * @Package com.oaoera.oao_coreservice.advertising.service.imp
 * @Description: TODO
 * Copyright: Copyright (c) 2015 
 * Company:上海追月信息科技有限公司
 * 
 * @author oaoCoder-徐飞
 * @date 2015-7-31 下午3:08:07
 * @version V1.0
 */

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: EntitySetting
 * @Description: TODO
 * @author Comsys-徐飞
 * @date 2015-7-31 下午3:08:07
 *
 */

public abstract class ResultBuilderUtil {

	private static final String select = "select";

	private static final String from = "from";

	private static final String as = "as";

	private static final String point = ".";

	private static final String comma = ",";

	/**
	 * @Title: build
	 * @author:徐飞
	 * @Description: 这个方法主要是为了解决数据查询的obj数组不能自动注入到对象的问题，使用new 构造不就行了吗？大哥一个构造方法有10几个字段，你能记住顺序吗？？？
	 * 性能问题怎么办？当然做转换是会一定性能损耗，那用构造方法就没有性能损耗吗，忽略吧啊，解放右手吧！
	 * 
	 * before :select new AdvertisingOrder(a.id,a.advertisingId,a.buyerUserId) from AdvertisingOrder a
	 * after : select a.id,a.advertisingId,a.buyerUserId  from AdvertisingOrder a
	 *         select id,advertisingId,buyerUserId from AdvertisingOrder
	 *         
	 * 1、id,advertisingId,buyerUserId 这个顺序是可以任意的
	 * 2、假如 advertisingId 在 映射的类中字段是advertisingId2 那么可以通过as 指定成 a.advertisingId as advertisingId2 就可以了
	 * 3、多表关联查询同样可以，只要查询的as后的字段与类中字段一致就可以了。
	 * 
	 * @param clazz 需要转换成的对象，这个对象不一定是entity，可以是普通的类，PO,VO都可以
	 * @param jpql 查询的jqpl语句
	 * @param list 被转换的数据集
	 * @return 
	 * @throws
	 */
	public static <T> List<T> build(Class<T> clazz, String jpql, List<Object> list) {
		if (list != null && list.size() > 100) {
			throw new RuntimeException("被转换的数据集大于100，不建议采用这用装换方法了！！！");
		}
		String lowJpql = jpql.toLowerCase().trim();
		List<String> rsfields = new ArrayList<String>();
		if (lowJpql.startsWith(select) && lowJpql.contains(from)) {
			String[] alias = jpql.substring(6, lowJpql.indexOf(from)).split(comma);
			for (String temp : alias) {
				if (temp.contains(as)) {
					rsfields.add(temp.split(as)[1].trim());
				} else {
					if (temp.contains(point)) {
						rsfields.add(temp.split(point)[1].trim());
					} else {
						rsfields.add(temp.trim());
					}
				}
			}
		}
		List<T> resultList = new ArrayList<T>();
		for (Object obj : list) {
			if (obj instanceof Object[]) {
				Object[] rs = (Object[]) obj;
				if (rsfields.size() != rs.length) {
					throw new RuntimeException("需要查询的结果和字段不一致，不能继续解析了！！！");
				}
				T t = null;
				try {
					t = clazz.newInstance();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				for (int i = 0; i < rs.length; i++) {
					Class<?> superClazz = clazz.getSuperclass();

					Field f = null;
					try {
						f = clazz.getDeclaredField(rsfields.get(i));
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (NoSuchFieldException e) {
						try {
							f = superClazz.getDeclaredField(rsfields.get(i));
						} catch (SecurityException e1) {
							e1.printStackTrace();
						} catch (NoSuchFieldException e1) {
							continue;
						}
					}
					f.setAccessible(true);
					Class<?> fClazz = f.getType();
					Object val = rs[i];
					if (fClazz == String.class) { // 字符串
						val = val.toString();
					} else if (fClazz == Integer.class || fClazz == Integer.TYPE) { // 整形数字
						val = Integer.valueOf(val.toString());
					} else if (fClazz == Long.class || fClazz == Long.TYPE) { // 长整形数字
						val = Long.valueOf(val.toString());
					} else if (fClazz == Double.class || fClazz == Double.TYPE) { // 双精度小数
						val = Double.valueOf(val.toString());
					} else if (fClazz == Float.class || fClazz == Float.TYPE) {// 单精度小数
						val = Float.valueOf(val.toString());
					}
					try {
						f.set(t, val);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
				resultList.add(t);
			}
		}

		return resultList;
	}
}
