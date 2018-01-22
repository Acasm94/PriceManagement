package com.sep.pricemanagement.config;

import java.util.ArrayList;
import java.util.List;

public class Temp {

	public static List<String> permisije(String rola) {
		List<String> s = new ArrayList<String>();
		if (rola.equals("user")) {
			s.add("a");
			s.add("b");
			s.add("c");
		}
		return s;
	}

}
