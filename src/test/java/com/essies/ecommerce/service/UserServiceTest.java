package com.essies.ecommerce.service;

import com.essies.ecommerce.data.model.Address;
import com.essies.ecommerce.data.model.Cart;
import com.essies.ecommerce.data.model.Product;
import com.essies.ecommerce.data.model.Rating;
import com.essies.ecommerce.data.repository.UserRepository;
import com.essies.ecommerce.dto.request.AddToCartRequest;
import com.essies.ecommerce.dto.request.ProductReviewRequest;
import com.essies.ecommerce.dto.request.RegisterUserRequest;
import com.essies.ecommerce.dto.response.ProductReviewResponse;
import com.essies.ecommerce.dto.response.RegisteredUserResponse;
import com.essies.ecommerce.exception.ExceededNumberOfReviewPerOrderException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressService addressService;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderHistoryService orderHistoryService;
    @Autowired
    private ReviewService reviewService;
    @BeforeEach
    public void setUp(){
        userRepository.deleteAll();
        addressService.deleteAll();
        cartService.deleteAll();
        orderItemService.deleteAll();
        orderService.deleteAll();
        orderHistoryService.deleteAll();
        reviewService.deleteAll();
    }
    @Test
    public void testThatUserCanRegister(){
        Address address = new Address();
        address.setCity("Lagos");
        address.setCountry("Nigeria");
        address.setPostalCode("100200");
        address.setLgaName("Ikorodu");
        address.setStreetName("Ogbomosho Street");
        address.setStreetNumber("16");

        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername("essie29");
        registerUserRequest.setEmail("estheraiyeola@yahoo.com");
        registerUserRequest.setFirstName("Esther");
        registerUserRequest.setLastName("Aiyeola");
        registerUserRequest.setPassword("password");
        registerUserRequest.setAddress(address);
        registerUserRequest.setPhoneNumber("08138112782");
        RegisteredUserResponse response = userService.registerUser(registerUserRequest);

        assertThat(response.getStatus(), is("Successful"));

    }

    @Test
    public void testThatUserCanFindProductsByName(){
        Address address = new Address();
        address.setCity("Lagos");
        address.setCountry("Nigeria");
        address.setPostalCode("100200");
        address.setLgaName("Ikorodu");
        address.setStreetName("Ogbomosho Street");
        address.setStreetNumber("16");

        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername("essie29");
        registerUserRequest.setEmail("estheraiyeola@yahoo.com");
        registerUserRequest.setFirstName("Esther");
        registerUserRequest.setLastName("Aiyeola");
        registerUserRequest.setPassword("password");
        registerUserRequest.setAddress(address);
        registerUserRequest.setPhoneNumber("08138112782");
        RegisteredUserResponse response = userService.registerUser(registerUserRequest);
        assertThat(response.getStatus(), is("Successful"));

        Product foundProduct = userService.findProduct("Milk");
        assertThat(foundProduct.getName(), is("Milk"));
    }
    @Test
    public void testThatUserCanFindProductsByCategory(){
        Address address = new Address();
        address.setCity("Lagos");
        address.setCountry("Nigeria");
        address.setPostalCode("100200");
        address.setLgaName("Ikorodu");
        address.setStreetName("Ogbomosho Street");
        address.setStreetNumber("16");

        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername("essie29");
        registerUserRequest.setEmail("estheraiyeola@yahoo.com");
        registerUserRequest.setFirstName("Esther");
        registerUserRequest.setLastName("Aiyeola");
        registerUserRequest.setPassword("password");
        registerUserRequest.setAddress(address);
        registerUserRequest.setPhoneNumber("08138112782");
        RegisteredUserResponse response = userService.registerUser(registerUserRequest);
        assertThat(response.getStatus(), is("Successful"));

        List<Product> foundProduct = userService.findProductsByCategoryName("Food");
        assertThat(foundProduct.get(0).getName(), is("Milk"));
    }

    @Test
    public void testThatUserAddProductsToCart(){
        Address address = new Address();
        address.setCity("Lagos");
        address.setCountry("Nigeria");
        address.setPostalCode("100200");
        address.setLgaName("Ikorodu");
        address.setStreetName("Ogbomosho Street");
        address.setStreetNumber("16");

        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername("essie29");
        registerUserRequest.setEmail("estheraiyeola@yahoo.com");
        registerUserRequest.setFirstName("Esther");
        registerUserRequest.setLastName("Aiyeola");
        registerUserRequest.setPassword("password");
        registerUserRequest.setAddress(address);
        registerUserRequest.setPhoneNumber("08138112782");
        RegisteredUserResponse response = userService.registerUser(registerUserRequest);
        assertThat(response.getStatus(), is("Successful"));

        AddToCartRequest cartRequest1 = new AddToCartRequest();
        cartRequest1.setProductName("Milk");
        cartRequest1.setQuantity(5L);
        cartRequest1.setUserId(response.getUserId());

        AddToCartRequest cartRequest2 = new AddToCartRequest();
        cartRequest2.setProductName("Chocolate");
        cartRequest2.setQuantity(2L);
        cartRequest2.setUserId(response.getUserId());

        AddToCartRequest cartRequest3 = new AddToCartRequest();
        cartRequest3.setProductName("Chocolate Cake");
        cartRequest3.setQuantity(5L);
        cartRequest3.setUserId(response.getUserId());

        List<AddToCartRequest> addToCartRequestList = List.of(cartRequest1, cartRequest2, cartRequest3);
        Cart cartResponse = userService.addProductToCart(addToCartRequestList, response.getUserId());
        assertThat(cartResponse.getOrderItems().size(), is(3));
    }

    @Test
    public void testThatUserRemoveProductFromCart(){
        Address address = new Address();
        address.setCity("Lagos");
        address.setCountry("Nigeria");
        address.setPostalCode("100200");
        address.setLgaName("Ikorodu");
        address.setStreetName("Ogbomosho Street");
        address.setStreetNumber("16");

        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername("essie29");
        registerUserRequest.setEmail("estheraiyeola@yahoo.com");
        registerUserRequest.setFirstName("Esther");
        registerUserRequest.setLastName("Aiyeola");
        registerUserRequest.setPassword("password");
        registerUserRequest.setAddress(address);
        registerUserRequest.setPhoneNumber("08138112782");
        RegisteredUserResponse response = userService.registerUser(registerUserRequest);
        assertThat(response.getStatus(), is("Successful"));

        AddToCartRequest cartRequest1 = new AddToCartRequest();
        cartRequest1.setProductName("Milk");
        cartRequest1.setQuantity(5L);
        cartRequest1.setUserId(response.getUserId());

        AddToCartRequest cartRequest2 = new AddToCartRequest();
        cartRequest2.setProductName("Chocolate");
        cartRequest2.setQuantity(2L);
        cartRequest2.setUserId(response.getUserId());

        AddToCartRequest cartRequest3 = new AddToCartRequest();
        cartRequest3.setProductName("Chocolate Cake");
        cartRequest3.setQuantity(5L);
        cartRequest3.setUserId(response.getUserId());

        List<AddToCartRequest> addToCartRequestList = List.of(cartRequest1, cartRequest2, cartRequest3);
        Cart cartResponse = userService.addProductToCart(addToCartRequestList, response.getUserId());
        assertThat(cartResponse.getOrderItems().size(), is(3));

        List<Cart> cartList = userService.removeProductFromCartBy("Milk");
        Cart firstCart = cartList.get(0);
        assertThat(firstCart.getOrderItems().size(), is(2));
    }

    @Test
    public void testThatUserCanCheckOrderHistory(){
        Address address = new Address();
        address.setCity("Lagos");
        address.setCountry("Nigeria");
        address.setPostalCode("100200");
        address.setLgaName("Ikorodu");
        address.setStreetName("Ogbomosho Street");
        address.setStreetNumber("16");

        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername("essie29");
        registerUserRequest.setEmail("estheraiyeola@yahoo.com");
        registerUserRequest.setFirstName("Esther");
        registerUserRequest.setLastName("Aiyeola");
        registerUserRequest.setPassword("password");
        registerUserRequest.setAddress(address);
        registerUserRequest.setPhoneNumber("08138112782");
        RegisteredUserResponse response = userService.registerUser(registerUserRequest);
        assertThat(response.getStatus(), is("Successful"));

        AddToCartRequest cartRequest1 = new AddToCartRequest();
        cartRequest1.setProductName("Milk");
        cartRequest1.setQuantity(5L);
        cartRequest1.setUserId(response.getUserId());

        AddToCartRequest cartRequest2 = new AddToCartRequest();
        cartRequest2.setProductName("Chocolate");
        cartRequest2.setQuantity(2L);
        cartRequest2.setUserId(response.getUserId());

        AddToCartRequest cartRequest3 = new AddToCartRequest();
        cartRequest3.setProductName("Chocolate Cake");
        cartRequest3.setQuantity(5L);
        cartRequest3.setUserId(response.getUserId());



        List<AddToCartRequest> addToCartRequestList = List.of(cartRequest1, cartRequest2, cartRequest3);
        Cart cartResponse = userService.addProductToCart(addToCartRequestList, response.getUserId());
        assertThat(cartResponse.getOrderItems().size(), is(3));

        Cart cartResponse2 = userService.addProductToCart(addToCartRequestList, response.getUserId());
        assertThat(cartResponse2.getOrderItems().size(), is(3));

        assertThat(orderHistoryService.findAll().size(), is(2));
    }


    @Test
    public void testThatA_User_CannotLeaveMoreThanOneReviewOn_A_ProductPerOrder(){
        Address address = new Address();
        address.setCity("Lagos");
        address.setCountry("Nigeria");
        address.setPostalCode("100200");
        address.setLgaName("Ikorodu");
        address.setStreetName("Ogbomosho Street");
        address.setStreetNumber("16");

        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername("essie29");
        registerUserRequest.setEmail("estheraiyeola@yahoo.com");
        registerUserRequest.setFirstName("Esther");
        registerUserRequest.setLastName("Aiyeola");
        registerUserRequest.setPassword("password");
        registerUserRequest.setAddress(address);
        registerUserRequest.setPhoneNumber("08138112782");
        RegisteredUserResponse response = userService.registerUser(registerUserRequest);
        assertThat(response.getStatus(), is("Successful"));

        Address address2 = new Address();
        address2.setCity("Ogun");
        address2.setCountry("Nigeria");
        address2.setPostalCode("100201");
        address2.setLgaName("Sagamu");
        address2.setStreetName("Makun");
        address2.setStreetNumber("16");

        RegisterUserRequest registerUserRequest2 = new RegisterUserRequest();
        registerUserRequest2.setUsername("tobi29");
        registerUserRequest2.setEmail("tobiaiyeola@yahoo.com");
        registerUserRequest2.setFirstName("Tobi");
        registerUserRequest2.setLastName("Aiyeola");
        registerUserRequest2.setPassword("password");
        registerUserRequest2.setAddress(address2);
        registerUserRequest2.setPhoneNumber("08138112782");
        RegisteredUserResponse response2 = userService.registerUser(registerUserRequest2);
        assertThat(response2.getStatus(), is("Successful"));

        AddToCartRequest cartRequest1 = new AddToCartRequest();
        cartRequest1.setProductName("Milk");
        cartRequest1.setQuantity(5L);
        cartRequest1.setUserId(response.getUserId());

        AddToCartRequest cartRequest2 = new AddToCartRequest();
        cartRequest2.setProductName("Chocolate");
        cartRequest2.setQuantity(2L);
        cartRequest2.setUserId(response.getUserId());

        AddToCartRequest cartRequest3 = new AddToCartRequest();
        cartRequest3.setProductName("Chocolate Cake");
        cartRequest3.setQuantity(5L);
        cartRequest3.setUserId(response.getUserId());

        List<AddToCartRequest> addToCartRequestList = List.of(cartRequest1, cartRequest2, cartRequest3);
        Cart cartResponse = userService.addProductToCart(addToCartRequestList, response.getUserId());
        assertThat(cartResponse.getOrderItems().size(), is(3));



        ProductReviewRequest productReviewRequest2 = new ProductReviewRequest();
        productReviewRequest2.setProductName("Chocolate");
        productReviewRequest2.setMessage("I love this product so much");
        productReviewRequest2.setRating(Rating.FIVE);
        ProductReviewResponse reviewResponse2 = userService.productReview(productReviewRequest2, response.getUserId(), cartResponse);
        assertThat(reviewResponse2.getMessage(), is("Review Added Successfully"));


        assertThrows(ExceededNumberOfReviewPerOrderException.class, ()->userService.productReview(productReviewRequest2, response.getUserId(), cartResponse));



    }




}