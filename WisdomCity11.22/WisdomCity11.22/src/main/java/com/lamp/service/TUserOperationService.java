package com.lamp.service;

public interface TUserOperationService {
    void recordOperationData(Object nowObj,Object oldObj,Integer userId,String operationKind,Integer result,String modelName);
}
