/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.school.ezon.api.controllers;

import java.util.Collections;
import java.util.List;
import org.school.ezon.api.Exceptions.UserExistException;
import org.school.ezon.api.dataFormatters.SpaceFormatter;
import org.school.ezon.api.entity.ClickedProduct;
import org.school.ezon.api.entity.Users;
import org.school.ezon.api.facade.Facade;
import org.school.ezon.api.pojo.Product;
import org.school.ezon.api.sorters.SortByPrice;

/**
 *
 * @author Mikkel
 */
public class Controller {

    private final ICollectorController collectorControl;
    private final Facade facade;

    public Controller(ICollectorController collectorControl, Facade facade) {
        this.collectorControl = collectorControl;
        this.facade = facade;
    }

    public List<Product> getProductsFromCategory(String category) {
        List<Product> products = collectorControl.getProductsFromCategory(category);
        Collections.sort(products, new SortByPrice());
        return products;
    }

    /**
     * Returns the CollectorControllers method by the same same
     *
     * @param searchString
     * @return
     */
    public List<Product> getProductsBySearch(String searchString) {
        String trimmedSearch = SpaceFormatter.format(searchString);
        facade.updateUnspecificSearch(trimmedSearch);
        List<Product> products = collectorControl.getProductsBySearch(trimmedSearch);
        Collections.sort(products, new SortByPrice());
        return products;
    }

    /**
     * Gets a list of products from ebay and DBA sorted by price from a keyword
     * and a search string
     *
     * @param category
     * @param searchString
     * @return
     */
    public List<Product> getProductsBySearchAndCategory(String category, String searchString) {
        String trimmedSearch = SpaceFormatter.format(searchString);
        facade.updateUnspecificSearch(trimmedSearch);
        List<Product> products = collectorControl.getProductsBySearchAndCategory(category, trimmedSearch);
        Collections.sort(products, new SortByPrice());
        return products;
    }

    public List<Product> getPopularProducts() {
        return collectorControl.getPopularProducts(facade.getPopularSearches());
    }

    public Users signUp(String email, String password) throws UserExistException {
        return facade.createUser(email, password);
    }
    
    public void registerClick(ClickedProduct product){
        facade.updateClickedLink(product);
    }
}
