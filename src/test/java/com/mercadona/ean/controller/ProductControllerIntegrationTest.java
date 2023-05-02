package com.mercadona.ean.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// Import your custom classes
import com.mercadona.ean.model.Product;
import com.mercadona.ean.model.Supplier;
import com.mercadona.ean.model.Destination;
import com.mercadona.ean.service.EanService;
import com.mercadona.ean.service.SupplierService;
import com.mercadona.ean.service.DestinationService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private EanService eanService;

    @MockBean
    private SupplierService supplierService;

    @MockBean
    private DestinationService destinationService;

    @Test
    public void createProductTest() {
        // given
        Product product = new Product();
        product.setName("Test Product");
        product.setEan("1234567890123");

        Supplier supplier = new Supplier();
        supplier.setId(1L);

        Destination destination = new Destination();
        destination.setId(1L);

        product.setSupplier(supplier);
        product.setDestination(destination);

        // when
        ResponseEntity<Product> responseEntity = this.restTemplate
                .postForEntity("/api/products/createProduct", product, Product.class);

        // then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(product.getName(), responseEntity.getBody().getName());
        assertEquals(product.getEan(), responseEntity.getBody().getEan());
    }
}
