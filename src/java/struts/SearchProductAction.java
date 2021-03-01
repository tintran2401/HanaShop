/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package struts;

import entity.TblProduct;
import java.util.ArrayList;
import java.util.List;
import service.ProductService;

/**
 *
 * @author TiTi
 */
public class SearchProductAction {

    private static final int DEFAULT_PAGE_SIZE = 20;
    private static final int DEFAULT_PAGE_NUMBER = 1;
    private static final int MAX_PRICE = 2000000000;

    private final String SUCCESS = "success";

    private String productName;
    private int minPrice;
    private int maxPrice;
    private String categoryName;
    private int pageNumber;
    private int pageSize;
    private List<TblProduct> products;
    private int productsCount;
    private String message;

    public SearchProductAction() {
    }

    public String execute() throws Exception {
        String url = SUCCESS;

        productName = productName == null ? "" : productName;
        categoryName = categoryName == null ? "" : categoryName;
        pageNumber = pageNumber <= 0 ? DEFAULT_PAGE_NUMBER : pageNumber;
        pageSize = pageSize <= 0 ? DEFAULT_PAGE_SIZE : pageNumber;

        // 1. Search all products with 'productName' and price in range 'min, max price'
        // 2. Filter products which have category contains 'categoryName'
        // 3. Store the number of products found in 'productsCount'
        // 4. Reduce products count to 'pageSize' with according 'pageNumber'
        try {
            ProductService productService = new ProductService();
            int searchMaxPrice = maxPrice == 0 ? MAX_PRICE : maxPrice;
            products = productService.getProducts(productName, minPrice, searchMaxPrice);

            List<TblProduct> productsToRemove = new ArrayList<>();
            for (TblProduct product : products) {
                if (!product.getCategoryId().getName().toLowerCase().contains(categoryName.toLowerCase())) {
                    productsToRemove.add(product);
                }
            }
            products.removeAll(productsToRemove);

            productsCount = products.size();

            int fromIndex = (pageNumber - 1) * pageSize;
            int toIndex = fromIndex + pageSize;
            toIndex = toIndex > productsCount ? productsCount : toIndex;

            products = products.subList(fromIndex, toIndex);
        } catch (Exception e) {
            products = new ArrayList<>();
            message = "Error something happenned! Message: " + e.getMessage();
        } finally {

        }
        return url;

    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the minPrice
     */
    public int getMinPrice() {
        return minPrice;
    }

    /**
     * @param minPrice the minPrice to set
     */
    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    /**
     * @return the maxPrice
     */
    public int getMaxPrice() {
        return maxPrice;
    }

    /**
     * @param maxPrice the maxPrice to set
     */
    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    /**
     * @return the categoryName
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * @param categoryName the categoryName to set
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * @return the pageNumber
     */
    public int getPageNumber() {
        return pageNumber;
    }

    /**
     * @param pageNumber the pageNumber to set
     */
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @return the products
     */
    public List<TblProduct> getProducts() {
        return products;
    }

    /**
     * @param products the products to set
     */
    public void setProducts(List<TblProduct> products) {
        this.products = products;
    }

    /**
     * @return the productsCount
     */
    public int getProductsCount() {
        return productsCount;
    }

    /**
     * @param productsCount the productsCount to set
     */
    public void setProductsCount(int productsCount) {
        this.productsCount = productsCount;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the DEFAULT_PAGE_SIZE
     */
    public static int getDEFAULT_PAGE_SIZE() {
        return DEFAULT_PAGE_SIZE;
    }
}
