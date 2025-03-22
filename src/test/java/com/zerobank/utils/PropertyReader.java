package com.zerobank.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
	public static Properties readProperty() {
		Properties property = null;
		String fileName = "src\\test\\resources\\config\\config.properties";
		try {
			FileInputStream fis = new FileInputStream(fileName);
			property = new Properties();
			property.load(fis);
		} catch (FileNotFoundException e) {
			System.out.println("File name or file path is not correct.Check again...");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return property;
	}
}
