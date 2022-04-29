package com.exercise.dealersolution.business;

import com.exercise.dealersolution.exception.ProdutoNaoEncontradoException;
import com.exercise.dealersolution.repository.DbProduct;
import com.exercise.dealersolution.repository.Product;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Set;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dealer")
public class ProductController {

  private DbProduct dbProduct;


  public ProductController(DbProduct product) {
    this.dbProduct = product;
  }

  @GetMapping("/models")
  public Set<Product> retrieveAll() {
    return dbProduct.todos();
  }

  @GetMapping("/models/available")
  public Set<Product> getAll() {

    return  this.dbProduct.getAvailableProducts();
  }

  @GetMapping("/models/{id}")
  public LocalDate retrieveDeadline(@PathVariable Integer id) {
    Set<Product> lista = dbProduct.todos();
    Product product = dbProduct.todos().stream().filter(x -> x.getProductModelId().equals(id)).findAny().orElse(null);
    if (product == null) {
      throw new ProdutoNaoEncontradoException();
    }
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    return LocalDate.parse(product.getDeadlineProduct(), formatter);

  }

  @PostMapping("/models/new/{id}")
  public void addNewProductModel(@PathVariable Integer productModelId, @RequestBody Product product) {
    product.setProductModelId(productModelId);
    this.dbProduct.save(product);
  }

  @PostMapping("/models")
  public void apagarItens(@RequestBody String listProductID) {
    for (String productID : listProductID.split(",")) {
      this.dbProduct.remove(Integer.parseInt(productID));
    }
  }

  @DeleteMapping("/models/{id}")
  public void delete(@PathVariable Integer id) {
    this.dbProduct.remove(id);
  }

  @PutMapping("/model/{id}")
  public void updateProduct(@PathVariable Integer productModelId, @RequestBody Product product) {
    product.setProductModelId(productModelId);
    this.dbProduct.updateProducts(product);
  }

  @PutMapping("/model/{id}/{price}")
  public void updateProductPrice(@PathVariable Integer productModelId, @PathVariable Double price) {
    Product product = this.dbProduct.getProduct(productModelId);
    product.setPrice(price);
    this.dbProduct.updateProducts(product);
  }
}
