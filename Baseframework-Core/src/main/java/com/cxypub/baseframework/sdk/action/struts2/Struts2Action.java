package com.cxypub.baseframework.sdk.action.struts2;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.cxypub.baseframework.sdk.action.IBaseAction;
import com.cxypub.baseframework.sdk.action.IResultRender;
import com.cxypub.baseframework.sdk.container.StrutsContextUtil;
import com.cxypub.baseframework.sdk.json.DefaultJsonConfig;
import com.cxypub.baseframework.sdk.search.Order;
import com.cxypub.baseframework.sdk.search.Page;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

/**
 * @ClassName: GenericAction
 * @Description: TODO
 * @author Comsys-徐飞
 * @date 2015年7月27日 下午1:37:40
 *
 */
public class Struts2Action extends ActionSupport implements IResultRender, IBaseAction, Preparable {

	private static final long serialVersionUID = -6647858505252235511L;

	protected final Logger log = Logger.getLogger(this.getClass());

	protected static final String ENCODING_PREFIX = "encoding";

	protected static final String NOCACHE_PREFIX = "no-cache";

	protected static final String ENCODING_DEFAULT = "utf-8";

	protected static final boolean NOCACHE_DEFAULT = true;

	protected static final String LIST = "list";

	protected static final String ERROR = "error";

	protected static final String INPUT = "input";

	/**
	 * 查看页面的命名，默认值为view
	 */
	protected static final String VIEW = "view";

	// 默认json格式设置
	protected DefaultJsonConfig defaultJsonConfig = new DefaultJsonConfig();

	/**
	 * 通用分页对象，页数大小在systemConfig.xml中定义，如果未定义默认15条。
	 */
	protected Page<? extends Object> page;

	protected ArrayList<Order> pageOrder = new ArrayList<Order>();

	/**
	 * <b>方法名</b>：afterProcess<br>
	 * <b>功能</b>：取得类的名称。<br>
	 * @return 类的名称（带全路径）。
	 */
	public String getActionName() {
		return this.getClass().getName();
	}

	public ArrayList<Order> getPageOrder() {
		return pageOrder;
	}

	public void setPageOrder(ArrayList<Order> pageOrder) {
		this.pageOrder = pageOrder;
	}

	/**
	 * @功能说明：返回排序的sql或hql
	 * @author xufei
	 * @date  2014-11-2 下午3:14:33
	 * @return
	 */
	public String getOrderSHql() {
		if (pageOrder == null) {
			return "";
		}
		StringBuilder shql = new StringBuilder();
		for (Order order : pageOrder) {
			shql.append(order.toSHql());
		}
		return shql.toString();
	}

	public Page<? extends Object> getPage() {
		return page;
	}

	public void setPage(Page<? extends Object> page) {
		this.page = page;
	}

	/**
	 * 得到servletContext
	 */
	public ServletContext getServletContext() {
		return ServletActionContext.getServletContext();
	}

	/**
	 * @description 得到HttpRequest
	 * @date 2010-7-12
	 * @author liuhh
	 * @return 获得的HttpRequest
	 */
	public HttpServletRequest getRequest() {

		return ServletActionContext.getRequest();
	}

	/**
	 * @description 将值放到request范围
	 * @date 2010-10-22
	 * @author chenl
	 * @param key
	 *            存入到request的key名称
	 * @param value
	 *            存入到request的value
	 */
	public void setIntoRequest(String key, Object value) {
		this.getRequest().setAttribute(key, value);
	}

	/**
	 * @description 将值从request范围内取出
	 * @date 2011-4-1
	 * @author chenl
	 * @param key
	 *            request key
	 * @return request里的值
	 */
	public String getParameter(String key) {
		return this.getRequest().getParameter(key);
	}

	/**
	 * @description 从request中得到数组
	 * @date 2010-10-22
	 * @author liuhh request key
	 * @return 所有的key所对应的value数组
	 */
	public String[] getParameterValues(String key) {
		return this.getRequest().getParameterValues(key);
	}

	/**
	 * @description 将值放到Session范围
	 * @date 2010-10-22
	 * @author chenl
	 * @param key
	 *            session key
	 * @param value
	 *            要存到session的值
	 */
	public void setIntoSession(String key, Object value) {
		this.getSession().setAttribute(key, value);
	}

	/**
	 * @description 将值放从Session范围取出
	 * @date 2010-10-22
	 * @author chenl
	 * @param key
	 *            session - key
	 * @return 得到key所对应的值
	 */
	public Object getFromSession(String key) {
		return this.getSession().getAttribute(key);
	}

