package com.gujun.springboot06.service;

import com.gujun.springboot06.dao.ProductMapper;
import com.gujun.springboot06.dao.PurchaseRecordMapper;
import com.gujun.springboot06.entity.Product;
import com.gujun.springboot06.entity.PurchaseRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
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

    //悲观锁克服超发问题
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public boolean negative(Integer uId, Integer pId, int quantity) {
        Product product=productMapper.negative(pId);
        if(product.getStock()<quantity){
            return false;
        }
        productMapper.decreaseProduct(pId,quantity);
        PurchaseRecord purchaseRecord=initPurchaseRecord(uId,product,quantity);
        purchaseRecordMapper.savePurchaseRecord(purchaseRecord);
        return true;
    }

    //乐观锁，版本号，克服超发问题(会有库存剩下，库存加消费记录等于之前总数)
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public boolean positiveVersion(Integer uId, Integer pId, int quantity) {
        Product product=productMapper.getProduct(pId);
        if(product.getStock()<quantity){
            return false;
        }
        int version=product.getVersion();
        int result=productMapper.decreaseProductPositiveVersion(pId,quantity,version);
        if(result==0){
            return false;
        }
        PurchaseRecord purchaseRecord=initPurchaseRecord(uId,product,quantity);
        purchaseRecordMapper.savePurchaseRecord(purchaseRecord);
        return true;
    }

    //乐观锁，版本号，限制时间来克服超发问题
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public boolean positiveVersionLimitTime(Integer uId, Integer pId, int quantity) {
        long start=System.currentTimeMillis();
        while (true){
            long end=System.currentTimeMillis();
            if(end-start>200){  //时间超过200ms就终止
                return false;
            }
            Product product=productMapper.getProduct(pId);
            if(product.getStock()<quantity){
                return false;
            }
            int version=product.getVersion();
            int result=productMapper.decreaseProductPositiveVersion(pId,quantity,version);
            if(result==0){
                continue;
            }
            PurchaseRecord purchaseRecord=initPurchaseRecord(uId,product,quantity);
            purchaseRecordMapper.savePurchaseRecord(purchaseRecord);
            return true;
        }
    }

    //乐观锁，版本号，限制重入次数来克服超发问题
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public boolean positiveVersionLimitCount(Integer uId, Integer pId, int quantity) {
        for(int i=0;i<3;i++){
            Product product=productMapper.getProduct(pId);
            if(product.getStock()<quantity){
                return false;
            }
            int version=product.getVersion();
            int result=productMapper.decreaseProductPositiveVersion(pId,quantity,version);
            if(result==0){
                continue;
            }
            PurchaseRecord purchaseRecord=initPurchaseRecord(uId,product,quantity);
            purchaseRecordMapper.savePurchaseRecord(purchaseRecord);
            return true;
        }
        return false;
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
