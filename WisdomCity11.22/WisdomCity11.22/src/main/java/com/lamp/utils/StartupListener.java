package com.lamp.utils;

import com.lamp.common.HuaWeiCommon;
import org.springframework.beans.CachedIntrospectionResults;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.beans.Introspector;

/**
 * 项目启动初始化方法
 */
public class StartupListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        CachedIntrospectionResults.acceptClassLoader(Thread.currentThread().getContextClassLoader());
        String path = event.getServletContext().getRealPath("/");
        HuaWeiCommon.PROJECT_PATH = path;
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        CachedIntrospectionResults.clearClassLoader(Thread.currentThread().getContextClassLoader());
        Introspector.flushCaches();
    }
}
