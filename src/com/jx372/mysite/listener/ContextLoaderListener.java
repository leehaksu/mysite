package com.jx372.mysite.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class ContextLoaderListener
 *
 */
//@WebListener
public class ContextLoaderListener implements ServletContextListener {


    public void contextInitialized(ServletContextEvent servletContextEvent)  { 
         // TODO Auto-generated method stub
    	servletContextEvent.getServletContext().getInitParameter("contextConfigLocation");
    	System.out.println("컨테이너 시작하였습니다.");
    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    	System.out.println("컨테이너 끝났다");
    }

	
}
