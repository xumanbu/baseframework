package com.cxypub.baseframework.sdk.dao.util;

/**
 * <p> Description: Intelligent Camp Monitoring System </p>
 * <p> Copyright: Copyright (c) 2014 </p>
 * <p> Company: www.pingtech.com.cn </p>
 * @author xufei
 * @version 4.0
 * @date  2014-10-22 ����10:36:23
 */
import java.io.Serializable;

import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.UUIDHexGenerator;

public class UIDGenerator extends UUIDHexGenerator {
	private static UIDGenerator uid = new UIDGenerator();

	/* (non-Javadoc)
	 * @see org.hibernate.id.UUIDHexGenerator#generate(org.hibernate.engine.SessionImplementor, java.lang.Object)
	 */

	@Override
	public Serializable generate(SessionImplementor session, Object object) {
		return new StringBuilder(16).append(formatShort(getAppId())).append(format(getHiTime())).append(format(getLoTime())).append(formatShort(getCount())).toString();
	}

	/**
	 * @description  ���16λ��ID
	 * @date 2010-12-2
	 * @author liuls
	 * @return l6λ��ID��
	 */
	public String createID() {
		return new StringBuilder(16).append(formatShort(getAppId())).append(format(getHiTime())).append(format(getLoTime())).append(formatShort(getCount())).toString();
	}

	/**
	 * @description ���UIDGeneratorʵ��
	 * @date 2010-12-2
	 * @author liuls
	 * @return UIDGenerator
	 */
	public static UIDGenerator getInstance() {
		return uid;
	}

	/**
	 * @description ����������ʵ�ִ�System Properties, Spring ApplicationContext�ȵط����ֵ.
	 * @date Jul 19, 2010
	 * @author liuls
	 * @return 0
	 */
	protected short getAppId() {
		return 0;
	}

	/**
	 * @description ��ʽ�����ֵΪ255����ֵ�ɳ���Ϊ2���ַ�.
	 * @date Jul 19, 2010
	 * @author liuls
	 * @param value ��Ҫת����short ֵ
	 * @return ת������ַ�
	 */
	protected String formatShort(short value) {
		String formatted = Integer.toHexString(value);
		if (formatted.length() < 2) {
			formatted = "0" + formatted;
		}
		return formatted;
	}
}
