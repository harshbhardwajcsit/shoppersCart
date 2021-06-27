package com.interview.shopper.service.impl;

import com.interview.shopper.model.Product;
import com.interview.shopper.model.ShoppingCartItem;
import com.interview.shopper.model.User;
import com.interview.shopper.service.TransactionService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TransactionServiceImpl implements TransactionService {

    private final Map<User, List<ShoppingCartItem>> allShopperCart = new ConcurrentHashMap<>();
    private final List<Product> products = new ArrayList<>();

    @Override
    public List<ShoppingCartItem> updateShopperCart(User user, List<ShoppingCartItem> shoppingCartItems) {
        if (CollectionUtils.isEmpty(shoppingCartItems)) {
            allShopperCart.remove(user);
        } else {
            allShopperCart.put(user, shoppingCartItems);
        }
        return allShopperCart.get(user);
    }

    @Override
    public List<ShoppingCartItem> getExistingCartDetails(User user) {
        return allShopperCart.get(user);
    }

    @Override
    public List<Product> addProductToSystem(Product product) {
        products.add(product);
        return products;
    }

    @Override
    public Optional<Product> getProduct(Long productId) {
        return products.stream()
                .filter(product -> product.getProductId().equals(productId))
                .findFirst();
    }


}
