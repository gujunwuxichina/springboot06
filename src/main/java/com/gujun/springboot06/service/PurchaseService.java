package com.gujun.springboot06.service;

public interface PurchaseService {

    boolean purchase(Integer uId,Integer pId,int quantity);

    boolean positive(Integer uId,Integer pId,int quantity);

}
