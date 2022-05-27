package ReBack.core.repository;

import ReBack.core.data.Design;
import ReBack.core.data.Product;
import org.hibernate.query.spi.QueryParameterBindingTypeResolver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface DesignRepository extends JpaRepository<Design, Long>,
        QuerydslPredicateExecutor<Design> {
    Page<Design> findByDesignCode(int DesignCode, Pageable pageable);

    @Query("select d, df, count(r) from Design d " +
            "left outer join DesignFile df on df.design = d " +
            "left outer join Reply r on r.design = d group by d")
    Page<Object[]> getListPage(Pageable pageable); // 페이지 처리

    @Query("select d, df, count(r)" + " from Design d left outer join DesignFile df on df.design = d " +
            " left outer join Reply r on r.design = d " +
            " where d.designCode = :designCode group by df")
    List<Object[]> getDesignWithAll(@Param("designCode") Long designCode);
}
