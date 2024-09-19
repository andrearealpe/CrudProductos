package co.com.hyunseda.market.service;

import co.com.hyunseda.market.domain.Product;

/**
 *
 * @author earea
 */
public interface IProductValidator {
    boolean isValid(Product product);
}
