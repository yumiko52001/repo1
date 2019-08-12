package com.xuecheng.test.freemarker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Controller
@RequestMapping("/freemarker")
public class FreemarkerController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/test1")
    public String freemarker(Map<String, Object> map) {
        map.put("name", "黑马程序员"); //返回模板文件名称
        return "test1";
    }

    @RequestMapping("/banner")
    public String test02(Map<String,Object> map){
        String url = "http://localhost:31001/cms/config/getModel/5a791725dd573c3574ee333f";

        ResponseEntity<Map> forEntity = restTemplate.getForEntity(url, Map.class);

        Map body = forEntity.getBody();

        map.putAll(body);

        return "index_banner";


    }



}
