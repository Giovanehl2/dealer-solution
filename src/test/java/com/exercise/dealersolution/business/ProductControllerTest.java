package com.exercise.dealersolution.business;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import com.exercise.dealersolution.repository.DbProduct;
import com.exercise.dealersolution.repository.Product;

import java.time.LocalDate;
import java.time.Month;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private DbProduct productRepository;

    private ProductController productController;

    @BeforeEach
    void setUp() {
        productController = new ProductController(new DbProduct());
    }
    @Test
    void shouldGetAllProducts() {
        final Set<Product> products = new HashSet<>();

        products.add(new Product(1,"SUV", 1, Double.parseDouble("120000.00"), 100,"31/12/2022"));
        products.add(new Product(2,"Sedan", 1,Double.parseDouble( "100000.00"), 100, "20/11/2022"));
        products.add(new Product(3,"Hatch1", 0,Double.parseDouble( "40000.00"), 100, "31/12/2099"));
        products.add(new Product(4,"Hatch2", 1, Double.parseDouble( "50000.00"), 0,"10/05/2022"));
        products.add(new Product(5,"Sport", 2, Double.parseDouble("220000.00"), 100,"03/04/2023"));
        products.add(new Product(6,"Truck", 1, Double.parseDouble( "250000.00"), 100, "01/02/2024"));
        products.add(new Product(7,"Eletric", 2,Double.parseDouble( "300000.00"), 100, "31/06/2025"));
        products.add(new Product(8,"Autonomos", 0, Double.parseDouble( "520000.00"), 100, "12/12/2022"));

        given(productRepository.todos()).willReturn(products);

        final Set<Product> retrievedObjects = productController.retrieveAll();
        assertThat(products, containsInAnyOrder(retrievedObjects.toArray()));
  }

    @Test
    void shouldGetAllAvailableProducts() {
        final Set<Product> expected = new HashSet<>();

        expected.add(new Product(1, "SUV", 1, Double.parseDouble("120000.00"), 100, "31/12/2022"));
        expected.add(new Product(2, "Sedan", 1, Double.parseDouble("100000.00"), 100, "20/11/2022"));

        final Set<Product> products = new HashSet<>();

        products.add(new Product(1, "SUV", 1, Double.parseDouble("120000.00"), 100, "31/12/2022"));
        products.add(new Product(2, "Sedan", 1, Double.parseDouble("100000.00"), 100, "20/11/2022"));

        given(productRepository.getAvailableProducts()).willReturn(products);

        final Set<Product> availableProducts = productController.getAll();

        assertTrue(!availableProducts.isEmpty());
    }

    @Test
    void shouldRetrieveDeadline() {
        LocalDate expectedDeadline = LocalDate.of(2022, Month.DECEMBER, 31);
        final Set<Product> products = new HashSet<>();

        products.add(new Product(1, "SUV", 1, Double.parseDouble("120000.00"), 100, "31/12/2022"));

        given(productRepository.retrieveUnavailable()).willReturn(products);

        final LocalDate retrieveDeadline = productController.retrieveDeadline(1);

        assertEquals(expectedDeadline, retrieveDeadline);
    }

    @Test
    void shouldAddNewProductModel() {

    }

    @Test
    void shouldDeleteAllProducts() {
    }

    @Test
    void shouldDeleteSpecificProduct() {
    }

    @Test
    void shouldUpdateProduct() {
    }

    @Test
    void shouldUpdatePriceProduct() {
    }


}