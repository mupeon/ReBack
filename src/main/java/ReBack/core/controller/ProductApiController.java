package ReBack.core.controller;

import ReBack.core.data.*;
import ReBack.core.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static ReBack.core.data.RefundState.환불완료;

@RestController
@RequestMapping("/api/product")
public class ProductApiController {

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    RefundRepository refundRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CommentFilesRepository commentFilesRepository;
    @PutMapping("/update") //상품 수정
    public void productUpdate(@RequestBody Product product) {
        System.out.println("수정api");
        System.out.println(product.getProductFilePath() + " 사진경로");
        Optional<Product> searchProduct = productRepository.findById(product.getProductCode());

        if (searchProduct.isPresent()) {
            product.setProductFilePath(searchProduct.get().getProductFilePath());
            product.setProductFileName(searchProduct.get().getProductFileName());
        }
        productRepository.save(product);

    }

    @DeleteMapping("/delete") //상품 삭제
    public void productDelete(@RequestBody Product product) {
        Optional<Product> deleteProduct = productRepository.findById(product.getProductCode());

        if (deleteProduct.isPresent()) {
            System.out.println(deleteProduct);
            productRepository.delete(deleteProduct.get());
        }

    }

    @PostMapping("/add") //상품 등록
    public String productAdd(@Validated @RequestPart(value = "key") Product product, @RequestPart(value = "file") MultipartFile file, HttpServletRequest request) throws Exception {

        System.out.println(product);
        String fileName;
        if (file == null) {
            fileName = "";
        } else {
            fileName = file.getOriginalFilename(); //
            String filepath = request.getSession().getServletContext().getRealPath("") + "file\\"; // webapps/file
            System.out.println("filepath  :    " + filepath);
            try {

                file.transferTo(new File(filepath + fileName));
                System.out.println("업로드 성공");
                product.setProductFileName(fileName);
                product.setProductFilePath("/file/" + fileName);

            } catch (IllegalStateException | IOException e) {
                System.out.println("실패");
                e.printStackTrace();
            }
        }

        productRepository.save(product);
        return "redirect:/product/manager";
    }

    @PostMapping("/order") //상품 주문
    public Long orderProduct(@RequestBody Orders orders) {

        Optional<Product> product = productRepository.findById(orders.getProductCode().getProductCode());
        Product product2 = product.get();

        int changeStock = product2.getProductStock() - orders.getOrdersStock();

        orders.setProductCode(product2);
        ordersRepository.save(orders);

        System.out.println("최종  orders :: " + orders);


        product2.setProductStock(changeStock);

        productRepository.save(product2);

        return orders.getProductCode().getProductCode();

    }

    @PostMapping("/refund") //환불정보
    public String refundProduct(@RequestBody Refund refund) {
        System.out.println(refund);
        refundRepository.save(refund);

//        System.out.println(ordersRepository.find2ByOrdersCode(refund.getOrdersCode().getOrdersCode()));
        Optional<Orders> orders = ordersRepository.find2ByOrdersCode(refund.getOrdersCode().getOrdersCode());

        System.out.println(orders.get().getRefundStatus());

        orders.get().setRefundStatus(refund.getRefundStatus());

        ordersRepository.save(orders.get());


        return "/product/refund";
    }

    @PutMapping("/refundManager") //환불 완료 처리
    public String refundManager(@RequestBody Refund refund) {
        System.out.println(refund.getRefundStatus());
        Optional<Refund> refund1 = refundRepository.findById(refund.getRefundCode());
        refund.setRefundCount(refund1.get().getRefundCount());
        refund.setRefundAmount(refund1.get().getRefundAmount());
        refund.setRefundPoint(refund1.get().getRefundPoint());
        refund.setRefundTime(refund1.get().getRefundTime());
        refund.setOrdersCode(refund1.get().getOrdersCode());
        refund.setRefundReason(refund1.get().getRefundReason());
        System.out.println("환불상태" + refund.getRefundStatus());
        refundRepository.save(refund);
        System.out.println("refund.getOrdersCode().getOrdersCode()" + refund.getOrdersCode().getOrdersCode());
        Optional<Orders> orders = ordersRepository.findById(refund.getOrdersCode().getOrdersCode());
        orders.get().setRefundStatus(refund.getRefundStatus());
        System.out.println("ordersRepository :: " + orders.get());
        ordersRepository.save(orders.get());


        if (refund.getRefundStatus() == 환불완료) {
            Optional<Product> product = productRepository.findById(refund.getOrdersCode().getProductCode().getProductCode());
            int changeStock = product.get().getProductStock() + refund.getOrdersCode().getOrdersStock();
            product.get().setProductStock(changeStock);
            productRepository.save(product.get());
        } else {
            System.out.println(refund.getRefundStatus());
        }
        return "redirect:/product/refundManager";
    }

