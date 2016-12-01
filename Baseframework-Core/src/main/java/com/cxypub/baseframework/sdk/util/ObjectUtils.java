package com.cxypub.baseframework.sdk.util;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.log4j.Logger;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeanUtils;

/**
 * <b>类名</b>：ObjectUtil.java<br>
 * <p><b>标题</b>：品恩产品研发  </p>
 * <p><b>描述</b>：
 *           对象处理的工具类，包括拷贝，序列化等内容。
 * </p>
 * <p><b>版权声明</b>：Copyright (c) 2013</p>
 * <p><b>公司</b>：品恩科技 </p>
 * @author <font color='blue'>liucong</font> 
 * @version 1.0
 * @date  2013-4-11 上午11:13:01
 */
public class ObjectUtils {

	protected static final Logger log = Logger.getLogger(ObjectUtils.class);

	/**
	 * <b>方法名</b>：objectAsXml<br>
	 * <b>功能</b>：将object转化为xml串<br>
	 * 注意：依赖于getter和setter方法
	 * 注意：如果字符集转化过程中与系统默认的字符集不一致，会出现字符处理的错误。参见转化为字节的处理。
	 * @author <font color='blue'>Administrator</font>
	 * @date 2012-8-2 上午10:31:07
	 * @param o
	 * @return
	 * @throws IOException
	 * @see #object2Byte(Object, boolean)
	 * @see #object2Byte(Object)
	 */
	public static String objectAsXml(Object o) throws IOException {
		OutputStream os = new ByteArrayOutputStream(1024);
		XMLEncoder xml = new XMLEncoder(os);
		xml.writeObject(o);
		xml.flush();
		String result = os.toString();
		xml.close();
		os.close();
		return result;
	}

	/**
	 * <b>方法名</b>：objectAsXmlNoHeader<br>
	 * <b>功能</b>：将object转化为xml串,去头补尾<br>
	 * @author <font color='blue'>gugq</font> 
	 * @date  2014-1-17 上午10:24:43
	 * @param o
	 * @return
	 * @throws IOException 
	 */
	public static String objectAsXmlNoHeader(Object o) throws IOException {
		long startData = System.currentTimeMillis();
		long endData = System.currentTimeMillis();
		String temStr = null;
		temStr = objectAsXml(o);
		if (temStr == null) {
			return null;
		}
		temStr = temStr.substring(temStr.indexOf("<java")).concat("</java>");
		log.info("将对象转xml共耗时：" + (endData - startData) + ":毫秒");
		return temStr;

	}

