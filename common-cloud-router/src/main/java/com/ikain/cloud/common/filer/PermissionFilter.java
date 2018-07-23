package com.ikain.cloud.common.filer;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by FengKai on 2018/7/23.
 */
public class PermissionFilter extends ZuulFilter {

    /**
     * filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：
     * <p>
     * pre：可以在请求被路由之前调用
     * route：在路由请求时候被调用
     * post：在route和error过滤器之后被调用
     * error：处理请求时发生错误时被调用
     *
     * @return String
     */
    @Override
    public String filterType() {
        return "pre"; // 前置过滤器
    }

    /**
     * @param filterOrder：通过int值来定义过滤器的执行顺序
     */
    @Override
    public int filterOrder() {
        return 0; // 优先级为0，数字越大，优先级越低
    }

    /**
     * @param shouldFilter：是否执行该过滤器，说明需要过滤
     */
    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String method = request.getMethod();
        return !"GET".equalsIgnoreCase(method); // 对GET请求不进行过滤
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String token = request.getHeader("Authorization");
        if (token != null) {
            ctx.setSendZuulResponse(true);// 对该请求进行路由
            ctx.setResponseStatusCode(200);
            ctx.set("isSuccess", true);// 设值，让下一个Filter看到上一个Filter的状态
            return null;
        }
        ctx.setSendZuulResponse(false);// 过滤该请求，不对其进行路由
        ctx.setResponseStatusCode(401);// 返回错误码
        ctx.setResponseBody("{\"result\":\"token is not null!\"}");
        ctx.set("isSuccess", false);
        return null;
    }

}
