package com.boredou.mercury.web.util;

import static cn.weili.util.SecurityUtil.md5;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.math.NumberUtils;
import cn.weili.util.StringUtil;
import com.boredou.mercury.repository.entity.User;
import com.boredou.mercury.web.apps.session.SessionAttribute;

/**
 * SessionUtil
 * 
 * Created with IntelliJ IDEA. User: caden Date: 5/5/13 Time: 3:53 PM
 * 
 * To change this template use File | Settings | File Templates.
 */
public final class SessionUtil {

	/**
	 * SessionUtil 
	 */
	private SessionUtil() {

	}

	/**
	 * isLogin
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isLogin(HttpServletRequest request) {
		return request.getSession().getAttribute("user") != null;
	}

	/**
	 * newSession
	 * 
	 * @param request
	 * @param userBasicDO
	 */
	public static void newSession(HttpServletRequest request, User user) {
		if (user == null)
			return;
		HttpSession httpSession = request.getSession();

		final String userAgent = StringUtil.defaultTrimString(request.getHeader("User-Agent"));
		httpSession.setAttribute(SessionAttribute.userAgent.name(), md5(userAgent));

		if (user.getUserId() > 0)
			httpSession.setAttribute("user", user);

	}

	/**
	 * getUserNick
	 * 
	 * @param request
	 * @return
	 */
	public static String getUserNick(HttpServletRequest request) {
		return String.valueOf(request.getSession().getAttribute(SessionAttribute.userNick.name()));
	}

	/**
	 * getUserPower
	 * 
	 * @param request
	 * @return
	 */
	public static String getUserPower(HttpServletRequest request) {
		return String.valueOf(request.getSession().getAttribute(SessionAttribute.userPower.name()));
	}

	/**
	 * getUserId
	 * 
	 * @param request
	 * @return 
	 */
	public static int getUserId(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session == null) {
			return 0;
		}
		return NumberUtils.toInt(ObjectUtils.toString(session.getAttribute(SessionAttribute.userIdNum.name())));
	}

	/**
	 * destroySession
	 * 
	 * @param request
	 */
	public static void destroySession(HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		if (httpSession == null)
			return;
		httpSession.invalidate();
	}


}