    @PostMapping("/cart") //카드 상품 담기
    public String cartProduct(@RequestBody Cart cart) {

        Optional<Cart> cart1 = cartRepository.findByProduct(cart.getProductCode(), cart.getMemberCode());
        if (cart1.isPresent()) {
            System.out.println(cart1.get());
            Product productCode = cart.getProductCode();
            Member memberCode = cart.getMemberCode();
            Product productCode1 = cart1.get().getProductCode();
            Member memberCode1 = cart1.get().getMemberCode();
            // 이미 담긴 상품이면 담지 않는다
            if (productCode.getProductCode().equals(productCode1.getProductCode()) && memberCode.getMemberCode().equals(memberCode1.getMemberCode())) {
                return "exist";
            }
        } // 그렇지 않다면 장바구니에 담는다.
        else {
            cartRepository.save(cart);
            return "save";
        }
        return "ok";
    }

    @PutMapping("/cart/update") //카드 cartCount 수정
    public String updateCart(@RequestBody Cart cart) {

        System.out.println(cart.getCartCode());
        System.out.println(cart.getCartCount());
        System.out.println(cart.getProductCode().getProductCode());
        System.out.println(cart.getMemberCode().getMemberCode());

        Optional<Cart> cart1 = cartRepository.findById(cart.getCartCode());
        System.out.println("cart1: " + cart1.get());
        cart1.get().setCartCount(cart.getCartCount());

        cartRepository.save(cart1.get());
        return "cart";
    }

    @DeleteMapping("/cart/delete") //상품 삭제
    public void deleteCart(@RequestBody Cart cart) {
        Optional<Cart> deleteCart = cartRepository.findById(cart.getCartCode());

        if (deleteCart.isPresent()) {
            System.out.println(deleteCart);
            cartRepository.delete(deleteCart.get());
        }

    }

    @PutMapping("/cart/checkState") //
    public void CheckCart(@RequestBody Cart cart) {
        System.out.println("cart.getCartCode()"+cart.getCartCode());

        System.out.println(cart.getCheckState());
        Optional<Cart> cart1 = cartRepository.findById(cart.getCartCode());
        System.out.println(cart1.get());
        cart1.get().setCheckState(cart.getCheckState());
        cartRepository.save(cart1.get());

    }

    @PostMapping("/cart/buy") //
    public void buyCart(@RequestBody Cart cart) {

        System.out.println(cart.getCheckState());
    }

    @PostMapping("/cart/order") //
    public void buyCart1(@RequestBody Orders orders) {

        System.out.println("  orders :: " + orders.getProductCode().getProductCode());
        System.out.println("  orders :: " + orders.getMemberCode().getMemberCode());

        Optional<Product> product = productRepository.findById(orders.getProductCode().getProductCode());
        Product product2 = product.get();

        int changeStock = product2.getProductStock() - orders.getOrdersStock();

        orders.setProductCode(product2);
        ordersRepository.save(orders);

        System.out.println("최종  orders :: " + orders);


        product2.setProductStock(changeStock);
        Long memberCode = orders.getMemberCode().getMemberCode();
        Long productCode = orders.getProductCode().getProductCode();
        productRepository.save(product2);
        Integer cart = cartRepository.findByCartOrders(memberCode,productCode);


    }
    @PostMapping("/search") //
    public String search(@RequestBody Product product) {

        System.out.println("Product " + product.getProductName() );
        System.out.println("검색어 결과 : "+ productRepository.findBySearch(product.getProductName()).size());
        return product.getProductName();
    }
    @PostMapping("/reviews") //리뷰
    public String reviewsAdd(@Validated @RequestPart(value = "key") Comment comment, @RequestPart(value = "file") MultipartFile file, HttpServletRequest request) throws Exception {
//        CommentFiles commentFiles = new CommentFiles();

        System.out.println("getCommentHoroscope :: "+comment.getCommentHoroscope());
        String fileName;
        if (file == null) {
            fileName = "";
        } else {
            fileName = file.getOriginalFilename(); //
            String filepath = request.getSession().getServletContext().getRealPath("") + "file\\"; // webapps/file
            System.out.println("filepath  :    " + filepath);
            try {

                file.transferTo(new File(filepath + fileName));
                System.out.println("업로드 성공");
                comment.setReviewFileName(fileName);
                comment.setReviewFilePath("/file/" + fileName);

            } catch (IllegalStateException | IOException e) {
                System.out.println("실패");
                e.printStackTrace();
            }
        }

        System.out.println();

        //이미 같은상품의 후기가 등록되어있으면 x 없으면 o
        if(commentRepository.findByMember(comment.getMember(),comment.getProduct()).isPresent() == false){
            commentRepository.save(comment);
            Optional<Comment> comment1 = commentRepository.findByMember(comment.getMember(),comment.getProduct());
            System.out.println(comment1.get().getCommentCode());
//        Comment commentCode = comment1.get();
//            commentFiles.setComment(comment1.get());
//            commentFilesRepository.save(commentFiles);
            System.out.println("ok 리턴");
            return "ok";

        }else{
            System.out.println("no 리턴");

            return "no";
        }

    }

}