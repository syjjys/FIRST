package com.example.rlone.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class XgController  {

    private Object XgDto1;

    @GetMapping(value = "/random/xg")
    @ResponseBody
    public ResponseEntity getRandom(){
        List<String> names = new ArrayList<>();
        names.add("魏瑞");
        names.add("陈静");
        names.add("王蕾");
        names.add("朱欣");
        names.add("张传进");
        names.add("张仕韬");
        names.add("美丽善良的佳敏姐");
        names.add("丁敬");
        names.add("范秋菊");
        names.add("方佳欣");
        names.add("付秋佳");
        names.add("冯敏");
        names.add("顾予晨");
        names.add("黄淑杰");
        names.add("黄思渊");
        names.add("黄岚");
        names.add("韩贻敏");
        names.add("李丽");
        names.add("李丹");
        names.add("刘婕");
        names.add("李婷");
        names.add("马晟");
        names.add("浦洋");
        names.add("邵永健");
        names.add("苏旸");
        names.add("宋苗苗");
        names.add("孙辉");
        names.add("孙忠禹");
        names.add("孙小彤");
        names.add("申永强");
        names.add("田阳");
        names.add("王金怡");
        names.add("王聪");
        names.add("张根全");
        names.add("许竹萱");
        names.add("郁文超");
        names.add("袁琳");
        names.add("杨志杰");
        names.add("杨亚亚");
        names.add("叶倩倩");
        names.add("张政");
        names.add("张倩颖");
        names.add("张天");
        names.add("陈万里");
        names.add("王紫莹");
//        int i = new Random().nextInt(45);
//        int q = new Random().nextInt(45);
//        int w = new Random().nextInt(45);
//        int e = new Random().nextInt(45);
//        int r = new Random().nextInt(45);
//        int t = new Random().nextInt(45);
//        int y = new Random().nextInt(45);
//        int u = new Random().nextInt(45);
//        int o = new Random().nextInt(45);
//        int p = new Random().nextInt(45);
        XgDto xgDto = new XgDto();
        xgDto.setName(names);
        XgDto1 = xgDto;
        return ResponseEntity.ok(XgDto1);
    }
}
