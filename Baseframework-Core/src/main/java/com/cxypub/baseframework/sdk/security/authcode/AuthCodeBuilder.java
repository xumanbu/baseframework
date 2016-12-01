package com.cxypub.baseframework.sdk.security.authcode;

import java.io.OutputStream;

public interface AuthCodeBuilder {

	/**
	 * @Title: generateAuthCode
	 * @Description: 随机生成验证码字符串
	 * @param codeSize
	 * @return
	 * @author 徐飞
	 */
	String generateAuthCode(int codeSize);

	/**
	 * @Title: outputImage
	 * @Description: 根据传入的验证码字符串生成图片
	 * @param with
	 * @param height
	 * @param os
	 * @param code
	 * @author 徐飞
	 */
	void outputImage(int with, int height, OutputStream os, String code);

	/**
	 * @Title: outputVerifyImage
	 * @Description: 随机生成验证码，并生成图片，返回验证码
	 * @param with
	 * @param height
	 * @param os
	 * @param verifySize
	 * @return
	 * @author 徐飞
	 */
	String outputAuthImage(int with, int height, OutputStream os, int codeSize);
}
