  package com.example.omarket.backend.data.data.repository;

  import android.content.Context;

  import com.example.omarket.backend.application.MyApplication;
  import com.example.omarket.backend.data.data.entities.Product;
  import com.example.omarket.backend.data.data.entities.RepoUser;

  import java.util.List;

  public class Repository {
      RepoUser repoUser;

      private static Repository repository;

    private LocalDataSource localDataSource;
    private RemoteDataSource remoteDataSource;

    private Repository(Context context) {
        localDataSource = new LocalDataSource(context);
        remoteDataSource = new RemoteDataSource();
    }

    public static Repository getInstance(Context context) {
        if(repository==null) {
            repository = new Repository(context);
        }
        return repository;
    }
    //////////
    // get all user
    public void getAllUsers(RepositoryCallback<List<RepoUser>> callback) {
        MyApplication.executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    List<RepoUser> repoUsers = localDataSource.getAllUsers();
                    callback.onComplete(new Result.Success<>(repoUsers));
                } catch (Exception e) {
                    callback.onComplete(new Result.Error<>(e));
                }
            }
        });

    }
    // insert new user
    public void insertUser(String name, String emailAddress, String password, String phoneNumber, String userType, RepositoryCallback<Void> callback) {
        MyApplication.executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    localDataSource.insertUser(name, emailAddress, password, phoneNumber, userType);
                    callback.onComplete(new Result.Success<>(null));
                } catch (Exception e) {
                    callback.onComplete(new Result.Error<>(null));
                }

            }
        });
    }
    // search a user by its emailAddress
    public RepoUser searchUser(RepositoryCallback<com.example.omarket.backend.data.data.entities.RepoUser> callback, String userEmailAddress) {
        MyApplication.executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    repoUser = localDataSource.searchUser(userEmailAddress);
                    callback.onComplete(new Result.Success<>(null));
                } catch (Exception e) {
                    callback.onComplete(new Result.Error<>(null));
                }
            }
        });
        return repoUser;
    }
    /////////
    // get all product
    public List<Product> getAllProduct(RepositoryCallback<List<com.example.omarket.backend.data.data.entities.RepoUser>> error) {
        return localDataSource.getAllProducts();
    }
    // insert new product
    public void insertProduct(String title, String description, String cost, String imagePath, String sellerEmail) {
        localDataSource.insertProduct(title, description, cost, imagePath, sellerEmail);
    }
    // search a product
    public Product searchProduct(String productName) {
        return localDataSource.searchProduct(productName);
    }
  }
