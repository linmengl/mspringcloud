package com.btc.springcloud.controller;

import com.btc.springcloud.entities.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeptController_Consumer {

	private static final String DEPT_URL_PREFIX = "http://localhost:8001";

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/consumer/dept/add")
	public boolean add(Dept dept){
		return restTemplate.postForObject(DEPT_URL_PREFIX+"/dept/add",dept,Boolean.class);
	}

	@RequestMapping(value = "/consumer/dept/get/{id}")
	public Dept get(@PathVariable Long id){
		return restTemplate.getForObject(DEPT_URL_PREFIX+"/dept/get/"+id,Dept.class);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/consumer/dept/list")
	public List<Dept> list(){
		return restTemplate.getForObject(DEPT_URL_PREFIX+"/dept/list",List.class);
	}

	// 测试@EnableDiscoveryClient,消费端可以调用服务发现
	@RequestMapping(value = "/consumer/dept/discovery")
	public Object discovery()
	{
		return restTemplate.getForObject(DEPT_URL_PREFIX + "/dept/discover", Object.class);
	}
}
