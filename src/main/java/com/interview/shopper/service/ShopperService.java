package com.interview.shopper.service;

import com.interview.shopper.entity.responseEntity.CommonResponse;
import com.interview.shopper.exception.ItemNotFoundException;
import com.interview.shopper.model.User;

public interface ShopperService {

    CommonResponse<?> addItem(Long productId, User user) throws ItemNotFoundException;

    CommonResponse<?> removeItem(Long productId, User user) throws ItemNotFoundException;
}
