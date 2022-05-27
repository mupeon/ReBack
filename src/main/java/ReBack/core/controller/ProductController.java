package ReBack.core.controller;

import ReBack.core.data.Member;
import ReBack.core.data.Orders;
import ReBack.core.data.Product;
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


    @GetMapping("/product") //상품 조회 페이지 [모든사람]
    public String ProductPage(@AuthenticationPrincipal SecurityUser principal, Model model, @PageableDefault(size = 5) Pageable pageable, @RequestParam(required = false, defaultValue = "0", value = "page") int page,
                              @RequestParam(required = false) Long id) {

        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());

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
            }
            return "product/productPage";
        }


    @GetMapping("/product/manager")
    public String productManager( @AuthenticationPrincipal SecurityUser principal,Model model,
                                  @PageableDefault(size = 5) Pageable pageable,
                                  @RequestParam(required = false, defaultValue = "0", value = "page") int page,
                                  @RequestParam(required = false) Member memberCode){

        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());

            System.out.println("RequestParam으로받은 page" + page);
            page = 100;
            Page<Product> listPage = productService.list(page);

            int totalPage = 7;// 총 페이지수
            System.out.println("토탈 페이지" + totalPage);
            model.addAttribute("productlists", listPage.getContent());
            model.addAttribute("totalPage", totalPage);


            model.addAttribute("addproducts", productRepository.findByMemberCode(memberCode));
            model.addAttribute("categorys", categoryRepository.findAll(pageable));
        }
        return "product/productManager";
    }


    @GetMapping("/product/manage") //상품 관리 페이지 [수정 및 삭제]
    public String productManage(@AuthenticationPrincipal SecurityUser principal,Model model, @RequestParam(required = false) Long id) {

        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());

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
        }
        return "product/productManage";
    }

    @GetMapping("/product/add") //상품 등록 페이지
    public String productAdd(@AuthenticationPrincipal SecurityUser principal, Model model) {
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());

            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("materials", materialRepository.findAll());
        }
        return "product/productAdd";
    }

    @GetMapping("/product/details")
    public String productDetails(@AuthenticationPrincipal SecurityUser principal, Model model, @RequestParam(required = false) Long id) {

        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());

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
        }
        return "product/productDetails";
    }

    @GetMapping("/product/orderlist")
    public String productOrderList(@AuthenticationPrincipal SecurityUser principal, @RequestParam(required = false) Member memberCode, Model model) {
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());

//        Orders orders = ordersRepository.findById(id).orElse(null);

//        System.out.println(orders);
            model.addAttribute("orders", ordersRepository.findByMemberCode(memberCode));


//        System.out.println(orders);

        }
        return "product/orderList";
    }
    @GetMapping("/product/refund")
    public String productRefund(@AuthenticationPrincipal SecurityUser principal, Model model, @RequestParam(required = false) Long ordersCode){

        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());

            System.out.println(ordersCode);

//        Optional<Orders> orders = ordersRepository.findById(ordersCode);
//        System.out.println(orders.get());
//
//        model.addAttribute("orders",ordersRepository.findById(ordersCode));
        }
        return"product/refund";
    }

//    @GetMapping("/product/orderlist/{MemberCode}")
//    public String userOrderList(@PathVariable("MemberCode")Long MemberCode){
////        Member member = memberRepository.findByMemberCode(MemberCode);
////
////        System.out.println("맴버 모든정보 ::: "+member);
////        System.out.println("멤버코드 :::: "+member.getMemberCode());
//
////        Optional<Orders> orders = ordersRepository.findByMemberCode(MemberCode);
//
////        Orders userOrders = orders.get();
//
////        System.out.println(userOrders);
//
//
//
//        return "product/orderList";
//
//    }
    /*
    @GetMapping("/product/order") //구매 페이지
    public String productOrder(Model model, @RequestBody Orders orders){
        System.out.println(orders);
        model.addAttribute("categorys", categoryRepository.findAll());
        model.addAttribute("materials", materialRepository.findAll());
        return "product/orderPage";
    }
    @PostMapping("/product/order")
    public String productOrder(@RequestBody String string ){
    System.out.println(string);
        return "product/orderPage";
    }
        @GetMapping("") //상품 조회 페이지 [관리자]
    public String AdminProductPage(Model model , @PageableDefault(size = 5) Pageable pageable, @RequestParam (required = false, defaultValue = "0", value = "page")  int page) {
        System.out.println("RequestParam으로받은 page"+page);
        page = 100;
        Page<Product> listPage = productService.list(page);

        int totalPage = 7;// 총 페이지수
        System.out.println("토탈 페이지" + totalPage);
        model.addAttribute("productlists",listPage.getContent());
        model.addAttribute("totalPage",totalPage);

        model.addAttribute("products", productRepository.findAll(pageable));
        model.addAttribute("categorys", categoryRepository.findAll(pageable));
        return "productManager";
    }
    */
}