	/**
	 * @description 得到HttpSession
	 * @date 2010-7-12
	 * @author liuhh
	 * @return 获得的HttpSession
	 */
	public HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}

	/**
	 * @description 得到HttpResponse
	 * @date 2010-7-12
	 * @author liuhh
	 * @return 获得的HttpResponse
	 */
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * @description 得到上下文路径，例如：/ceiba
	 * @date 2010-7-12
	 * @author liuhh
	 * @return 上下文路径
	 */
	public String getContext() {
		return this.getRequest().getContextPath();
	}

	/**
	 * @description 得到服务全路径，例如：http://127.0.0.1:8080/portal
	 * @date 2010-7-12
	 * @author liuhh
	 * @return 服务全路径
	 */
	public String getBasePath() {
		String basePath = this.getRequest().getScheme() + "://" + this.getRequest().getServerName() + ":" + this.getRequest().getServerPort() + this.getContext();
		return basePath;
	}

	/**
	 * @description 取得真实的IP地址
	 * @date 2010-7-12
	 * @author liuhh
	 * @return 返回真实的IP地址
	 * @see cn.com.pingtech.sdk.framework.container.StrutsContextUtil#getRemoteAddr(HttpServletRequest)
	 */
	public String getRemoteAddr() {
		return StrutsContextUtil.getRemoteAddr(this.getRequest());
	}

	/**
	 * @description 直接输出内容的简便函数. eg. render("text/plain", "hello",
	 *              "encoding:UTF-8"); render("text/plain", "hello",
	 *              "no-cache:false"); render("text/plain", "hello",
	 *              "encoding:UTF-8", "no-cache:false");
	 * @date Jul 23, 2010
	 * @author liuls
	 * @param contentType
	 *            输出的文本格式 如："text/html"，“text/plain”，"text/xml"
	 * @param content
	 *            对外输出的内容
	 * @param headers
	 *            可变的header数组，目前接受的值为"encoding:"或"no-cache:",默认值分别为UTF-8和true.
	 */
	@Override
	public void render(final String contentType, final String content, final String... headers) {
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			// 分析headers参数
			String encoding = ENCODING_DEFAULT;
			boolean noCache = NOCACHE_DEFAULT;
			for (String header : headers) {
				String headerName = StringUtils.substringBefore(header, ":");
				String headerValue = StringUtils.substringAfter(header, ":");

				if (StringUtils.equalsIgnoreCase(headerName, ENCODING_PREFIX)) {
					encoding = headerValue;
				} else if (StringUtils.equalsIgnoreCase(headerName, NOCACHE_PREFIX)) {
					noCache = Boolean.parseBoolean(headerValue);
				} else {
					throw new IllegalArgumentException(headerName + "不是一个合法的header类型");
				}
			}

			// 设置headers参数
			String fullContentType = contentType + ";charset=" + encoding;
			response.setContentType(fullContentType);
			if (noCache) {
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
			}

			response.getWriter().write(content);
			response.getWriter().flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// Response在不同应用服务器中实现不同。在WAS中需要关闭。
			try {
				response.getWriter().close();
			} catch (IOException e) {
				// do mothing
			}
		}
	}

	/**
	 * @description 直接输出文本.
	 * @see #render(String, String, String...)
	 * @date Jul 23, 2010
	 * @author liuls
	 * @param text
	 *            需要输出的字符串内容
	 * @param headers
	 *            输出的页面头内容
	 */
	@Override
	public void renderText(final String text, final String... headers) {
		render("text/plain", text, headers);
	}

	/**
	 * @description 直接输出HTML.
	 * @see #render(String, String, String...)
	 * @date Jul 23, 2010
	 * @author liuls
	 * @param html
	 *            html格式的字符串
	 * @param headers
	 *            输出的页面头内容
	 */
	@Override
	public void renderHtml(final String html, final String... headers) {
		render("text/html", html, headers);
	}

	/**
	 * @description 直接输出XML.
	 * @see #render(String, String, String...)
	 * @date Jul 23, 2010
	 * @author liuls
	 * @param xml
	 *            xml格式的内容
	 * @param headers
	 *            可变的header数组，目前接受的值为"encoding:"或"no-cache:",默认值分别为UTF-8和true.
	 */
	@Override
	public void renderXml(final String xml, final String... headers) {
		render("text/xml", xml, headers);
	}

	/**
	 * @description 直接输出JSON.
	 * @see #render(String, String, String...)
	 * @date Jul 23, 2010
	 * @author liuls
	 * @param string
	 *            json字符串.
	 * @param headers
	 *            可变的header数组，目前接受的值为"encoding:"或"no-cache:",默认值分别为UTF-8和true.
	 */
	private void renderJsonString(final String string, final String... headers) {
		render("application/json", string, headers);
	}

	/**
	 * @description 直接输出JSON.
	 * @date Jul 23, 2010
	 * @author liuls
	 * @param object
	 *            Java对象,将被转化为json字符串.
	 * @param headers
	 *            可变的header数组，目前接受的值为"encoding:"或"no-cache:",默认值分别为UTF-8和true.
	 * @see #render(String, String, String...)
	 */
	@Override
	public void renderJson(final Object object, final String... headers) {
		if (object == null) {
			renderJsonString(null, headers);
		}
		if (object.getClass().isArray()) {
			String jsonString = JSONArray.fromObject(object, defaultJsonConfig).toString();
			renderJsonString(jsonString, headers);
		} else if (object instanceof Collection<?>) {
			String jsonString = JSONArray.fromObject(((List<?>) object).toArray(), defaultJsonConfig).toString();
			renderJsonString(jsonString, headers);
		} else if (object instanceof String) {
			renderJsonString(object.toString(), headers);
		} else {
			String jsonString = JSONObject.fromObject(object, defaultJsonConfig).toString();
			renderJsonString(jsonString, headers);
		}
	}

	/**
	 * @description 直接输出流.
	 * @date 2010-7-23
	 * @author xiyf
	 * @param contentType
	 *            输出类型
	 * @param inputStream
	 *            输入流
	 * @param headers 文件名称(使用iso编码格式)
	 *           
	 */
	@Override
	public void renderStream(final String contentType, final InputStream inputStream, final String... headers) {
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.setContentType(contentType);

			if (headers != null) {
				response.addHeader("Content-Disposition", "attachment;filename=" + headers[0]);
			}

			byte[] b = new byte[1024];
			int len = -1;
			while ((len = inputStream.read(b, 0, 1024)) != -1) {
				response.getOutputStream().write(b, 0, len);
			}

			response.getOutputStream().flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// Response在不同应用服务器中实现不同。在WAS中需要关闭。
			/*try {
				response.getWriter().close();
			} catch (IOException e) {
				// do mothing
			}*/
		}
	}

	/**
	 * <b>方法名</b>：populate<br>
	 * <b>功能</b>：将request的值populate到bean中。<br>
	 * 使用范围：当实体中不是所有字段都展示在页面上，编辑时造成属性值丢失。<br>
	 * 例如：super.populateFromRequestParameters(user);<br>
	 * @author <font color='blue'>Administrator</font> 
	 * @date  2012-7-25 下午02:14:16
	 */
	public void populateFromRequestParameters(Object bean) {
		populateFromRequestParameters(bean, null);
	}

	/**
	 * <b>方法名</b>：populateFromRequestParameters<br>
	 * <b>功能</b>：将request的值populate到bean中。<br>
	 * 使用范围：当实体中不是所有字段都展示在页面上，编辑时造成属性值丢失。<br>
	 * 在struts2中页面上的name如下entity.xxx时，根据property键值对会注入不进去，把“entity.”当做参数传进来会对parameter中的name进行截取。
	 * @author <font color='blue'>Administrator</font> 
	 * @date  2012-8-8 上午11:16:30
	 * @param bean
	 * @param beanPrefixInParamter
	 */
	public void populateFromRequestParameters(Object bean, String beanPrefixInParamter) {
		// Build a list of relevant request parameters from this request
		HashMap<String, Object> properties = new HashMap<String, Object>();
		// Iterator of parameter names
		Enumeration<?> names = getRequest().getParameterNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			Object parameterValue = getRequest().getParameterValues(name);
			properties.put(name, parameterValue);
			// 根据前缀进行截取
			if (beanPrefixInParamter != null && !beanPrefixInParamter.equals("") && name.startsWith(beanPrefixInParamter)) {
				properties.put(name.substring(beanPrefixInParamter.length()), parameterValue);
			}
		}
		try {
			BeanUtils.populate(bean, properties);
		} catch (IllegalAccessException e) {
			log.error("将request中的参数populate到bean实体过程中出错。");
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			log.error("将request中的参数populate到bean实体过程中出错。");
			e.printStackTrace();
		}
	}

	/**
	 * <p>Title: prepare</p>
	 * <p>Description: </p>
	 * @throws Exception
	 * @see com.opensymphony.xwork2.Preparable#prepare()
	 */

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub

	}
}
