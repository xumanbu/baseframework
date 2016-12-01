package com.cxypub.baseframework.sdk.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 
 * @ClassName: ImgCompress
 * @Description: 图片压缩处理
 * @author Comsys-陈鹏
 * @date 2015-5-27 下午02:46:40
 *
 */
public class ImageCompress {

	/** 
	 * 按照宽度还是高度进行压缩 
	 * @param w int 最大宽度 
	 * @param h int 最大高度 
	 */
	public static boolean resizeFix(Image img, File dest, int w, int h) throws IOException {
		if (img.getWidth(null) / img.getHeight(null) > w / h) {
			return resizeByWidth(img, dest, w);
		} else {
			return resizeByHeight(img, dest, h);
		}
	}

	/**
	 * @Title: resizeByWidthAndHeight
	 * @Description: 按照宽度和高度进行压缩 
	 * @param img
	 * @param dest
	 * @param w
	 * @param h
	 * @return
	 * @throws IOException  传入参数
	 * @throws
	 */
	public static boolean resizeByWidthAndHeight(Image img, File dest, int w, int h) throws IOException {
		return resize(img, dest, w, h);
	}

	/** 
	 * 以宽度为基准，等比例放缩图片 
	 * @param w int 新宽度 
	 */
	public static boolean resizeByWidth(Image img, File dest, int w) throws IOException {
		int h = img.getHeight(null) * w / img.getWidth(null);
		return resize(img, dest, w, h);
	}

	/** 
	 * 以高度为基准，等比例缩放图片 
	 * @param h int 新高度
	 */
	public static boolean resizeByHeight(Image img, File dest, int h) throws IOException {
		int w = img.getWidth(null) * h / img.getHeight(null);
		return resize(img, dest, w, h);
	}

	/** 
	 * 强制压缩/放大图片到固定的大小 
	 * @param img  图片处理类
	 * @param dest  目标文件
	 * @param w int 新宽度 
	 * @param h int 新高度 
	 */
	public static boolean resize(Image img, File dest, int w, int h) throws ImageFormatException, IOException {
		// SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(dest);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(image); // JPEG编码
			return true;
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
		// 输出到文件流
		// 可以正常实现bmp、png、gif转jpg
		return false;
	}

}