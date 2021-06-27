package com.interview.shopper.resource;

import com.interview.shopper.constant.CommonConstants;
import com.interview.shopper.entity.responseEntity.CommonResponse;
import com.interview.shopper.exception.ItemNotFoundException;
import com.interview.shopper.model.User;
import com.interview.shopper.service.ShopperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CommonConstants.API_VERSION + "/cart")
public class ShopperResource {

    final private ShopperService shopperService;

    @Autowired
    public ShopperResource(ShopperService shopperService ) {
        this.shopperService = shopperService;
    }

    @PostMapping
    public CommonResponse<?> addItemToCart(@PathVariable Long productId) throws ItemNotFoundException {
        final User dummyUser = new User(1L, "Harsh", "harsh@email.com");
        return shopperService.addItem(productId, dummyUser);
    }

    @PostMapping
    public CommonResponse<?> removeItemFromCart(@PathVariable Long productId) throws ItemNotFoundException {
        final User dummyUser = new User(1L, "Harsh", "harsh@email.com");
        return shopperService.removeItem(productId, dummyUser);
    }

}
