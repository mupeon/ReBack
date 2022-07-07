package ReBack.core.repository;

import ReBack.core.data.Comment;
import ReBack.core.data.Member;
import ReBack.core.data.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {


    @Query("select c from Comment c where c.member =:member and c.product =:product")
    Optional<Comment> findByMember(@Param("member") Member member, @Param("product") Product product);

    @Query("select c from Comment c where c.product.productCode =:product and c.bulletinBoard.bulletinBoardCode =3 order by c.commentHoroscope desc")
    List<Comment> findByReviews(@Param("product") Long product);
//
//    @Query("select c,cf from Comment c,CommentFiles cf where c.product.productCode =:product and c.bulletinBoard.bulletinBoardCode =3 and cf.comment.commentCode=c.commentCode ")
//    List<Comment> findByReviews(@Param("product") Long product);
}