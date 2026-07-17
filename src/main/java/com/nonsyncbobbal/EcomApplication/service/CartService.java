package com.nonsyncbobbal.EcomApplication.service;

import com.nonsyncbobbal.EcomApplication.dto.CartRequest;
import com.nonsyncbobbal.EcomApplication.dto.CartResponse;
import com.nonsyncbobbal.EcomApplication.mapper.CartMapper;
import com.nonsyncbobbal.EcomApplication.model.Cart;
import com.nonsyncbobbal.EcomApplication.model.Product;
import com.nonsyncbobbal.EcomApplication.model.User;
import com.nonsyncbobbal.EcomApplication.repo.CartRepository;
import com.nonsyncbobbal.EcomApplication.repo.ProductRepository;
import com.nonsyncbobbal.EcomApplication.repo.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository  productRepository;
    private final UserRepository userRepository;

    public boolean addToCart(String userId, CartRequest cartRequest) {
        //Validate the product
        Optional<Product> productOptional = productRepository.findById(cartRequest.getProductId());
        if (productOptional.isEmpty())
            return false;
        Product product = productOptional.get();
        if(!product.getIsActive()) return false;
        if(product.getStockQuantity() < cartRequest.getQuantity())
            return false;
        //Validate the user
        Optional<User> userOptional = userRepository.findById(Integer.valueOf(userId));
        if (userOptional.isEmpty())
            return false;
        User user = userOptional.get();
        //Updating the Cart
        Cart existingCart = cartRepository.findByUserAndProduct(user, product);
        if(existingCart != null) {
            //update the cart
            existingCart.setQuantity(existingCart.getQuantity() + cartRequest.getQuantity());
            existingCart.setPrice(product.getPrice().multiply(BigDecimal.valueOf(existingCart.getQuantity())));
            cartRepository.save(existingCart);
        }
        else{
            Cart newCart = new Cart();
            newCart.setUser(user);
            newCart.setProduct(product);
            newCart.setQuantity(cartRequest.getQuantity());
            newCart.setPrice(product.getPrice().multiply(BigDecimal.valueOf(newCart.getQuantity())));
            cartRepository.save(newCart);
        }
        return true;

    }

    public boolean deleteFromCart(String userId, int productId) {
        Optional<Product> productOptional = productRepository.findById(productId);

        Optional<User> userOptional = userRepository.findById(Integer.valueOf(userId));

        if(productOptional.isPresent() && userOptional.isPresent()) {
            cartRepository.deleteByUserAndProduct(userOptional.get(), productOptional.get());
            return true;
        }
        return false;
    }

    public List<CartResponse> getCart(String userId) {
        Optional<List<Cart>> carts = userRepository.findById(Integer.valueOf(userId))
                .map(cartRepository::findByUser);
        return carts.map(CartMapper::mapToCartResponse).orElseGet(List::of);


    }


}
