package com.btc.springcloud.service;

import com.btc.springcloud.entities.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService> {
	@Override
	public DeptClientService create(Throwable throwable) {
		return new DeptClientService() {
			@Override
			public Dept get(long id) {
				return new Dept().setDname("服务异常，请稍后重试").setDb_source("no db");
			}

			@Override
			public List<Dept> list() {
				return null;
			}

			@Override
			public boolean add(Dept dept) {
				return false;
			}
		};
	}
}
