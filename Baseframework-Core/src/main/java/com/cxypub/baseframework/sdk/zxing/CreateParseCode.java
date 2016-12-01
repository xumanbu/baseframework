package com.cxypub.baseframework.sdk.zxing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

/**   
 * @Description: (��ͨ��ά�����)      
 * @author��Relieved   
 * @date��2014-11-7 ����04:42:35      
 */
public class CreateParseCode {
	public static void main(String[] args) throws IOException, WriterException {
		CreateParseCode cpCode = new CreateParseCode();
		// ��ɶ�ά��
		cpCode.createCode();
		// ������ά��
		cpCode.parseCode(new File("D:/��ά�����/TDC-test.png"));

	}

	/** 
	 * ��ά������ 
	 * 
	 */
	public void createCode() {
		String text = "http://blog.csdn.net/gao36951";
		int width = 300;
		int height = 300;
		// ��ά���ͼƬ��ʽ
		String format = "png";
		/** 
		 * ���ö�ά��Ĳ��� 
		 */
		Map<EncodeHintType, String> hints = new HashMap<EncodeHintType, String>();
		// ������ʹ�ñ���
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
			// ��ɶ�ά��
			File outputFile = new File("D:" + File.separator + "��ά�����" + File.separator + "TDC-test.png");
			outputFile.getParentFile().mkdirs();
			MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/** 
	 * ��ά��Ľ��� 
	 * 
	 * @param file 
	 */
	public void parseCode(File file) {
		try {
			MultiFormatReader formatReader = new MultiFormatReader();

			if (!file.exists()) {
				return;
			}
			BufferedImage image = ImageIO.read(file);

			LuminanceSource source = new BufferedImageLuminanceSource(image);
			Binarizer binarizer = new HybridBinarizer(source);
			BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);

			Map<DecodeHintType, String> hints = new HashMap<DecodeHintType, String>();
			hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");

			Result result = formatReader.decode(binaryBitmap, hints);

			System.out.println("������� = " + result.toString());
			System.out.println("��ά���ʽ���� = " + result.getBarcodeFormat());
			System.out.println("��ά���ı����� = " + result.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}