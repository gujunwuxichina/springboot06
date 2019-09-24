package com.gujun.springboot06.controller;

import com.alibaba.fastjson.JSONObject;
import com.gujun.springboot06.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;

@RequestMapping("purchase")
@RestController
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/goPurchase01")
    public ModelAndView goPurchase01(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("purchase/purchase01");
        return mv;
    }

    @GetMapping("/goPurchase02")
    public ModelAndView goPurchase02(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("purchase/purchase02");
        return mv;
    }

    @GetMapping("/goPositive")  //乐观锁
    public ModelAndView goPositive(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("purchase/positive");
        return mv;
    }

    @PostMapping("/purchaseProduct")
    public JSONObject purchaseProduct(Integer uId,Integer pId,int quantity){
        JSONObject jsonObject=new JSONObject();
        boolean result=purchaseService.purchase(uId,pId,quantity);
        String resultMsg=result?"抢购成功":"抢购失败";
        jsonObject.put("result",resultMsg);
        return jsonObject;
    }

    @PostMapping("/positive")
    public JSONObject positive(Integer uId,Integer pId,int quantity){
        JSONObject jsonObject=new JSONObject();
        boolean result=purchaseService.positive(uId,pId,quantity);
        String resultMsg=result?"抢购成功":"抢购失败";
        jsonObject.put("result",resultMsg);
        return jsonObject;
    }


}
