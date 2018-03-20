package com.wemeCity.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wemeCity.admin.sysUser.model.SysUser;
import com.wemeCity.common.utils.Constants;
import com.wemeCity.common.utils.RequestUtils;

public class BusiFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		HttpSession session = request.getSession();
		String requestURI = request.getRequestURI();
		// get webRoot
		String webRoot = RequestUtils.getWebRoot(session.getServletContext());
		// get current user
		SysUser user = (SysUser) session.getAttribute(Constants.CURRENT_USER);
		if (user == null && !requestURI.endsWith("doLogin")) {
			response.sendRedirect(webRoot + "/login");
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
	}

}
