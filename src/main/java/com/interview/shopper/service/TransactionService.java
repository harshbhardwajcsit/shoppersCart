package com.interview.shopper.service;

import com.interview.shopper.model.Product;
import com.interview.shopper.model.ShoppingCartItem;
import com.interview.shopper.model.User;

import java.util.List;
import java.util.Optional;

public interface TransactionService {

    public List<ShoppingCartItem> updateShopperCart(User user, List<ShoppingCartItem> shoppingCartItems);

    public List<ShoppingCartItem> getExistingCartDetails(User user);

    public List<Product> addProductToSystem(Product product);

    public Optional<Product> getProduct(Long productId);
}
