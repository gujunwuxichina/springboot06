package com.gujun.springboot06.service;

public interface PurchaseService {

    boolean purchase(Integer uId,Integer pId,int quantity);

    boolean negative(Integer uId,Integer pId,int quantity);

    boolean positiveVersion(Integer uId,Integer pId,int quantity);

    boolean positiveVersionLimitTime(Integer uId,Integer pId,int quantity);

    boolean positiveVersionLimitCount(Integer uId,Integer pId,int quantity);

}
