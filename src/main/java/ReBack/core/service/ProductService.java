package ReBack.core.service;

import ReBack.core.data.Product;
import ReBack.core.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;



@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Page<Product> list(int page) {
        return productRepository.findAll(PageRequest.of(page, 10, Sort.by(Sort.DEFAULT_DIRECTION, "productCode")));
    }


}
