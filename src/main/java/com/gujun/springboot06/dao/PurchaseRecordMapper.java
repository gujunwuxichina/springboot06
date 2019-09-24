package com.gujun.springboot06.dao;

import com.gujun.springboot06.entity.PurchaseRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRecordMapper {

    int savePurchaseRecord(PurchaseRecord purchaseRecord);

}
