package com.gujun.springboot06.service;

import com.gujun.springboot06.dao.ProductMapper;
import com.gujun.springboot06.dao.PurchaseRecordMapper;
import com.gujun.springboot06.entity.Product;
import com.gujun.springboot06.entity.PurchaseRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private PurchaseRecordMapper purchaseRecordMapper;

    @Override
    @Transactional
    public boolean purchase(Integer uId, Integer pId, int quantity) {
        Product product=productMapper.getProduct(pId);
        if(product.getStock()<quantity){
            return false;
        }
        productMapper.decreaseProduct(pId,quantity);
        PurchaseRecord purchaseRecord=initPurchaseRecord(uId,product,quantity);
        purchaseRecordMapper.savePurchaseRecord(purchaseRecord);
        return true;
    }

    @Override
    public boolean positive(Integer uId, Integer pId, int quantity) {
        Product product=productMapper.positive(pId);
        if(product.getStock()<quantity){
            return false;
        }
        productMapper.decreaseProduct(pId,quantity);
        PurchaseRecord purchaseRecord=initPurchaseRecord(uId,product,quantity);
        purchaseRecordMapper.savePurchaseRecord(purchaseRecord);
        return true;
    }

    private PurchaseRecord initPurchaseRecord(Integer uId, Product product, int quantity){
        PurchaseRecord purchaseRecord=new PurchaseRecord();
        purchaseRecord.setNote("购买时间:"+new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        purchaseRecord.setPrice(product.getPrice());
        purchaseRecord.setpId(product.getpId());
        purchaseRecord.setQuantity(quantity);
        purchaseRecord.setSum(product.getPrice()*quantity);
        purchaseRecord.setuId(uId);
        return purchaseRecord;
    }

}
