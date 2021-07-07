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
    // user methods
    public List<User> getAllUsers() {
        return localDataSource.getAllUsers();
    }

    public void insertUser(User user) { // save new user
        localDataSource.insertUser(user);
    }
    // product methods
    public List<Product> getAllProduct() {
        return localDataSource.getAllProducts();
    }
}
