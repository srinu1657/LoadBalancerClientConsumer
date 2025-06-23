package com.example.LoadBalancerClientConsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ConsumerController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;


    @GetMapping("/info")
    public String getEmpData(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("DISCOVERCLIENTPRODUCER");
        String url = serviceInstance.getUri().toString() + "/emp/show";
        System.out.println("url:"+url);
        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<String> resp = restTemplate.getForEntity(url, String.class);
        return  resp.getBody();

    }

}
