package com.system.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtil {

	/**
	 * 上传文件
	 * 
	 * @param in
	 * @param fileName
	 * @param account
	 * @throws IOException
	 */
	public static void uploadFile(InputStream in, String filePath,
			String account) throws IOException {
		// 创建一个文件输出流
		FileOutputStream fos = new FileOutputStream(filePath);

		// 创建一个缓冲区
		byte buffer[] = new byte[1024];
		// 判断输入流中的数据是否已经读完的标识
		int length = 0;
		// 循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
		while ((length = in.read(buffer)) > 0) {
			// 使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" +
			// filename)当中
			fos.write(buffer, 0, length);
		}
		// 关闭输入流
		in.close();
		// 关闭输出流
		fos.close();
	}

}
