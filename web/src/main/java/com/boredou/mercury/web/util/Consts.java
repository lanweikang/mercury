package com.boredou.mercury.web.util;

import java.nio.charset.Charset;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

public class Consts {
	public static final Header EMPTY_USER_AGENT = new BasicHeader("User-Agent", "");
    public static final Header CHEOME_USER_AGENT = new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.116 Safari/537.36");
    public static final Header IPHONE_USER_AGENT = new BasicHeader("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 7_0_4 like Mac OS X) AppleWebKit/537.51.1 (KHTML, like Gecko) Mobile/11B554a MicroMessenger/5.2.1");
    public static final Header ACCEPT = new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
    public static final Charset GB2312 = Charset.forName("GB2312");
    public static final Charset GBK = Charset.forName("GBK");
    public static final Charset UTF8 = Charset.forName("UTF-8");
}
