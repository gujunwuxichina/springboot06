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

    @GetMapping("/goNegative")  //乐观锁
    public ModelAndView goNegative(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("purchase/negative");
        return mv;
    }

    @GetMapping("/goPositiveVersion")
    public ModelAndView goPositiveVersion(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("purchase/positiveVersion");
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

    @PostMapping("/negative")
    public JSONObject negative(Integer uId,Integer pId,int quantity){
        JSONObject jsonObject=new JSONObject();
        boolean result=purchaseService.negative(uId,pId,quantity);
        String resultMsg=result?"抢购成功":"抢购失败";
        jsonObject.put("result",resultMsg);
        return jsonObject;
    }

    @PostMapping("/positiveVersion")
    public JSONObject positiveVersion(Integer uId,Integer pId,int quantity){
        JSONObject jsonObject=new JSONObject();
        boolean result=purchaseService.positiveVersion(uId,pId,quantity);
        String resultMsg=result?"抢购成功":"抢购失败";
        jsonObject.put("result",resultMsg);
        return jsonObject;
    }

    @PostMapping("/positiveVersionLimitTime")
    public JSONObject positiveVersionLimitTime(Integer uId,Integer pId,int quantity){
        JSONObject jsonObject=new JSONObject();
        boolean result=purchaseService.positiveVersionLimitTime(uId,pId,quantity);
        String resultMsg=result?"抢购成功":"抢购失败";
        jsonObject.put("result",resultMsg);
        return jsonObject;
    }

    @PostMapping("/positiveVersionLimitCount")
    public JSONObject positiveVersionLimitCount(Integer uId,Integer pId,int quantity){
        JSONObject jsonObject=new JSONObject();
        boolean result=purchaseService.positiveVersionLimitCount(uId,pId,quantity);
        String resultMsg=result?"抢购成功":"抢购失败";
        jsonObject.put("result",resultMsg);
        return jsonObject;
    }

}
