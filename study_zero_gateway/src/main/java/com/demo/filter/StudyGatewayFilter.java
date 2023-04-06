package com.demo.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author: 王孟伟
 * @Date: 2023/3/15 16:48
 * @Description:
 */
@Component
public class StudyGatewayFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println(exchange.getLogPrefix());
        System.out.println(exchange.getApplicationContext().getApplicationName());
        System.out.println(exchange.getFormData());
        System.out.println(exchange.getLocaleContext());
        System.out.println(exchange.getMultipartData());
        System.out.println(exchange.getRequest().getPath());
        System.out.println(chain);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
