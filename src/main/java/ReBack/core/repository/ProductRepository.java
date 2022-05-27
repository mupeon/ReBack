package ReBack.core.repository;

import ReBack.core.data.Member;
import ReBack.core.data.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
@EnableJpaRepositories
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByProductCode(int ProductCode, Pageable pageable);

    List<Product> findByMemberCode(Member memberCode);

//    Product findById(Product productCode);
}