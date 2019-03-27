package com.lamp.utils;

import com.lamp.model.Tsysuser;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class SystemUtils implements Filter {

	/**在登录之前，有些URL是没有Session的，查找到这些URL，统一进行控制，相当于过滤器中不包含这些方法和连接*/
	List<String> list = new ArrayList<String>();
	/**web容器初始化*/
	public void init(FilterConfig config) throws ServletException {
		//没有Session，但需要放行
		list.add("/login.jsp");
		list.add("/loginOut.do");
	}
	
	/**跳转之前先完成过滤器的方法*/
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		//获取访问的servlet的路径
		String path = request.getServletPath();
		//在跳转到index.jsp页面之前，先获取Cookie，传值的方式给index.jsp
		this.initIndexPage(request, path);
		
		/**添加粗颗粒权限控制*/
		//没有Session需要放行的连接
		if(list.contains(path)){
			//放行
			chain.doFilter(request, response);
			return;
		}
		//判断Session是否存在
		Tsysuser tsysuser = (Tsysuser)request.getSession().getAttribute("loginUser");
		//Session中存在数据，放行，指定对应的URL的页面
		if(tsysuser!=null){
			//放行
			chain.doFilter(request, response);
			return;
		}
		
		//如果Session中不存在数据，重定向到index.jsp的页面上
		//response.sendRedirect(request.getContextPath()+"/index.jsp");
		//如果Session中不存在数据，重定向到error.jsp的页面上
		//response.sendRedirect(request.getContextPath()+"/loginOut.do");
		response.getWriter().write("<script>window.parent.location.href='login.jsp'</script>");
	}

	

	/**销毁*/
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	/**在跳转到index.jsp页面之前，先获取Cookie，传值的方式给index.jsp*/
	private void initIndexPage(HttpServletRequest request, String path) {
		if(path!=null && path.equals("/login.jsp") || path.equals("/loginOut.do")){
			//获取Cookie中存放的用户名和密码
			//用户名
			String name = "";
			//密码
			String password = "";
			//复选框
			String checked = "";

			String autoLogin = "";
			//从Cookie中获取记住我中存放的登录名和密码
			Cookie[] cookies = request.getCookies();
			if(cookies!=null && cookies.length>0){
				for(Cookie cookie:cookies){
					if("name".equals(cookie.getName())){
						//用户名
						name = cookie.getValue();
						//如果是中文，需要解码
						try {
							name = URLDecoder.decode(name, "UTF-8");
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
						//复选框选中
						checked = "checked";
					}
					if("password".equals(cookie.getName())){
						//密码
						password = cookie.getValue();
					}
					if ("autoLogin".equals(cookie.getName()) && path.equals("/login.jsp")){
						autoLogin = cookie.getValue();
						System.out.println("autoLogin: "+ autoLogin);
					}
					
				}
			}
			//存放到request中
			request.setAttribute("name", name);
			request.setAttribute("password", password);
			request.setAttribute("checked", checked);
			request.setAttribute("autoChecked", autoLogin);
		}
	}
	

	
}