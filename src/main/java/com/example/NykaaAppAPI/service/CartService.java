package com.example.NykaaAppAPI.service;

import com.example.NykaaAppAPI.exception.ResourceNotFoundException;
import com.example.NykaaAppAPI.model.Cart;
import com.example.NykaaAppAPI.model.CartProduct;
import com.example.NykaaAppAPI.model.NykaaUser;
import com.example.NykaaAppAPI.model.Product;
import com.example.NykaaAppAPI.repository.CartProductRepository;
import com.example.NykaaAppAPI.repository.CartRepository;
import com.example.NykaaAppAPI.repository.ProductRepository;
import com.example.NykaaAppAPI.repository.UserRepository;
import com.example.NykaaAppAPI.request.CartRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartProductRepository cartProductRepository;

    public List<CartProduct> addToCart(CartRequest cartRequest) {
        List<CartProduct> cartProducts = new ArrayList<>();

        NykaaUser nykaaUser = userRepository.findById(cartRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with id "
                        + cartRequest.getUserId()));

        Product product = productRepository.findById(cartRequest.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product does not exist with id "
                        + cartRequest.getProductId()));

        Cart cart = cartRepository.findByProductUser(nykaaUser)
                .orElseThrow(() -> new ResourceNotFoundException("No products in the cart for the user"));

            if (cartProductRepository.findByProductAndCart(product , cart).isPresent()){
                cartProducts = cartProductRepository.findByProductAndCart(product , cart).get();
                boolean isExist = false;
                for(CartProduct cartProduct : cartProducts){
                    if(cartProduct.getProduct().equals(product)){
                           cartProduct.setCount(cartRequest.getCount());
                           cartProductRepository.save(cartProduct);
                           isExist = true;
                    }
                }
                if (!isExist) {
                    cartProducts.add(addProductToCart(product, cart, cartRequest.getCount()));
                }

            } else {
                cartProducts.add(addProductToCart(product, cart, cartRequest.getCount()));
            }
        return cartProductRepository.findByCart(nykaaUser.getCart())
                .orElseThrow(() -> new ResourceNotFoundException("No products in the cart for the user"));
    }
    private CartProduct addProductToCart(Product product, Cart cart, int count) {
        CartProduct cartProduct = new CartProduct();
        cartProduct.setProduct(product);
        cartProduct.setCart(cart);
        cartProduct.setCount(count);
        cartProductRepository.save(cartProduct);
        return cartProduct;
    }
    public List<CartProduct> showCartOfUserById(Integer userId) {
        NykaaUser nykaaUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with id "
                        + userId));

        return cartProductRepository.findByCart(nykaaUser.getCart())
                .orElseThrow(() -> new ResourceNotFoundException("No products in the cart for the user"));
    }
    public List<CartProduct> removeProductFromCart(Integer cartProductId) {
        CartProduct cartProduct = cartProductRepository.findById(cartProductId)
                .orElseThrow(() -> new ResourceNotFoundException("No products in the cart for the user"));

        cartProductRepository.deleteById(cartProductId);

        return cartProductRepository.findByCart(cartProduct.getCart())
                .orElseThrow(() -> new ResourceNotFoundException("No products in the cart for the user"));

    }

}