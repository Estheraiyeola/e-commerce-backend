package com.essies.ecommerce.service;

import com.essies.ecommerce.data.model.Inventory;
import com.essies.ecommerce.data.model.Order;
import com.essies.ecommerce.data.model.OrderStatus;
import com.essies.ecommerce.data.repository.AdminRepository;
import com.essies.ecommerce.dto.request.AddProductRequest;
import com.essies.ecommerce.dto.request.CreateCategoryRequest;
import com.essies.ecommerce.dto.request.RegisterAdminRequest;
import com.essies.ecommerce.dto.response.*;
import com.essies.ecommerce.exception.AdminAlreadyExistsException;
import com.essies.ecommerce.exception.CategoryAlreadyExistsException;
import com.essies.ecommerce.exception.ProductAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AdminServiceTest {
    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private  CategoryService categoryService;
    @Autowired
    private  ProductService productService;
    @Autowired
    private InventoryService inventoryService;

    @BeforeEach
    public void setUp(){
        adminRepository.deleteAll();
        categoryService.deleteAll();
        productService.deleteAll();
        inventoryService.deleteAll();
    }
    @Test
    public void testThatAdminCanRegister(){
        RegisterAdminRequest registerAdminRequest = new RegisterAdminRequest();
        registerAdminRequest.setUsername("essie.codes");
        registerAdminRequest.setEmail("estheraiyeola@yahoo.com");
        registerAdminRequest.setPassword("password");

        RegisterAdminResponse registerAdminResponse = adminService.registerAdmin(registerAdminRequest);
        assertThat(registerAdminResponse.getStatus(), is("Successful"));

    }

    @Test
    public void testThatUniqueAdminsCanRegister(){
        RegisterAdminRequest registerAdminRequest = new RegisterAdminRequest();
        registerAdminRequest.setUsername("essie.codes");
        registerAdminRequest.setEmail("estheraiyeola@yahoo.com");
        registerAdminRequest.setPassword("password");

        RegisterAdminResponse registerAdminResponse = adminService.registerAdmin(registerAdminRequest);
        assertThat(registerAdminResponse.getStatus(), is("Successful"));
        assertThrows(AdminAlreadyExistsException.class, ()-> adminService.registerAdmin(registerAdminRequest));

    }

    @Test
    public void testThatAdminCanCreateACategory(){
        RegisterAdminRequest registerAdminRequest = new RegisterAdminRequest();
        registerAdminRequest.setUsername("essie.codes");
        registerAdminRequest.setEmail("estheraiyeola@yahoo.com");
        registerAdminRequest.setPassword("password");

        RegisterAdminResponse registerAdminResponse = adminService.registerAdmin(registerAdminRequest);
        assertThat(registerAdminResponse.getStatus(), is("Successful"));

        CreateCategoryRequest categoryRequest = new CreateCategoryRequest();
        categoryRequest.setName("Food");
        categoryRequest.setDescription("This section contains food items");

        CreatedCategoryResponse response = adminService.createCategory(categoryRequest);
        assertThat(response.getStatus(), is("Successful"));
        assertThat(response.getName(), is("Food"));
        assertThat(response.getDescription(), is("This section contains food items"));


    }

    @Test
    public void testThatAdminCanCreateUniqueCategories(){
        RegisterAdminRequest registerAdminRequest = new RegisterAdminRequest();
        registerAdminRequest.setUsername("essie.codes");
        registerAdminRequest.setEmail("estheraiyeola@yahoo.com");
        registerAdminRequest.setPassword("password");

        RegisterAdminResponse registerAdminResponse = adminService.registerAdmin(registerAdminRequest);
        assertThat(registerAdminResponse.getStatus(), is("Successful"));

        CreateCategoryRequest categoryRequest = new CreateCategoryRequest();
        categoryRequest.setName("Food");
        categoryRequest.setDescription("This section contains food items");

        CreatedCategoryResponse response = adminService.createCategory(categoryRequest);
        assertThat(response.getStatus(), is("Successful"));
        assertThat(response.getName(), is("Food"));
        assertThat(response.getDescription(), is("This section contains food items"));


        CreateCategoryRequest categoryRequest1 = new CreateCategoryRequest();
        categoryRequest1.setName("Clothing");
        categoryRequest1.setDescription("This section contains clothes and accessories");

        CreatedCategoryResponse response1 = adminService.createCategory(categoryRequest1);
        assertThat(response1.getStatus(), is("Successful"));
        assertThat(response1.getName(), is("Clothing"));
        assertThat(response1.getDescription(), is("This section contains clothes and accessories"));


       assertThrows(CategoryAlreadyExistsException.class, ()->adminService.createCategory(categoryRequest));


    }

    @Test
    public void testThatAdminCanAddProducts(){
        RegisterAdminRequest registerAdminRequest = new RegisterAdminRequest();
        registerAdminRequest.setUsername("essie.codes");
        registerAdminRequest.setEmail("estheraiyeola@yahoo.com");
        registerAdminRequest.setPassword("password");

        RegisterAdminResponse registerAdminResponse = adminService.registerAdmin(registerAdminRequest);
        assertThat(registerAdminResponse.getStatus(), is("Successful"));

        CreateCategoryRequest categoryRequest = new CreateCategoryRequest();
        categoryRequest.setName("Food");
        categoryRequest.setDescription("This section contains food items");

        CreatedCategoryResponse response = adminService.createCategory(categoryRequest);
        assertThat(response.getStatus(), is("Successful"));
        assertThat(response.getName(), is("Food"));
        assertThat(response.getDescription(), is("This section contains food items"));


        CreateCategoryRequest categoryRequest1 = new CreateCategoryRequest();
        categoryRequest1.setName("Clothing");
        categoryRequest1.setDescription("This section contains clothes and accessories");

        CreatedCategoryResponse response1 = adminService.createCategory(categoryRequest1);
        assertThat(response1.getStatus(), is("Successful"));
        assertThat(response1.getName(), is("Clothing"));
        assertThat(response1.getDescription(), is("This section contains clothes and accessories"));

        AddProductRequest addProductRequest = new AddProductRequest();
        addProductRequest.setCategoryId(response.getId());
        addProductRequest.setName("Milk");
        addProductRequest.setDescription("Fat free milk");
        addProductRequest.setQuantity(12L);
        addProductRequest.setPrice(BigDecimal.valueOf(12.50));
        addProductRequest.setImageUrl("https://images.unsplash.com/photo-1550583724-b2692b85b150?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8bWlsa3xlbnwwfHwwfHx8MA%3D%3D");
        AddedProductResponse addedProductResponse = adminService.addProduct(addProductRequest);
         assertThat(addedProductResponse.getStatus(), is("Successful!!"));
    }

    @Test
    public void testThatAdminCanAddUniqueProducts(){
        RegisterAdminRequest registerAdminRequest = new RegisterAdminRequest();
        registerAdminRequest.setUsername("essie.codes");
        registerAdminRequest.setEmail("estheraiyeola@yahoo.com");
        registerAdminRequest.setPassword("password");

        RegisterAdminResponse registerAdminResponse = adminService.registerAdmin(registerAdminRequest);
        assertThat(registerAdminResponse.getStatus(), is("Successful"));

        CreateCategoryRequest categoryRequest = new CreateCategoryRequest();
        categoryRequest.setName("Food");
        categoryRequest.setDescription("This section contains food items");

        CreatedCategoryResponse response = adminService.createCategory(categoryRequest);
        assertThat(response.getStatus(), is("Successful"));
        assertThat(response.getName(), is("Food"));
        assertThat(response.getDescription(), is("This section contains food items"));


        CreateCategoryRequest categoryRequest1 = new CreateCategoryRequest();
        categoryRequest1.setName("Clothing");
        categoryRequest1.setDescription("This section contains clothes and accessories");

        CreatedCategoryResponse response1 = adminService.createCategory(categoryRequest1);
        assertThat(response1.getStatus(), is("Successful"));
        assertThat(response1.getName(), is("Clothing"));
        assertThat(response1.getDescription(), is("This section contains clothes and accessories"));

        AddProductRequest addProductRequest = new AddProductRequest();
        addProductRequest.setCategoryId(response.getId());
        addProductRequest.setName("Milk");
        addProductRequest.setDescription("Fat free milk");
        addProductRequest.setQuantity(12L);
        addProductRequest.setPrice(BigDecimal.valueOf(12.50));
        addProductRequest.setImageUrl("https://images.unsplash.com/photo-1550583724-b2692b85b150?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8bWlsa3xlbnwwfHwwfHx8MA%3D%3D");
        AddedProductResponse addedProductResponse = adminService.addProduct(addProductRequest);
        assertThat(addedProductResponse.getStatus(), is("Successful!!"));

        assertThrows(ProductAlreadyExistsException.class, ()->adminService.addProduct(addProductRequest));
    }

    @Test
    public void testThatAdminCanGetAll_ProductsBasedOn_TheirCategories(){
        RegisterAdminRequest registerAdminRequest = new RegisterAdminRequest();
        registerAdminRequest.setUsername("essie.codes");
        registerAdminRequest.setEmail("estheraiyeola@yahoo.com");
        registerAdminRequest.setPassword("password");

        RegisterAdminResponse registerAdminResponse = adminService.registerAdmin(registerAdminRequest);
        assertThat(registerAdminResponse.getStatus(), is("Successful"));

        CreateCategoryRequest categoryRequest = new CreateCategoryRequest();
        categoryRequest.setName("Food");
        categoryRequest.setDescription("This section contains food items");

        CreatedCategoryResponse response = adminService.createCategory(categoryRequest);
        assertThat(response.getStatus(), is("Successful"));
        assertThat(response.getName(), is("Food"));
        assertThat(response.getDescription(), is("This section contains food items"));


        CreateCategoryRequest categoryRequest1 = new CreateCategoryRequest();
        categoryRequest1.setName("Clothing");
        categoryRequest1.setDescription("This section contains clothes and accessories");

        CreatedCategoryResponse response1 = adminService.createCategory(categoryRequest1);
        assertThat(response1.getStatus(), is("Successful"));
        assertThat(response1.getName(), is("Clothing"));
        assertThat(response1.getDescription(), is("This section contains clothes and accessories"));

        AddProductRequest addProductRequest = new AddProductRequest();
        addProductRequest.setCategoryId(response.getId());
        addProductRequest.setName("Milk");
        addProductRequest.setDescription("Fat free milk");
        addProductRequest.setQuantity(12L);
        addProductRequest.setPrice(BigDecimal.valueOf(12.50));
        addProductRequest.setImageUrl("https://images.unsplash.com/photo-1550583724-b2692b85b150?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8bWlsa3xlbnwwfHwwfHx8MA%3D%3D");
        AddedProductResponse addedProductResponse = adminService.addProduct(addProductRequest);
        assertThat(addedProductResponse.getStatus(), is("Successful!!"));


        AddProductRequest addProductRequest2 = new AddProductRequest();
        addProductRequest2.setCategoryId(response.getId());
        addProductRequest2.setName("Chocolate");
        addProductRequest2.setDescription("Dark chocolate");
        addProductRequest2.setQuantity(15L);
        addProductRequest2.setPrice(BigDecimal.valueOf(550.50));
        addProductRequest2.setImageUrl("https://media.istockphoto.com/id/1494766888/photo/chocolate-truffles.webp?b=1&s=170667a&w=0&k=20&c=O99kTKeau2QkmaboKtzeXfqputZikLeqgOdkDPs3sfw=");
        AddedProductResponse addedProductResponse2 = adminService.addProduct(addProductRequest2);
        assertThat(addedProductResponse2.getStatus(), is("Successful!!"));

        AddProductRequest addProductRequest3 = new AddProductRequest();
        addProductRequest3.setCategoryId(response.getId());
        addProductRequest3.setName("Chocolate Cake");
        addProductRequest3.setDescription("Chocolate Flavoured Cake");
        addProductRequest3.setQuantity(15L);
        addProductRequest3.setPrice(BigDecimal.valueOf(550.50));
        addProductRequest3.setImageUrl("https://media.istockphoto.com/id/1482639594/photo/a-slice-of-chocolate-cake-on-white-background.webp?b=1&s=170667a&w=0&k=20&c=GL8eYavvFe-yO2cJEOGfRsAOHZwuWfFF-z8EHyO8aXk=");
        AddedProductResponse addedProductResponse3 = adminService.addProduct(addProductRequest3);
        assertThat(addedProductResponse3.getStatus(), is("Successful!!"));


        AllProductsInA_CategoryResponse categoryResponse = adminService.getAllProductsInACategory("Food");
        assertThat(categoryResponse.getTotal(), is(3L));

    }
    @Test
    public void testThatAdminCanGet_A_Product_In_Inventory(){
        RegisterAdminRequest registerAdminRequest = new RegisterAdminRequest();
        registerAdminRequest.setUsername("essie.codes");
        registerAdminRequest.setEmail("estheraiyeola@yahoo.com");
        registerAdminRequest.setPassword("password");

        RegisterAdminResponse registerAdminResponse = adminService.registerAdmin(registerAdminRequest);
        assertThat(registerAdminResponse.getStatus(), is("Successful"));

        CreateCategoryRequest categoryRequest = new CreateCategoryRequest();
        categoryRequest.setName("Food");
        categoryRequest.setDescription("This section contains food items");

        CreatedCategoryResponse response = adminService.createCategory(categoryRequest);
        assertThat(response.getStatus(), is("Successful"));
        assertThat(response.getName(), is("Food"));
        assertThat(response.getDescription(), is("This section contains food items"));


        CreateCategoryRequest categoryRequest1 = new CreateCategoryRequest();
        categoryRequest1.setName("Clothing");
        categoryRequest1.setDescription("This section contains clothes and accessories");

        CreatedCategoryResponse response1 = adminService.createCategory(categoryRequest1);
        assertThat(response1.getStatus(), is("Successful"));
        assertThat(response1.getName(), is("Clothing"));
        assertThat(response1.getDescription(), is("This section contains clothes and accessories"));

        AddProductRequest addProductRequest = new AddProductRequest();
        addProductRequest.setCategoryId(response.getId());
        addProductRequest.setName("Milk");
        addProductRequest.setDescription("Fat free milk");
        addProductRequest.setQuantity(12L);
        addProductRequest.setPrice(BigDecimal.valueOf(12.50));
        addProductRequest.setImageUrl("https://images.unsplash.com/photo-1550583724-b2692b85b150?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8bWlsa3xlbnwwfHwwfHx8MA%3D%3D");
        AddedProductResponse addedProductResponse = adminService.addProduct(addProductRequest);
        assertThat(addedProductResponse.getStatus(), is("Successful!!"));


        AddProductRequest addProductRequest2 = new AddProductRequest();
        addProductRequest2.setCategoryId(response.getId());
        addProductRequest2.setName("Chocolate");
        addProductRequest2.setDescription("Dark chocolate");
        addProductRequest2.setQuantity(15L);
        addProductRequest2.setPrice(BigDecimal.valueOf(550.50));
        addProductRequest2.setImageUrl("https://media.istockphoto.com/id/1494766888/photo/chocolate-truffles.webp?b=1&s=170667a&w=0&k=20&c=O99kTKeau2QkmaboKtzeXfqputZikLeqgOdkDPs3sfw=");
        AddedProductResponse addedProductResponse2 = adminService.addProduct(addProductRequest2);
        assertThat(addedProductResponse2.getStatus(), is("Successful!!"));

        AddProductRequest addProductRequest3 = new AddProductRequest();
        addProductRequest3.setCategoryId(response.getId());
        addProductRequest3.setName("Chocolate Cake");
        addProductRequest3.setDescription("Chocolate Flavoured Cake");
        addProductRequest3.setQuantity(15L);
        addProductRequest3.setPrice(BigDecimal.valueOf(550.50));
        addProductRequest3.setImageUrl("https://media.istockphoto.com/id/1482639594/photo/a-slice-of-chocolate-cake-on-white-background.webp?b=1&s=170667a&w=0&k=20&c=GL8eYavvFe-yO2cJEOGfRsAOHZwuWfFF-z8EHyO8aXk=");
        AddedProductResponse addedProductResponse3 = adminService.addProduct(addProductRequest3);
        assertThat(addedProductResponse3.getStatus(), is("Successful!!"));

        AddProductRequest addProductRequest4 = new AddProductRequest();
        addProductRequest4.setCategoryId(response1.getId());
        addProductRequest4.setName("Men's Belt");
        addProductRequest4.setDescription("Men's Quality Leather Belt");
        addProductRequest4.setQuantity(20L);
        addProductRequest4.setPrice(BigDecimal.valueOf(1500.65));
        addProductRequest4.setImageUrl("https://media.istockphoto.com/id/1465195036/photo/buttoned-black-male-leather-belt-worn-on-jeans.webp?b=1&s=170667a&w=0&k=20&c=kMkLh5vEojnstRZBmubG2vRmNcPPK-bO4v8nw80ReBw=");
        AddedProductResponse addedProductResponse4 = adminService.addProduct(addProductRequest4);
        assertThat(addedProductResponse4.getStatus(), is("Successful!!"));

        Inventory productInInventoryResponse = adminService.findByProductId(addedProductResponse4.getId());
        assertThat(productInInventoryResponse.getProductId(), is(addedProductResponse4.getId()));

    }

    @Test
    public void testThatAdminCanGet_All_Items_In_Inventory(){
        RegisterAdminRequest registerAdminRequest = new RegisterAdminRequest();
        registerAdminRequest.setUsername("essie.codes");
        registerAdminRequest.setEmail("estheraiyeola@yahoo.com");
        registerAdminRequest.setPassword("password");

        RegisterAdminResponse registerAdminResponse = adminService.registerAdmin(registerAdminRequest);
        assertThat(registerAdminResponse.getStatus(), is("Successful"));

        CreateCategoryRequest categoryRequest = new CreateCategoryRequest();
        categoryRequest.setName("Food");
        categoryRequest.setDescription("This section contains food items");

        CreatedCategoryResponse response = adminService.createCategory(categoryRequest);
        assertThat(response.getStatus(), is("Successful"));
        assertThat(response.getName(), is("Food"));
        assertThat(response.getDescription(), is("This section contains food items"));


        CreateCategoryRequest categoryRequest1 = new CreateCategoryRequest();
        categoryRequest1.setName("Clothing");
        categoryRequest1.setDescription("This section contains clothes and accessories");

        CreatedCategoryResponse response1 = adminService.createCategory(categoryRequest1);
        assertThat(response1.getStatus(), is("Successful"));
        assertThat(response1.getName(), is("Clothing"));
        assertThat(response1.getDescription(), is("This section contains clothes and accessories"));

        AddProductRequest addProductRequest = new AddProductRequest();
        addProductRequest.setCategoryId(response.getId());
        addProductRequest.setName("Milk");
        addProductRequest.setDescription("Fat free milk");
        addProductRequest.setQuantity(12L);
        addProductRequest.setPrice(BigDecimal.valueOf(12.50));
        addProductRequest.setImageUrl("https://images.unsplash.com/photo-1550583724-b2692b85b150?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8bWlsa3xlbnwwfHwwfHx8MA%3D%3D");
        AddedProductResponse addedProductResponse = adminService.addProduct(addProductRequest);
        assertThat(addedProductResponse.getStatus(), is("Successful!!"));


        AddProductRequest addProductRequest2 = new AddProductRequest();
        addProductRequest2.setCategoryId(response.getId());
        addProductRequest2.setName("Chocolate");
        addProductRequest2.setDescription("Dark chocolate");
        addProductRequest2.setQuantity(15L);
        addProductRequest2.setPrice(BigDecimal.valueOf(550.50));
        addProductRequest2.setImageUrl("https://media.istockphoto.com/id/1494766888/photo/chocolate-truffles.webp?b=1&s=170667a&w=0&k=20&c=O99kTKeau2QkmaboKtzeXfqputZikLeqgOdkDPs3sfw=");
        AddedProductResponse addedProductResponse2 = adminService.addProduct(addProductRequest2);
        assertThat(addedProductResponse2.getStatus(), is("Successful!!"));

        AddProductRequest addProductRequest3 = new AddProductRequest();
        addProductRequest3.setCategoryId(response.getId());
        addProductRequest3.setName("Chocolate Cake");
        addProductRequest3.setDescription("Chocolate Flavoured Cake");
        addProductRequest3.setQuantity(15L);
        addProductRequest3.setPrice(BigDecimal.valueOf(550.50));
        addProductRequest3.setImageUrl("https://media.istockphoto.com/id/1482639594/photo/a-slice-of-chocolate-cake-on-white-background.webp?b=1&s=170667a&w=0&k=20&c=GL8eYavvFe-yO2cJEOGfRsAOHZwuWfFF-z8EHyO8aXk=");
        AddedProductResponse addedProductResponse3 = adminService.addProduct(addProductRequest3);
        assertThat(addedProductResponse3.getStatus(), is("Successful!!"));

        AddProductRequest addProductRequest4 = new AddProductRequest();
        addProductRequest4.setCategoryId(response1.getId());
        addProductRequest4.setName("Men's Belt");
        addProductRequest4.setDescription("Men's Quality Leather Belt");
        addProductRequest4.setQuantity(20L);
        addProductRequest4.setPrice(BigDecimal.valueOf(1500.65));
        addProductRequest4.setImageUrl("https://media.istockphoto.com/id/1465195036/photo/buttoned-black-male-leather-belt-worn-on-jeans.webp?b=1&s=170667a&w=0&k=20&c=kMkLh5vEojnstRZBmubG2vRmNcPPK-bO4v8nw80ReBw=");
        AddedProductResponse addedProductResponse4 = adminService.addProduct(addProductRequest4);
        assertThat(addedProductResponse4.getStatus(), is("Successful!!"));

        List<Inventory> productsInInventory = adminService.getInventory();
        assertThat(productsInInventory.size(), is(4));

    }

    @Test
    public void testThatAdminCanRemoveProducts(){
        RegisterAdminRequest registerAdminRequest = new RegisterAdminRequest();
        registerAdminRequest.setUsername("essie.codes");
        registerAdminRequest.setEmail("estheraiyeola@yahoo.com");
        registerAdminRequest.setPassword("password");

        RegisterAdminResponse registerAdminResponse = adminService.registerAdmin(registerAdminRequest);
        assertThat(registerAdminResponse.getStatus(), is("Successful"));

        CreateCategoryRequest categoryRequest = new CreateCategoryRequest();
        categoryRequest.setName("Food");
        categoryRequest.setDescription("This section contains food items");

        CreatedCategoryResponse response = adminService.createCategory(categoryRequest);
        assertThat(response.getStatus(), is("Successful"));
        assertThat(response.getName(), is("Food"));
        assertThat(response.getDescription(), is("This section contains food items"));


        CreateCategoryRequest categoryRequest1 = new CreateCategoryRequest();
        categoryRequest1.setName("Clothing");
        categoryRequest1.setDescription("This section contains clothes and accessories");

        CreatedCategoryResponse response1 = adminService.createCategory(categoryRequest1);
        assertThat(response1.getStatus(), is("Successful"));
        assertThat(response1.getName(), is("Clothing"));
        assertThat(response1.getDescription(), is("This section contains clothes and accessories"));

        AddProductRequest addProductRequest = new AddProductRequest();
        addProductRequest.setCategoryId(response.getId());
        addProductRequest.setName("Milk");
        addProductRequest.setDescription("Fat free milk");
        addProductRequest.setQuantity(12L);
        addProductRequest.setPrice(BigDecimal.valueOf(12.50));
        addProductRequest.setImageUrl("https://images.unsplash.com/photo-1550583724-b2692b85b150?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8bWlsa3xlbnwwfHwwfHx8MA%3D%3D");
        AddedProductResponse addedProductResponse = adminService.addProduct(addProductRequest);
        assertThat(addedProductResponse.getStatus(), is("Successful!!"));


        AddProductRequest addProductRequest2 = new AddProductRequest();
        addProductRequest2.setCategoryId(response.getId());
        addProductRequest2.setName("Chocolate");
        addProductRequest2.setDescription("Dark chocolate");
        addProductRequest2.setQuantity(15L);
        addProductRequest2.setPrice(BigDecimal.valueOf(550.50));
        addProductRequest2.setImageUrl("https://media.istockphoto.com/id/1494766888/photo/chocolate-truffles.webp?b=1&s=170667a&w=0&k=20&c=O99kTKeau2QkmaboKtzeXfqputZikLeqgOdkDPs3sfw=");
        AddedProductResponse addedProductResponse2 = adminService.addProduct(addProductRequest2);
        assertThat(addedProductResponse2.getStatus(), is("Successful!!"));

        AddProductRequest addProductRequest3 = new AddProductRequest();
        addProductRequest3.setCategoryId(response.getId());
        addProductRequest3.setName("Chocolate Cake");
        addProductRequest3.setDescription("Chocolate Flavoured Cake");
        addProductRequest3.setQuantity(15L);
        addProductRequest3.setPrice(BigDecimal.valueOf(550.50));
        addProductRequest3.setImageUrl("https://media.istockphoto.com/id/1482639594/photo/a-slice-of-chocolate-cake-on-white-background.webp?b=1&s=170667a&w=0&k=20&c=GL8eYavvFe-yO2cJEOGfRsAOHZwuWfFF-z8EHyO8aXk=");
        AddedProductResponse addedProductResponse3 = adminService.addProduct(addProductRequest3);
        assertThat(addedProductResponse3.getStatus(), is("Successful!!"));

        AddProductRequest addProductRequest4 = new AddProductRequest();
        addProductRequest4.setCategoryId(response1.getId());
        addProductRequest4.setName("Men's Belt");
        addProductRequest4.setDescription("Men's Quality Leather Belt");
        addProductRequest4.setQuantity(20L);
        addProductRequest4.setPrice(BigDecimal.valueOf(1500.65));
        addProductRequest4.setImageUrl("https://media.istockphoto.com/id/1465195036/photo/buttoned-black-male-leather-belt-worn-on-jeans.webp?b=1&s=170667a&w=0&k=20&c=kMkLh5vEojnstRZBmubG2vRmNcPPK-bO4v8nw80ReBw=");
        AddedProductResponse addedProductResponse4 = adminService.addProduct(addProductRequest4);
        assertThat(addedProductResponse4.getStatus(), is("Successful!!"));

        AddProductRequest addProductRequest5 = new AddProductRequest();
        addProductRequest5.setCategoryId(response1.getId());
        addProductRequest5.setName("Sugar");
        addProductRequest5.setDescription("Healthy Sugar");
        addProductRequest5.setQuantity(50L);
        addProductRequest5.setPrice(BigDecimal.valueOf(250.65));
        addProductRequest5.setImageUrl("https://media.istockphoto.com/id/481580726/photo/sugar-textured.webp?b=1&s=170667a&w=0&k=20&c=mDWZn8MFqQzJ8CtSyTlpDoImoYROUxT3c30oYa9qhjI=");
        AddedProductResponse addedProductResponse5 = adminService.addProduct(addProductRequest5);
        assertThat(addedProductResponse5.getStatus(), is("Successful!!"));

       adminService.removeProduct(addedProductResponse3.getName());
       assertNull(adminService.findProduct(addedProductResponse3.getName()));

    }

    @Test
    public void testThatAdminCan_CheckList_Of_Orders(){
        RegisterAdminRequest registerAdminRequest = new RegisterAdminRequest();
        registerAdminRequest.setUsername("essie.codes");
        registerAdminRequest.setEmail("estheraiyeola@yahoo.com");
        registerAdminRequest.setPassword("password");

        RegisterAdminResponse registerAdminResponse = adminService.registerAdmin(registerAdminRequest);
        assertThat(registerAdminResponse.getStatus(), is("Successful"));

        CreateCategoryRequest categoryRequest = new CreateCategoryRequest();
        categoryRequest.setName("Food");
        categoryRequest.setDescription("This section contains food items");

        CreatedCategoryResponse response = adminService.createCategory(categoryRequest);
        assertThat(response.getStatus(), is("Successful"));
        assertThat(response.getName(), is("Food"));
        assertThat(response.getDescription(), is("This section contains food items"));


        CreateCategoryRequest categoryRequest1 = new CreateCategoryRequest();
        categoryRequest1.setName("Clothing");
        categoryRequest1.setDescription("This section contains clothes and accessories");

        CreatedCategoryResponse response1 = adminService.createCategory(categoryRequest1);
        assertThat(response1.getStatus(), is("Successful"));
        assertThat(response1.getName(), is("Clothing"));
        assertThat(response1.getDescription(), is("This section contains clothes and accessories"));

        AddProductRequest addProductRequest = new AddProductRequest();
        addProductRequest.setCategoryId(response.getId());
        addProductRequest.setName("Milk");
        addProductRequest.setDescription("Fat free milk");
        addProductRequest.setQuantity(12L);
        addProductRequest.setPrice(BigDecimal.valueOf(12.50));
        addProductRequest.setImageUrl("https://images.unsplash.com/photo-1550583724-b2692b85b150?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8bWlsa3xlbnwwfHwwfHx8MA%3D%3D");
        AddedProductResponse addedProductResponse = adminService.addProduct(addProductRequest);
        assertThat(addedProductResponse.getStatus(), is("Successful!!"));


        AddProductRequest addProductRequest2 = new AddProductRequest();
        addProductRequest2.setCategoryId(response.getId());
        addProductRequest2.setName("Chocolate");
        addProductRequest2.setDescription("Dark chocolate");
        addProductRequest2.setQuantity(15L);
        addProductRequest2.setPrice(BigDecimal.valueOf(550.50));
        addProductRequest2.setImageUrl("https://media.istockphoto.com/id/1494766888/photo/chocolate-truffles.webp?b=1&s=170667a&w=0&k=20&c=O99kTKeau2QkmaboKtzeXfqputZikLeqgOdkDPs3sfw=");
        AddedProductResponse addedProductResponse2 = adminService.addProduct(addProductRequest2);
        assertThat(addedProductResponse2.getStatus(), is("Successful!!"));

        AddProductRequest addProductRequest3 = new AddProductRequest();
        addProductRequest3.setCategoryId(response.getId());
        addProductRequest3.setName("Chocolate Cake");
        addProductRequest3.setDescription("Chocolate Flavoured Cake");
        addProductRequest3.setQuantity(15L);
        addProductRequest3.setPrice(BigDecimal.valueOf(550.50));
        addProductRequest3.setImageUrl("https://media.istockphoto.com/id/1482639594/photo/a-slice-of-chocolate-cake-on-white-background.webp?b=1&s=170667a&w=0&k=20&c=GL8eYavvFe-yO2cJEOGfRsAOHZwuWfFF-z8EHyO8aXk=");
        AddedProductResponse addedProductResponse3 = adminService.addProduct(addProductRequest3);
        assertThat(addedProductResponse3.getStatus(), is("Successful!!"));

        AddProductRequest addProductRequest4 = new AddProductRequest();
        addProductRequest4.setCategoryId(response1.getId());
        addProductRequest4.setName("Men's Belt");
        addProductRequest4.setDescription("Men's Quality Leather Belt");
        addProductRequest4.setQuantity(20L);
        addProductRequest4.setPrice(BigDecimal.valueOf(1500.65));
        addProductRequest4.setImageUrl("https://media.istockphoto.com/id/1465195036/photo/buttoned-black-male-leather-belt-worn-on-jeans.webp?b=1&s=170667a&w=0&k=20&c=kMkLh5vEojnstRZBmubG2vRmNcPPK-bO4v8nw80ReBw=");
        AddedProductResponse addedProductResponse4 = adminService.addProduct(addProductRequest4);
        assertThat(addedProductResponse4.getStatus(), is("Successful!!"));

        AddProductRequest addProductRequest5 = new AddProductRequest();
        addProductRequest5.setCategoryId(response1.getId());
        addProductRequest5.setName("Sugar");
        addProductRequest5.setDescription("Healthy Sugar");
        addProductRequest5.setQuantity(50L);
        addProductRequest5.setPrice(BigDecimal.valueOf(250.65));
        addProductRequest5.setImageUrl("https://media.istockphoto.com/id/481580726/photo/sugar-textured.webp?b=1&s=170667a&w=0&k=20&c=mDWZn8MFqQzJ8CtSyTlpDoImoYROUxT3c30oYa9qhjI=");
        AddedProductResponse addedProductResponse5 = adminService.addProduct(addProductRequest5);
        assertThat(addedProductResponse5.getStatus(), is("Successful!!"));

       List<Order> orderList = adminService.checkListOfOrders();
       assertThat(orderList.size(), is(1));


    }

    @Test
    public void testThatAdminCan_Process_Orders(){
        RegisterAdminRequest registerAdminRequest = new RegisterAdminRequest();
        registerAdminRequest.setUsername("essie.codes");
        registerAdminRequest.setEmail("estheraiyeola@yahoo.com");
        registerAdminRequest.setPassword("password");

        RegisterAdminResponse registerAdminResponse = adminService.registerAdmin(registerAdminRequest);
        assertThat(registerAdminResponse.getStatus(), is("Successful"));

        CreateCategoryRequest categoryRequest = new CreateCategoryRequest();
        categoryRequest.setName("Food");
        categoryRequest.setDescription("This section contains food items");

        CreatedCategoryResponse response = adminService.createCategory(categoryRequest);
        assertThat(response.getStatus(), is("Successful"));
        assertThat(response.getName(), is("Food"));
        assertThat(response.getDescription(), is("This section contains food items"));


        CreateCategoryRequest categoryRequest1 = new CreateCategoryRequest();
        categoryRequest1.setName("Clothing");
        categoryRequest1.setDescription("This section contains clothes and accessories");

        CreatedCategoryResponse response1 = adminService.createCategory(categoryRequest1);
        assertThat(response1.getStatus(), is("Successful"));
        assertThat(response1.getName(), is("Clothing"));
        assertThat(response1.getDescription(), is("This section contains clothes and accessories"));

        AddProductRequest addProductRequest = new AddProductRequest();
        addProductRequest.setCategoryId(response.getId());
        addProductRequest.setName("Milk");
        addProductRequest.setDescription("Fat free milk");
        addProductRequest.setQuantity(12L);
        addProductRequest.setPrice(BigDecimal.valueOf(12.50));
        addProductRequest.setImageUrl("https://images.unsplash.com/photo-1550583724-b2692b85b150?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8bWlsa3xlbnwwfHwwfHx8MA%3D%3D");
        AddedProductResponse addedProductResponse = adminService.addProduct(addProductRequest);
        assertThat(addedProductResponse.getStatus(), is("Successful!!"));


        AddProductRequest addProductRequest2 = new AddProductRequest();
        addProductRequest2.setCategoryId(response.getId());
        addProductRequest2.setName("Chocolate");
        addProductRequest2.setDescription("Dark chocolate");
        addProductRequest2.setQuantity(15L);
        addProductRequest2.setPrice(BigDecimal.valueOf(550.50));
        addProductRequest2.setImageUrl("https://media.istockphoto.com/id/1494766888/photo/chocolate-truffles.webp?b=1&s=170667a&w=0&k=20&c=O99kTKeau2QkmaboKtzeXfqputZikLeqgOdkDPs3sfw=");
        AddedProductResponse addedProductResponse2 = adminService.addProduct(addProductRequest2);
        assertThat(addedProductResponse2.getStatus(), is("Successful!!"));

        AddProductRequest addProductRequest3 = new AddProductRequest();
        addProductRequest3.setCategoryId(response.getId());
        addProductRequest3.setName("Chocolate Cake");
        addProductRequest3.setDescription("Chocolate Flavoured Cake");
        addProductRequest3.setQuantity(15L);
        addProductRequest3.setPrice(BigDecimal.valueOf(550.50));
        addProductRequest3.setImageUrl("https://media.istockphoto.com/id/1482639594/photo/a-slice-of-chocolate-cake-on-white-background.webp?b=1&s=170667a&w=0&k=20&c=GL8eYavvFe-yO2cJEOGfRsAOHZwuWfFF-z8EHyO8aXk=");
        AddedProductResponse addedProductResponse3 = adminService.addProduct(addProductRequest3);
        assertThat(addedProductResponse3.getStatus(), is("Successful!!"));

        AddProductRequest addProductRequest4 = new AddProductRequest();
        addProductRequest4.setCategoryId(response1.getId());
        addProductRequest4.setName("Men's Belt");
        addProductRequest4.setDescription("Men's Quality Leather Belt");
        addProductRequest4.setQuantity(20L);
        addProductRequest4.setPrice(BigDecimal.valueOf(1500.65));
        addProductRequest4.setImageUrl("https://media.istockphoto.com/id/1465195036/photo/buttoned-black-male-leather-belt-worn-on-jeans.webp?b=1&s=170667a&w=0&k=20&c=kMkLh5vEojnstRZBmubG2vRmNcPPK-bO4v8nw80ReBw=");
        AddedProductResponse addedProductResponse4 = adminService.addProduct(addProductRequest4);
        assertThat(addedProductResponse4.getStatus(), is("Successful!!"));

        AddProductRequest addProductRequest5 = new AddProductRequest();
        addProductRequest5.setCategoryId(response1.getId());
        addProductRequest5.setName("Sugar");
        addProductRequest5.setDescription("Healthy Sugar");
        addProductRequest5.setQuantity(50L);
        addProductRequest5.setPrice(BigDecimal.valueOf(250.65));
        addProductRequest5.setImageUrl("https://media.istockphoto.com/id/481580726/photo/sugar-textured.webp?b=1&s=170667a&w=0&k=20&c=mDWZn8MFqQzJ8CtSyTlpDoImoYROUxT3c30oYa9qhjI=");
        AddedProductResponse addedProductResponse5 = adminService.addProduct(addProductRequest5);
        assertThat(addedProductResponse5.getStatus(), is("Successful!!"));

        List<Order> orderList = adminService.checkListOfOrders();
        assertThat(orderList.size(), is(1));
        ProcessOrderResponse processOrderResponse = adminService.processOrder(orderList.get(0).getId());
        assertThat(orderList.get(0).getStatus(), is(OrderStatus.COMPLETED_ADMIN));

    }



}