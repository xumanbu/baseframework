package com.cxypub.baseframework.sdk.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * <b>类名：</b>FileDownloadUtil.java<br>
 * <p><b>标题：</b>OAO商城定制系统</p>
 * <p><b>描述：</b>OAO商城定制系统</p>
 * <p><b>版权声明：</b>Copyright (c) 2016</p>
 * <p><b>公司：</b>上海追月信息科技有限公司 </p>
 * @author <font color='blue'>徐飞</font> 
 * @version 1.0.1
 * @date  2016年3月1日 下午3:24:12
 * @Description 文件下载工具
 */
public class FileDownloadUtil {
	/** 
	 * @param args 
	 */
	public static void main(String[] args) throws Exception {
		String url = "http://localhost:8080/creater/poster";
		for (int i = 0; i < 1000; i++) {
			File imageFile = new File("d://hello/test" + i + ".jpg");
			downloadFile(url, imageFile);
		}
	}

	/**
	 * <b>方法名：</b>：downloadFile<br>
	 * <b>功能说明：</b>：TODO<br>
	 * @author <font color='blue'>徐飞</font> 
	 * @date  2016年3月1日 下午3:26:15
	 * @param urlString url路径
	 * @param saveFile 需要保存的文件名称
	 */
	public static void downloadFile(String urlString, File saveFile) {
		FileOutputStream outStream = null;
		try {
			saveFile.getParentFile().mkdirs();
			outStream = new FileOutputStream(saveFile);
			downloadFile(urlString, outStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * <b>方法名：</b>：downloadFile<br>
	 * <b>功能说明：</b>：下载文件<br>
	 * @author <font color='blue'>徐飞</font> 
	 * @date  2016年3月1日 下午3:25:14
	 * @param urlString url路径
	 * @param saveOutputStream 需要保存到的流
	 */
	public static void downloadFile(String urlString, OutputStream saveOutputStream) {
		InputStream inStream = null;
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(10 * 1000);
			conn.setRequestMethod("GET");
			inStream = conn.getInputStream();
			byte[] b = new byte[1024];
			int length;
			while ((length = inStream.read(b)) > 0) {
				saveOutputStream.write(b, 0, length);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (saveOutputStream != null) {
					saveOutputStream.close();
				}
				if (inStream != null) {
					inStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

}