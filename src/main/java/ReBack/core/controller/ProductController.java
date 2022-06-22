package ReBack.core.controller;

import ReBack.core.data.Member;
import ReBack.core.data.Orders;
import ReBack.core.data.Product;
import ReBack.core.data.Refund;
import ReBack.core.repository.*;
import ReBack.core.security.SecurityUser;
import ReBack.core.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


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

    @GetMapping("/product/category")
    public String selectCate(@AuthenticationPrincipal SecurityUser principal, Model model,
                             @RequestParam(required = false) Long categoryCode) {
        List<Product> selectProduct;

        if(categoryCode == 0) {
            selectProduct = productRepository.findAll();
            System.out.println(selectProduct.size());
            model.addAttribute("products", selectProduct);
        } else {
            selectProduct = productRepository.selectCate(categoryCode);
            System.out.println(selectProduct.size());
            model.addAttribute("products", selectProduct);
        }

        model.addAttribute("categorys", categoryRepository.findAll());

        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }
        return "product/productPage";
    }



    @GetMapping("/product") //상품 조회 페이지 [모든사람]
    public String ProductPage(@AuthenticationPrincipal SecurityUser principal, Model model,
                              @RequestParam(required = false) Long id) {

        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("categorys", categoryRepository.findAll());

        System.out.println("id값:" + id);
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }
        return "product/productPage";
    }

    @GetMapping("/product/manager") // 상품 관리자 페이지 [관리자]
    public String productManager(@AuthenticationPrincipal SecurityUser principal, Model model,
                                 @RequestParam(required = false) Member memberCode) {

        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }



        model.addAttribute("addproducts", productRepository.findByMemberCode(memberCode));
//
        model.addAttribute("categorys", categoryRepository.findAll());

        return "product/productManager";
    }

    @GetMapping("/product/manager/category")
    public String selectCate2(@AuthenticationPrincipal SecurityUser principal, Model model,
                              @RequestParam(required = false) Member memberCode,
                              @RequestParam(required = false) Long categoryCode) {

        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }

        System.out.println(memberCode.getMemberCode());
        Long memberCode1= memberCode.getMemberCode();
        List<Product> selectProduct;

        if(categoryCode == 0) {
            selectProduct = productRepository.findByMemberCode(memberCode);
            System.out.println(selectProduct.size());
            model.addAttribute("addproducts", selectProduct);
        } else {
            selectProduct = productRepository.selectCate2(categoryCode,memberCode1); //memberCode + categoryCode
            System.out.println(selectProduct.size());
            model.addAttribute("addproducts", selectProduct);
        }

        model.addAttribute("categorys", categoryRepository.findAll());


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
