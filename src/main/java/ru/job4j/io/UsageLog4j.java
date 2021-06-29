package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
	private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

	public static void main(String[] args) {
		byte num1 = 127;
		short num2 = 32767;
		char num3 = 'd';
		int num4 = 133;
		long num5 = 14214124124L;
		float num6 = 123.4f;
		double num7 = 224.4;
		boolean num8 = true;

		LOG.debug("byte:{}, short:{}, char:{}, int:{}, long:{}, float:{}, double:{}, boolean:{}",
				num1, num2, num3, num4, num5, num6, num7, num8);
	}
}
