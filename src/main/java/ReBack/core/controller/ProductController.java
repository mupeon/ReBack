package ReBack.core.controller;

import ReBack.core.data.Member;
import ReBack.core.data.Orders;
import ReBack.core.data.Product;
import ReBack.core.data.Refund;
import ReBack.core.repository.*;
import ReBack.core.security.SecurityUser;
import ReBack.core.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Controller
public class ProductController {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    MaterialRepository materialRepository;
    @Autowired
    ProductService productService;
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    RefundRepository refundRepository;


    @GetMapping("/product") //상품 조회 페이지 [모든사람]
    public String ProductPage(@AuthenticationPrincipal SecurityUser principal, Model model, @PageableDefault(size = 5) Pageable pageable, @RequestParam(required = false, defaultValue = "0", value = "page") int page,
                              @RequestParam(required = false) Long id) {

        System.out.println("RequestParam으로받은 page" + page);
        page = 100;
        Page<Product> listPage = productService.list(page);

        int totalPage = 5;// 총 페이지수
        System.out.println("토탈 페이지" + totalPage);
        model.addAttribute("productlists", listPage.getContent());
        model.addAttribute("totalPage", totalPage);

//        List<Product> productList = (List<Product>) productRepository.findAll(pageable);
        model.addAttribute("products", productRepository.findAll(pageable));
        model.addAttribute("categorys", categoryRepository.findAll(pageable));

        System.out.println("id값:" + id);
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }
        return "product/productPage";
    }

    @GetMapping("/product/manager") // 상품 관리자 페이지 [관리자]
    public String productManager(@AuthenticationPrincipal SecurityUser principal, Model model,
                                 @PageableDefault(size = 5) Pageable pageable,
                                 @RequestParam(required = false, defaultValue = "0", value = "page") int page,
                                 @RequestParam(required = false) Member memberCode) {

        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }

        System.out.println("RequestParam으로받은 page" + page);
        page = 100;
        Page<Product> listPage = productService.list(page);

        int totalPage = 7;// 총 페이지수
        System.out.println("토탈 페이지" + totalPage);
        model.addAttribute("productlists", listPage.getContent());
        model.addAttribute("totalPage", totalPage);


        model.addAttribute("addproducts", productRepository.findByMemberCode(memberCode));
        model.addAttribute("categorys", categoryRepository.findAll(pageable));

        return "product/productManager";
    }


    @GetMapping("/product/manage") //상품 관리 페이지 [수정 및 삭제] , [관리자]
    public String productManage(@AuthenticationPrincipal SecurityUser principal, Model model, @RequestParam(required = false) Long id) {

        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }
        System.out.println("받은id = " + id);
        Product product = productRepository.findById(id).orElse(null);
        model.addAttribute("categoryCode", product.getCategoryCode());
        model.addAttribute("productName", product.getProductName());
        model.addAttribute("productCode", product.getProductCode());
        model.addAttribute("productInfo", product.getProductInfo());
        model.addAttribute("productPrice", product.getProductPrice());
        model.addAttribute("productStock", product.getProductStock());
        model.addAttribute("materialCode", product.getMaterialCode());
        model.addAttribute("productFileName", product.getProductFileName());
        model.addAttribute("productFilePath", product.getProductFilePath());
        model.addAttribute("categorys", categoryRepository.findAll());
        model.addAttribute("materials", materialRepository.findAll());
        model.addAttribute("categoryName", product.getCategoryCode().getCategoryName());
        model.addAttribute("materialName", product.getMaterialCode().getMaterialName());

        return "product/productManage";
    }

    @GetMapping("/product/add") //상품 등록 페이지 [관리자]
    public String productAdd(@AuthenticationPrincipal SecurityUser principal,
                             Model model) {
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("materials", materialRepository.findAll());
        return "product/productAdd";
    }

    @GetMapping("/product/details") // 상품 상세보기 [모든사람]
    public String productDetails(@AuthenticationPrincipal SecurityUser principal,
                                 Model model, @RequestParam(required = false) Long id) {

        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }
        Product product = productRepository.findById(id).orElse(null);
        model.addAttribute("product", product);

        model.addAttribute("productCode", product.getProductCode());
        model.addAttribute("categoryCode", product.getCategoryCode().getCategoryCode());
        model.addAttribute("categoryName", product.getCategoryCode().getCategoryName());
        model.addAttribute("materialCode", product.getMaterialCode().getMaterialCode());
        model.addAttribute("materialName", product.getMaterialCode().getMaterialName());
        model.addAttribute("productName", product.getProductName());
        model.addAttribute("productCode", product.getProductCode());
        model.addAttribute("productInfo", product.getProductInfo());
        model.addAttribute("productPrice", product.getProductPrice());
        model.addAttribute("productStock", product.getProductStock());
        model.addAttribute("productFileName", product.getProductFileName());
        model.addAttribute("productFilePath", product.getProductFilePath());
        model.addAttribute("categorys", categoryRepository.findAll());
        model.addAttribute("materials", materialRepository.findAll());

        return "product/productDetails";
    }

    @GetMapping("/product/orderlist") // 상품 주문 내역 [모든사람]
    public String productOrderList(@AuthenticationPrincipal SecurityUser principal, @RequestParam(required = false) Member memberCode, Model model) {
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }

        model.addAttribute("orders", ordersRepository.find2ByMemberCode(memberCode));
        model.addAttribute("refund", refundRepository.find3ByMemberCode(memberCode));

        System.out.println("ordersRepository.find3ByMemberCode(memberCode) :::  "+refundRepository.find3ByMemberCode(memberCode));
//        System.out.println(orders);


        return "product/orderList";
    }

    @GetMapping("/product/refund") // 상품 환불 페이지 [모든사람]
    public String productRefund(@AuthenticationPrincipal SecurityUser principal, Model model, @RequestParam(required = false) Long ordersCode) {

        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }
        System.out.println("ordersCode :: " + ordersCode);

        Orders orders = ordersRepository.findByOrdersCode(ordersCode);
        System.out.println("orders.getOrdersCode() ::: " + orders.getOrdersCode());

        model.addAttribute("list", ordersRepository.findByOrdersCode(ordersCode));

        return "product/refund";
    }

    @GetMapping("/product/refundlist") // 상품 환불 내역 [모든사람]
    public String productRefundList(@AuthenticationPrincipal SecurityUser principal, Model model, @RequestParam(required = false) Member memberCode) {

        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }
        model.addAttribute("refundList",refundRepository.find2ByMemberCode(memberCode));


        return "product/refundList";
    }

    @GetMapping("/product/refundManager") // 상품 환불 관리 [관리자]
    public String refundManager(@AuthenticationPrincipal SecurityUser principal, Model model, @RequestParam(name = "memberCode" ,required = false) Member memberCode) {
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }
        model.addAttribute("refundList",refundRepository.findByMemberCode(memberCode));

        return "product/refundManager";
    }

    @GetMapping("/product/refundDetail") // 상품 환불 관리 상세보기 [관리자]
    public  String refundDetail(@AuthenticationPrincipal SecurityUser principal, Model model, @RequestParam(name = "refundCode" ,required = false) Long refundCode){
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }
        Refund refund = refundRepository.findById(refundCode).orElse(null);
        System.out.println("refund :: "+refund);
        model.addAttribute("Detail",refund);
        return "/product/refundDetail";
    }

}
