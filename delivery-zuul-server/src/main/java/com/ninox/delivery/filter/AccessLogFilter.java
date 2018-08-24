package com.ninox.delivery.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class AccessLogFilter extends ZuulFilter{

    Logger logger = LoggerFactory.getLogger(AccessLogFilter.class);
    
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        HttpServletResponse response = RequestContext.getCurrentContext().getResponse();

        logger.info("REQUEST :: < " + request.getScheme() + " " + request.getLocalAddr() + ":" + request.getLocalPort());
        logger.info("REQUEST :: < " + request.getMethod() + " " + request.getRequestURI() + " " + request.getProtocol());
        logger.info("RESPONSE:: > HTTP:" + response.getStatus());

        return null;
	}

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 0;
	}
}
