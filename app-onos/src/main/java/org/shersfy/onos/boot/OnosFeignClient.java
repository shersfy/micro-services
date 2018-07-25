package org.shersfy.onos.boot;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("app-accel-ppp")
public interface OnosFeignClient {

    // spring mvc 注解, 不能使用GetMapping
    // @PathVariable 必须指定值
    // GET 请求参数如果是对象，即便指定get 也会被当做post
    // @FeignClient 不能和@ComponentScan("org.shersfy.onos")扫描重复，否则找不到bean
    @RequestMapping(method = RequestMethod.GET, value = "/offline/{username}")
    public boolean callPPPUserOffline(@PathVariable("username")String username);
}
