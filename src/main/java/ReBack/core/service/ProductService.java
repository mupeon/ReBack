package ReBack.core.service;

import ReBack.core.data.Product;
import ReBack.core.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;



@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;



    public Page<Product> pageList(Pageable pageable) {
        return productRepository.findAll(pageable);    }
}