	/**
	 * <b>方法名</b>：xmlAsObject<br>
	 * <b>功能</b>：将xml串转化为object<br>
	 * 注意：依赖于getter和setter方法
	 * 注意：如果字符集转化过程中与系统默认的字符集不一致，会出现字符处理的错误。参见转化为字节的处理。
	 * @author <font color='blue'>Administrator</font>
	 * @date 2012-8-2 上午10:31:23
	 * @return
	 * @see #byte2Object(byte[])
	 * @see #byte2Object(byte[], boolean)
	 */
	public static Object xmlAsObject(String xml) {
		XMLDecoder d = null;
		try {
			d = new XMLDecoder(new ByteArrayInputStream(xml.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Object result = d.readObject();
		d.close();
		return result;
	}

	/**
	 * <b>方法名</b>：getTarget<br>
	 * <b>功能</b>：获取代理的目标对象<br>
	 * 
	 * @author <font color='blue'>Administrator</font>
	 * @date 2012-8-9 上午10:30:26
	 * @param proxy
	 * @return
	 * @throws Exception
	 */
	public static Object getTarget(Object proxy) throws Exception {
		if (!AopUtils.isAopProxy(proxy)) {
			return proxy;// 不是代理对象，直接返回
		}
		if (AopUtils.isJdkDynamicProxy(proxy)) {
			return getJdkDynamicProxyTargetObject(proxy);
		} else if (AopUtils.isCglibProxy(proxy)) { // cglib
			return getCglibProxyTargetObject(proxy);
		} else {
			return proxy;
		}
	}

	static Object getCglibProxyTargetObject(Object proxy) throws Exception {
		Field h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
		h.setAccessible(true);
		Object dynamicAdvisedInterceptor = h.get(proxy);

		Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
		advised.setAccessible(true);

		Object target = ((AdvisedSupport) advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();

		return target;
	}

	static Object getJdkDynamicProxyTargetObject(Object proxy) throws Exception {
		Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
		h.setAccessible(true);
		AopProxy aopProxy = (AopProxy) h.get(proxy);

		Field advised = aopProxy.getClass().getDeclaredField("advised");
		advised.setAccessible(true);

		Object target = ((AdvisedSupport) advised.get(aopProxy)).getTargetSource().getTarget();

		return target;
	}

	/**
	 * <b>方法名</b>：copyProperties<br>
	 * <b>功能</b>：根据相同的property名字将orig中的内容拷贝到dest中<br>
	 * 
	 * @author <font color='blue'>Administrator</font>
	 * @date 2012-8-9 上午11:10:26
	 * @param dest
	 * @param orig
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static void copyProperties(Object dest, Object orig) throws IllegalAccessException, InvocationTargetException {
		// BeanUtils.copyProperties(dest, orig);
		BeanUtils.copyProperties(dest, orig);
	}

	/**
	 * <b>方法名</b>：copyObjectWithSerialization<br>
	 * <b>功能</b>：通过对象序列化、反序列化方式拷贝对象<br>
	 * 如果有父类，父类须实现${@link java.io.Serializable}接口，父类里的属性才可以被保留。
	 * @author <font color='blue'>liucong</font> 
	 * @date  2013-4-10 上午11:32:10
	 * @param o
	 * @return
	 */
	public static Object copyObjectWithSerialization(Object o) {
		return ObjectUtils.byte2Object((ObjectUtils.object2Byte(o)));
	}

	public static void main(String[] args) {
		// try {
		// System.out.println(objectAsXml(new Cform()));
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// try {
		// System.out.println(xmlAsObject(objectAsXml(new Cform())));
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		System.out.println("-----");
	}

	/**
	 * 
	 * <b>方法名</b>：object2Byte<br>
	 * <b>功能</b>：把对象转化成字节<br>
	 * 加入是否进行压缩的参数
	 * @author <font color='blue'>zohan</font>
	 * @date 2013-3-13 下午03:53:40
	 * @param value
	 * @param compressEnable
	 * @return
	 */
	public static byte[] object2Byte(Object value, boolean compressEnable) {
		byte[] val = object2Byte(value);
		if (compressEnable) {
			val = compressByte(val);
		}

		return val;
	}

	/**
	 * <b>方法名</b>：object2Byte<br>
	 * <b>功能</b>：把对象转化成字节<br>
	 * 如果有父类，父类须实现${@link java.io.Serializable}接口，字节才有父类里的属性。<br>
	 * transient关键字的属性不被序列化
	 * @author <font color='blue'>zohan</font> 
	 * @date  2013-3-13 下午04:06:29
	 * @param value
	 * @return
	 */
	public static byte[] object2Byte(Object value) {
		byte[] val = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			// CompactedObjectOutputStream out = new CompactedObjectOutputStream(bos);
			// out.writeObject(value);
			(new ObjectOutputStream(bos)).writeObject(value);
			// 写到文件的测试代码。
			// FileOutputStream os = new FileOutputStream(new File("d:\\cc.txt"));
			// os.write(bos.toByteArray());
			// os.flush();
			// os.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		val = bos.toByteArray();
		return val;
	}

	/**
	 * <b>方法名</b>：compressByte<br>
	 * <b>功能</b>：压缩数组<br>
	 * @author <font color='blue'>zohan</font> 
	 * @date  2013-3-13 下午04:05:44
	 * @param val
	 * @return
	 */
	public static byte[] compressByte(byte[] val) {
		ByteArrayOutputStream bos;
		bos = new ByteArrayOutputStream(val.length);
		GZIPOutputStream gos;
		try {
			gos = new GZIPOutputStream(bos);
			gos.write(val, 0, val.length);
			gos.finish();
			gos.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		val = bos.toByteArray();
		return val;
	}

	/**
	 * 
	 * <b>方法名</b>：byte2Object<br>
	 * <b>功能</b>：字节数组转化成对象<br>
	 * @author <font color='blue'>zohan</font> 
	 * @date  2013-3-13 下午04:02:29
	 * @param buf
	 * @param compressEnable
	 * @return
	 */
	public static <T> T byte2Object(byte[] buf, boolean compressEnable) {
		if (compressEnable) {
			buf = unCompressByte(buf);
		}
		T o = byte2Object(buf);
		return o;

	}

	/**
	 * <b>方法名</b>：byte2Object<br>
	 * <b>功能</b>：字节数组转化成对象<br>
	 * @author <font color='blue'>zohan</font> 
	 * @date  2013-3-13 下午04:09:41
	 * @param buf
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T byte2Object(byte[] buf) {
		ObjectInputStream ois = null;
		Object o = null;
		try {
			ByteArrayInputStream is = new ByteArrayInputStream(buf);
			// CompactedObjectInputStream in = new CompactedObjectInputStream(is);
			ois = new ObjectInputStream(is);
			o = ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return (T) o;
	}

	/**
	 * <b>方法名</b>：unCompressByte<br>
	 * <b>功能</b>：解压缩数组<br>
	 * @author <font color='blue'>zohan</font> 
	 * @date  2013-3-13 下午04:07:40
	 * @param buf
	 * @return
	 */
	public static byte[] unCompressByte(byte[] buf) {
		GZIPInputStream gzi = null;
		try {
			gzi = new GZIPInputStream(new ByteArrayInputStream(buf));
			ByteArrayOutputStream bos = new ByteArrayOutputStream(buf.length);

			int count;
			byte[] tmp = new byte[2048];
			while ((count = gzi.read(tmp)) != -1) {
				bos.write(tmp, 0, count);
			}

			buf = bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (gzi != null) {
				try {
					gzi.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return buf;
	}
}
