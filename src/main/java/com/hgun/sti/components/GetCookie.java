package com.hgun.sti.components;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class GetCookie {
    public static Cookie getCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals( cookieName ))
                    return cookie;
            }
        }
        return null;
    }
}
