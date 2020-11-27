package com.sample.hystrix.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@SpringBootApplication
@RestController
@EnableHystrix
@EnableHystrixDashboard
public class SampleHystrix {


	@HystrixCommand(groupKey = "Ketan", commandKey = "Ketan", fallbackMethod = "FallBack",commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "30"),
	        @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "300")
	        })
	@GetMapping("/first")
	public String bookShow() throws InterruptedException {
		Thread.sleep(2000);
		return "First";
	}
	@HystrixCommand(groupKey = "Ketan 2", commandKey = "Ketan 2", fallbackMethod = "FallBack")
	@GetMapping("/second")
	public String bookShow1() {
		return  "Second";
	}
	
	
	

	
	

	public static void main(String[] args) {
		SpringApplication.run(SampleHystrix.class, args);
	}

	public String FallBack() {
		return "service failed...";
	}
}
