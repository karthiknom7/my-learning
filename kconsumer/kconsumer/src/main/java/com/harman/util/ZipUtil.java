package com.harman.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.log4j.Logger;

public class ZipUtil {

	private static final Logger LOGGER = Logger.getLogger(ZipUtil.class);

	public static byte[] zip(byte[] rawData) {
		byte[] compressedData = null;
		try {
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream(rawData.length);
			try {
				GZIPOutputStream zipStream = new GZIPOutputStream(byteStream);
				try {
					zipStream.write(rawData);
				} finally {
					zipStream.close();
				}
			} finally {
				byteStream.close();
			}
			compressedData = byteStream.toByteArray();
		} catch (Exception e) {
			LOGGER.error("zipping failed!!!!", e);
			e.printStackTrace();
		}

		return compressedData;
	}

	public static String unzipData(byte[] rawData) {
		String decompressedData = null;
		byte[] buffer = new byte[8192];
		try {
			ByteArrayInputStream byteStream = new ByteArrayInputStream(rawData);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			try {
				GZIPInputStream zipStream = new GZIPInputStream(byteStream);
				try {

					int c = 0;
					while ((c = zipStream.read(buffer)) > 0) {
						out.write(buffer, 0, c);
					}
				} finally {
					zipStream.close();
				}
			} finally {
				byteStream.close();
			}
			decompressedData = out.toString("UTF-8");
		} catch (Exception e) {
			LOGGER.error("zipping failed!!!!" + e);
			e.printStackTrace();
		}

		return decompressedData;
	}
}
