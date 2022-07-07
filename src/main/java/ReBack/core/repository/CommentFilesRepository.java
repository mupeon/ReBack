package ReBack.core.repository;

import ReBack.core.data.CommentFiles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentFilesRepository extends JpaRepository<CommentFiles, Long> {

//    @Query("select cf from CommentFiles cf,Comment c where cf.comment.commentCode=c.commentCode and c.product.productCode =:product and c.bulletinBoard.bulletinBoardCode =3")
//    List<Comment> findByFile(@Param("product") Long product);
}