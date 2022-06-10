package ReBack.core.controller;

import ReBack.core.data.OrderList;
import ReBack.core.data.Orders;
import ReBack.core.data.Product;
import ReBack.core.data.Refund;
import ReBack.core.repository.OrderListRepository;
import ReBack.core.repository.OrdersRepository;
import ReBack.core.repository.ProductRepository;
import ReBack.core.repository.RefundRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
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
//    CategoryRepository categoryRepository;
//    MaterialRepository materialRepository;

    @Autowired
    OrderListRepository orderListRepository;

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
            productRepository.delete(product);
        }

    }

    @PostMapping("/add") //상품 등록
    public String productAdd(@Validated @RequestPart(value = "key") Product product,
                             @RequestPart(value = "file") MultipartFile file,
                             HttpServletRequest request) throws Exception {

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
    public String orderProduct(@RequestBody Orders orders) {

        Optional<Product> product = productRepository.findById(orders.getProductCode().getProductCode());
        Product product2 = product.get();

        int changeStock = product2.getProductStock() - orders.getOrdersStock();

        orders.setProductCode(product2);
        ordersRepository.save(orders);

        System.out.println("최종  orders :: " + orders);


        product2.setProductStock(changeStock);

        productRepository.save(product2);

        /*---------------------------------------------------------------------------------------*/
        Optional<Orders> orders1 = ordersRepository.findById(orders.getOrdersCode());
        Orders orders2 = orders1.get();

        System.out.println("저장후 orders  :::: " + orders2);
        System.out.println(orders2.getProductCode());
        OrderList orderList = new OrderList();
        orderList.setOrderListAmount(orders2.getOrdersStock());
        orderList.setOrdersCode(orders2);
        orderList.setProductCode(orders2.getProductCode());
        System.out.println("최종 orderList" + orderList);
        orderListRepository.save(orderList);

        return "ok";
    }

    @PostMapping("/refund") //환불정보
    public String refundProduct(@RequestBody Refund refund) {
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
        Optional<Refund> refund1 = refundRepository.findById(refund.getRefundCode());
        refund.setRefundCount(refund1.get().getRefundCount());
        refund.setRefundAmount(refund1.get().getRefundAmount());
        refund.setRefundPoint(refund1.get().getRefundPoint());
        refund.setRefundTime(refund1.get().getRefundTime());
        refund.setOrdersCode(refund1.get().getOrdersCode());
        refund.setRefundReason(refund1.get().getRefundReason());

        refundRepository.save(refund);
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
}
