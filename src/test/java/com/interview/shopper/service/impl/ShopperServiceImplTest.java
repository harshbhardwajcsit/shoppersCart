package com.interview.shopper.service.impl;

import com.interview.shopper.entity.responseEntity.CommonResponse;
import com.interview.shopper.exception.ItemNotFoundException;
import com.interview.shopper.model.CartEvent;
import com.interview.shopper.model.Product;
import com.interview.shopper.model.ShoppingCartItem;
import com.interview.shopper.model.User;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
class ShopperServiceImplTest {

    public ShopperServiceImplTest() {
    }

    @InjectMocks
    private ShopperServiceImpl shopperService;

    @Mock
    private TransactionServiceImpl transactionService;

    @BeforeEach
    void setUp() {
        transactionService = new TransactionServiceImpl();
        MockitoAnnotations.initMocks(this);
        shopperService = new ShopperServiceImpl(transactionService);

        final Product product1 = new Product(1001L, "product1", 100.0);
        final Product product2 = new Product(1002L, "product2", 100.0);

        transactionService.addProductToSystem(product1);
        transactionService.addProductToSystem(product2);

    }

    @Test
    void testForAddingItemFirstTime() {
        final Product product1 = new Product(1001L, "product1", 100.0);
        final User dummyUser = new User(1L, "Harsh", "harsh@email.com");
        final List<ShoppingCartItem> items = shopperService.manageCart(product1, dummyUser, CartEvent.ADD);
        final Optional<ShoppingCartItem> shoppingCartItem = items.stream()
                .filter(item -> item.getProductId().equals(product1.getProductId()))
                .findFirst();
        Integer qty = shoppingCartItem.get().getQty();
        Assert.assertEquals(Optional.ofNullable(qty), Optional.of(1));
    }

    @Test
    void testForShoppingCartItemsCount() {
        final Product product1 = new Product(1001L, "product1", 100.0);
        final User dummyUser = new User(1L, "Harsh", "harsh@email.com");
        final List<ShoppingCartItem> items = shopperService.manageCart(product1, dummyUser, CartEvent.ADD);
        final List<ShoppingCartItem> items2 = shopperService.manageCart(product1, dummyUser, CartEvent.ADD);
        final long shoppingCartItemsCount = items2.stream()
                .filter(item -> item.getProductId().equals(product1.getProductId()))
                .count();
        Assert.assertEquals(shoppingCartItemsCount, 1);
    }

    @Test
    void testForProductQtyInShoppingCart() {
        final Product product1 = new Product(1001L, "product1", 100.0);
        final User dummyUser = new User(1L, "Harsh", "harsh@email.com");
        final List<ShoppingCartItem> items = shopperService.manageCart(product1, dummyUser, CartEvent.ADD);
        final List<ShoppingCartItem> items2 = shopperService.manageCart(product1, dummyUser, CartEvent.ADD);
        final Optional<ShoppingCartItem> shoppingCartItem = items2.stream()
                .filter(item -> item.getProductId().equals(product1.getProductId()))
                .findFirst();
        final Integer qty = shoppingCartItem.get().getQty();
        Assert.assertEquals(Optional.ofNullable(qty), Optional.of(2));
    }

    @Test
    void testForProductNotFound() {
        Assertions.assertThrows(ItemNotFoundException.class, () -> {
            final Product product1 = new Product(1005L, "product3", 100.0);
            final User dummyUser = new User(1L, "Harsh", "harsh@email.com");
            final CommonResponse<?> commonResponse = shopperService.getCommonResponse(1005L, dummyUser, CartEvent.ADD);
        });
    }
}
