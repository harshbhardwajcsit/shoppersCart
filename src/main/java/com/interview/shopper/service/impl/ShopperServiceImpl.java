package com.interview.shopper.service.impl;

import com.interview.shopper.constant.MessageConstants;
import com.interview.shopper.entity.responseEntity.CommonResponse;
import com.interview.shopper.exception.ItemNotFoundException;
import com.interview.shopper.model.CartEvent;
import com.interview.shopper.model.Product;
import com.interview.shopper.model.ShoppingCartItem;
import com.interview.shopper.model.User;
import com.interview.shopper.service.ShopperService;
import com.interview.shopper.service.TransactionService;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopperServiceImpl implements ShopperService {
    private final TransactionService transactionService;


    @Autowired
    public ShopperServiceImpl(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public CommonResponse<?> addItem(Long productId, User user) throws ItemNotFoundException {
        return getCommonResponse(productId, user, CartEvent.ADD);
    }

    @Override
    public CommonResponse<?> removeItem(Long productId, User user) throws ItemNotFoundException {
        return getCommonResponse(productId, user, CartEvent.REMOVE);
    }

    public CommonResponse<?> getCommonResponse(Long productId, User user, CartEvent event) throws ItemNotFoundException {
        final Optional<Product> product = transactionService.getProduct(productId);
        if (product.isPresent()) {
            final List<ShoppingCartItem> cartItems = manageCart(product.get(), user, event);
            transactionService.updateShopperCart(user, cartItems);
            return CommonResponse.success(HttpStatus.CREATED.value(), MessageConstants.SUCCESS, ImmutableList.copyOf(cartItems));

        }
        throw new ItemNotFoundException("Item Not found");
    }

    public List<ShoppingCartItem> manageCart(Product product, User user, CartEvent event) {
        final List<ShoppingCartItem> userCartItems = transactionService.getExistingCartDetails(user);
        final Optional<ShoppingCartItem> item = userCartItems.stream()
                .filter(cartItem -> cartItem.getProductId().equals(product.getProductId()))
                .findFirst();

        if (item.isPresent()) {
            final ShoppingCartItem cartItem = item.get();
            final int indexOfItem = userCartItems.indexOf(item.get());
            final Integer newQty = CartEvent.ADD.equals(event) ? cartItem.getQty() + 1 : cartItem.getQty() - 1;
            final ShoppingCartItem updateItem = new ShoppingCartItem(cartItem.getProductId(),
                    cartItem.getName(), cartItem.getPrice(),
                    newQty);
            userCartItems.set(indexOfItem, updateItem);
        } else {
            if (CartEvent.ADD.equals(event)) {
                userCartItems.add(new ShoppingCartItem(product.getProductId(), product.getName(), product.getPrice(), 1));
            }
        }
        return userCartItems;
    }

}
