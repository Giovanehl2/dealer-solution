package com.exercise.dealersolution.repository;


import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class DbProduct {

  Set<Product> produtos = new HashSet<>();
  public DbProduct() {

    //Description, Status, Price, Quantity, Deadline to receive new products
    this.produtos.add(new Product(1,"SUV", 1, Double.parseDouble("120000.00"), 100,"31/12/2022"));
    this.produtos.add(new Product(2,"Sedan", 1,Double.parseDouble( "100000.00"), 100, "20/11/2022"));
    this.produtos.add(new Product(3,"Hatch1", 0,Double.parseDouble( "40000.00"), 100, "31/12/2099"));
    this.produtos.add(new Product(4,"Hatch2", 1, Double.parseDouble( "50000.00"), 0,"10/05/2022"));
    this.produtos.add(new Product(5,"Sport", 2, Double.parseDouble("220000.00"), 100,"03/04/2023"));
    this.produtos.add(new Product(6,"Truck", 1, Double.parseDouble( "250000.00"), 100, "01/02/2024"));
    this.produtos.add(new Product(7,"Eletric", 2,Double.parseDouble( "300000.00"), 100, "31/06/2025"));
    this.produtos.add(new Product(8,"Autonomos", 0, Double.parseDouble( "520000.00"), 100, "12/12/2022"));

  }

  public Set<Product> todos(){ return this.produtos;}


  public Set<Product> retrieveOutdated(){
    return this.produtos.stream().filter(x -> x.getStatus() == 0).collect(Collectors.toSet());
  }

  public Set<Product> retrieveUnavailable(){
    return this.produtos.stream().filter(x -> x.getQuantity() == 0).collect(Collectors.toSet());
  }

  public Set<Product> fetchProductsInTransport(){
    return this.produtos.stream().filter(x -> x.getStatus() == 1).collect(Collectors.toSet());
  }

  public Set<Product> getAvailableProducts(){
    return this.produtos.stream().filter(x -> x.getStatus() == 2).collect(Collectors.toSet());
  }

  public void save(Product product){
    this.produtos.add(product);
  }



  public boolean updateProducts(Product product) {
    Product produto =  this.produtos.stream().filter(x -> x.getProductModelId().equals(product.getProductModelId())).findAny().orElse(null);
    if(produto != null){
      this.produtos.remove(produto);
      this.produtos.add(product);
      return true;
    }
      return false;
  }

  public boolean remove(Integer productID) {
    Product produto =  this.produtos.stream().filter(x -> x.getProductModelId().equals(productID)).findAny().orElse(null);

    if(produto == null)
      return false;

    this.produtos.remove(produto);
    return true;
  }

  public Product getProduct(Integer productModelId) {
    return this.produtos.stream().filter(x -> x.getProductModelId().equals(productModelId)).findAny().orElse(null);

  }
}
