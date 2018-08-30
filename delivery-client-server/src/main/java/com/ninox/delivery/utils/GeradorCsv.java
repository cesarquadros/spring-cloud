package com.ninox.delivery.utils;

import java.io.File;

public class GeradorCsv {

	public static void main(String[] args) {
		
		String caminho = System.getProperty("user.dir");
		File path = new File(caminho);
		
		File[] files = path.listFiles();
	}
}
