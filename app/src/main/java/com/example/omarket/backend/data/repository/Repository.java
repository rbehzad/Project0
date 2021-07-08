  package com.example.omarket.backend.data.repository;

  import com.example.omarket.backend.data.entities.Product;
  import com.example.omarket.backend.data.entities.User;

  import java.util.List;

  public class Repository {
    private static Repository repository = new Repository();

    private LocalDataSource localDataSource;
    private RemoteDataSource remoteDataSource;

    private Repository() {
    }

    public static Repository getInstance() {
        if(repository==null) {
            repository = new Repository();
        }
        return repository;
    }
    //////////
    // get all user
    public List<User> getAllUsers() {
        return localDataSource.getAllUsers();
    }
    // save new user
    public void insertUser(int id, String name, String emailAddress, String password, String phoneNumber, String userType) {
        localDataSource.insertUser(id, name, emailAddress, password, phoneNumber, userType);
    }
    // search a user by its emailAddress
    public User searchUser(String userEmailAddress) {
        return localDataSource.searchUser(userEmailAddress);
    }
    /////////
    // get all product
    public List<Product> getAllProduct() {
        return localDataSource.getAllProducts();
    }
    // save new product
    public void insertProduct(int id, String name, String info, String imagePath, String sellerName, String sellerId) {
        localDataSource.insertProduct(id, name, info, imagePath, sellerName, sellerId);
    }
    // search a product
    public Product searchProduct(String productName) {
        return localDataSource.searchProduct(productName);
    }
}
