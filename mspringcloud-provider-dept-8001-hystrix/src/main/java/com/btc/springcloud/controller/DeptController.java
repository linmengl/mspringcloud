package com.btc.springcloud.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.btc.springcloud.entities.Dept;
import com.btc.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeptController {

	@Autowired
	private DeptService deptService;

	@Autowired
	private DiscoveryClient discoveryClient;

	@RequestMapping(value = "/dept/add", method = RequestMethod.POST)
	public Boolean add(Dept dept) {
		return deptService.add(dept);
	}

	@RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "hystrixCommand")
	public Dept get(@PathVariable Long id) {
		System.out.println("aaa");
		Dept dept = deptService.get(id);
		if (dept == null){
			throw new RuntimeException("没有id为{}"+id+"对应的数据");
		}
		return dept;
	}

	private Dept hystrixCommand(@PathVariable Long id){
		return new Dept().setDname("没有id为"+id+"对应的数据").setDb_source("no db");
	}

	@RequestMapping(value = "/dept/list", method = RequestMethod.GET)
	public List<Dept> list() {
		return deptService.list();
	}

	@RequestMapping(value = "/dept/discover",method = RequestMethod.GET)
	public Object discovery(){
		List<String> services = discoveryClient.getServices();
		System.out.println(JSONUtils.toJSONString(services));
		List<ServiceInstance> instanceList  = discoveryClient.getInstances("mspringcloud-provider-dept");
		for (ServiceInstance instance:instanceList){
			System.out.println(instance.getServiceId()+"=="+instance.getHost()+"=="+instance.getUri());
		}
		return this.discoveryClient;


	}
}
