package space.lala.nyxpizzaapp.datasource.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import space.lala.nyxpizzaapp.datasource.local.database.ProductDao;
import space.lala.nyxpizzaapp.datasource.local.database.ProductsDatabase;
import space.lala.nyxpizzaapp.datasource.remote.ProductService;
import space.lala.nyxpizzaapp.model.Product;

public class ProductsRepository {
    private ProductDao productDao;
    private ProductService productService;

    public ProductsRepository(Application application) {
        ProductsDatabase database = ProductsDatabase.getInstance(application);
        productDao = database.productDao();
        productService = new ProductService();
        productService.getProductsFromServer()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<List<Product>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<Product> serverProducts) {
                        List<Product> localProducts = productDao.getAllProductsSync(0);

                        for (Product localProduct: localProducts) {
                            if (!serverProducts.contains(localProduct)) {
                                deleteProduct(localProduct);
                            }
                        }

                        for (Product product : serverProducts) {
                            insertProduct(product);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<List<Product>> getAllProducts(Product.Type type) {
        return productDao.getAllProducts(type.ordinal());
    }

    public LiveData<Product> getProduct(int id) {
        return productDao.getProduct(id);
    }

    public void insertProduct(Product product) {
        new InsertProductAsyncTask(productDao).execute(product);
    }

    private void deleteProduct(Product product) {
        new DeleteProductAsyncTask(productDao).execute(product);
    }

    private static class InsertProductAsyncTask extends AsyncTask<Product, Void, Void> {
        private ProductDao productDao;

        private InsertProductAsyncTask(ProductDao productDao) {
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Product... products) {
            productDao.insert(products[0]);
            return null;
        }
    }

    private static class DeleteProductAsyncTask extends AsyncTask<Product, Void, Void> {
        private ProductDao productDao;

        private DeleteProductAsyncTask(ProductDao productDao) {
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Product... products) {
            productDao.deleteProduct(products[0].getId());
            return null;
        }
    }


}
