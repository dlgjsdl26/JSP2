package kr.or.ddit.utils;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;

/**
 * 쿠키의 생성과 획득을 지원하는 유틸리티
 * @author PC-26
 *
 */
public class CookieUtils {
	public static Cookie createCookie(String name, String value) throws IOException {
		value = URLEncoder.encode(value, "UTF-8");
		Cookie cookie = new Cookie(name, value);
		return cookie;
	}
	
	public static Cookie createCookie(String name, String value, int maxAge) throws IOException {
		Cookie cookie = createCookie(name, value);
		cookie.setMaxAge(maxAge);
		return cookie;
	}
	
	public static enum TextType {DOMAIN, PATH};
	/**
	 * 
	 * @param name
	 * @param value
	 * @param type 다음 파라미터 text를 결정
	 * @param text
	 * @return
	 * @throws IOException
	 */
	public static Cookie createCookie(String name, String value, TextType type, String text) throws IOException {
		Cookie cookie = createCookie(name, value);
		if(TextType.DOMAIN == type) {
			cookie.setDomain(text);
		}else if(TextType.PATH == type) {
			cookie.setPath(text);
		}
		return cookie;
	}
	
	public static Cookie createCookie(String name, String value, TextType type, String text, int maxAge) throws IOException {
		Cookie cookie = createCookie(name, value, type, text);
		cookie.setMaxAge(maxAge);
		return cookie;
	}
	
	public static Cookie createCookie(String name, String value, String domain, String path) throws IOException {
		Cookie cookie = createCookie(name, value);
		cookie.setPath(path);
		cookie.setDomain(domain);
		return cookie;
	}
	
	public static Cookie createCookie(String name, String value, String domain, String path, int maxAge) throws IOException {
		Cookie cookie = createCookie(name, value, domain, path);
		cookie.setMaxAge(maxAge);
		return cookie;
	}
}
