package com.reserv.myapplicationeli.slidingmenu.tools;

public class StringSplitter {
	String s, delimiter;

	public StringSplitter(String s, String delimiter) {
		this.s = s;
		this.delimiter = delimiter;
	}

	public String nextToken() {
		String res = s;
		if (s.contains(delimiter)) {
			res = s.substring(0, s.indexOf(delimiter));
			s = s.substring(s.indexOf(delimiter) + delimiter.length());
		} 
		return res;
	}
}
