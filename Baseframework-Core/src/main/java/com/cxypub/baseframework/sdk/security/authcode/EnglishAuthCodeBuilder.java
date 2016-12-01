package com.cxypub.baseframework.sdk.security.authcode;

import java.io.OutputStream;
import java.util.Random;

public abstract class EnglishAuthCodeBuilder implements AuthCodeBuilder {

	// 使用到Algerian字体，系统里没有的话需要安装字体，字体只显示大写，去掉了1,0,i,o几个容易混淆的字符
	public static final String VERIFY_CODES = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";

	public Random random = new Random();

	@Override
	public String generateAuthCode(int codeSize) {
		int codesLen = VERIFY_CODES.length();
		Random rand = new Random(System.currentTimeMillis());
		StringBuilder verifyCode = new StringBuilder(codeSize);
		for (int i = 0; i < codeSize; i++) {
			verifyCode.append(VERIFY_CODES.charAt(rand.nextInt(codesLen - 1)));
		}
		return verifyCode.toString();
	}

	@Override
	public abstract void outputImage(int with, int height, OutputStream os, String code);

}
