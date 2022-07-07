package ReBack.core.controller;

import ReBack.core.data.*;
import ReBack.core.repository.*;
import ReBack.core.security.SecurityUser;
import ReBack.core.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;


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
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    CommentFilesRepository commentFilesRepository;

    @GetMapping("/product/category")
    public String selectCate(@AuthenticationPrincipal SecurityUser principal, Model model,@RequestParam(required = false) Long categoryCode) {
        List<Product> selectProduct;


        if (categoryCode == 0) {
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

    @GetMapping("/product/search")
    public String selectCate(@AuthenticationPrincipal SecurityUser principal, Model model, @RequestParam(required = false) String search) {

        System.out.println("search: " + search);
        model.addAttribute("products", productRepository.findBySearch(search));
        model.addAttribute("categorys", categoryRepository.findAll());

        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }
        return "product/productPage";
    }

    @GetMapping("/product") //상품 조회 페이지 [모든사람]
    public String ProductPage(@AuthenticationPrincipal SecurityUser principal, Model model, @RequestParam(required = false) Long id,
                              @PageableDefault(sort = "productCode", size = 5, direction = Sort.Direction.DESC) Pageable pageable) {

        Page<Product> list = productService.pageList(pageable);
        model.addAttribute("hasNext", list.hasNext());
        model.addAttribute("hasPrev", list.hasPrevious());

        System.out.println("productRepository.findAll(pageable) :: " + productRepository.findAll(pageable));

        model.addAttribute("products", productRepository.findAll(pageable));
        model.addAttribute("categorys", categoryRepository.findAll());
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        System.out.println("id값:" + id);
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }
        return "product/productPage";
    }

    @GetMapping("/product/manager") // 상품 관리자 페이지 [업체]
    public String productManager(@AuthenticationPrincipal SecurityUser principal, Model model, @RequestParam(required = false) Member memberCode) {

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
    public String selectCate2(@AuthenticationPrincipal SecurityUser principal, Model model,@RequestParam(required = false) Member memberCode,@RequestParam(required = false) Long categoryCode) {

        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }


        System.out.println(memberCode.getMemberCode());
        Long memberCode1 = memberCode.getMemberCode();
        List<Product> selectProduct;

        if (categoryCode == 0) {
            selectProduct = productRepository.findByMemberCode(memberCode);
            System.out.println(selectProduct.size());
            model.addAttribute("addproducts", selectProduct);
        } else {
            selectProduct = productRepository.selectCate2(categoryCode, memberCode1); //memberCode + categoryCode
            System.out.println(selectProduct.size());
            model.addAttribute("addproducts", selectProduct);
        }

        model.addAttribute("categorys", categoryRepository.findAll());


        return "product/productManager";
    }

    @GetMapping("/product/manage") //상품 관리 페이지 [수정 및 삭제] , [업체]
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

    @GetMapping("/product/add") //상품 등록 페이지 [업체]
    public String productAdd(@AuthenticationPrincipal SecurityUser principal,Model model) {
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("materials", materialRepository.findAll());
        return "product/productAdd";
    }

    @GetMapping("/product/details") // 상품 상세보기 [모든사람]
    public String productDetails(@AuthenticationPrincipal SecurityUser principal, Model model, @RequestParam(required = false) Long id) {

        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }
        Product product = productRepository.findById(id).orElse(null);
        if(commentRepository.findByReviews(id) != null){
            model.addAttribute("reviews", commentRepository.findByReviews(id));
            System.out.println(commentRepository.findByReviews(id).size());
        }
        else{
            model.addAttribute("reviews", null);

        }
//        System.out.println(commentRepository.findByReviews(id).get(0));
//        model.addAttribute("files", commentFilesRepository.findByFile(id));
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

        System.out.println("ordersRepository.find3ByMemberCode(memberCode) :::  " + refundRepository.find3ByMemberCode(memberCode));
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
        model.addAttribute("refundList", refundRepository.find2ByMemberCode(memberCode));


        return "product/refundList";
    }

    @GetMapping("/product/refundManager") // 상품 환불 관리 [업체]
    public String refundManager(@AuthenticationPrincipal SecurityUser principal, Model model, @RequestParam(name = "memberCode", required = false) Member memberCode) {
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }
        model.addAttribute("refundList", refundRepository.findByMemberCode(memberCode));

        return "product/refundManager";
    }

    @GetMapping("/product/refundDetail") // 상품 환불 관리 상세보기 [업체]
    public String refundDetail(@AuthenticationPrincipal SecurityUser principal, Model model, @RequestParam(name = "refundCode", required = false) Long refundCode) {
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }
        Refund refund = refundRepository.findById(refundCode).orElse(null);
        System.out.println("refund :: " + refund);
        model.addAttribute("Detail", refund);
        return "/product/refundDetail";
    }

    @GetMapping("/product/paymentCompleted") // 결제완료 페이지
    public String paymentCompleted(@AuthenticationPrincipal SecurityUser principal, Model model, @RequestParam(name = "memberCode", required = false) Long memberCode,@RequestParam(name = "productCode", required = false) Long productCode) {
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }
        List<Orders> orders = ordersRepository.findByBuy(memberCode,productCode);
        System.out.println(" ::: " + orders.size());
        model.addAttribute("order",ordersRepository.findByBuy(memberCode,productCode));
//        model.addAttribute("order", ordersRepository.findById(ordersCode));
        return "/product/paymentCompleted";
    }

//    @GetMapping("/product/CartCompleted") // 장바구니 상품 결제완료 페이지
//    public String CartCompleted(@AuthenticationPrincipal SecurityUser principal, Model model, @RequestParam(name = "memberCode", required = false) Long memberCode) {
//        if (principal != null) {
//            model.addAttribute("principal", principal.getMember());
//            model.addAttribute("role", principal.getMember().getRole().getDescription());
//        }
//        List<Orders> orders = ordersRepository.findByCartBuy(memberCode);
//        System.out.println(" ::: " + orders.size());
//        model.addAttribute("order",ordersRepository.findByCartBuy(memberCode));
////        model.addAttribute("order", ordersRepository.findById(ordersCode));
//        return "/product/CartCompleted";
//    }

    @GetMapping("/product/deliveryCheck") // 배송현황 페이지
    public String deliveryCheck(@AuthenticationPrincipal SecurityUser principal, Model model, @RequestParam(name = "ordersCode", required = false) Long ordersCode) {
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }
        Optional<Orders> orders = ordersRepository.findById(ordersCode);
        System.out.println(" ::: " + orders.get());
        model.addAttribute("order", ordersRepository.findById(ordersCode));
        return "/product/deliveryCheck";
    }

    @GetMapping("/product/cart") // 장바구니 페이지
    public String cart(@AuthenticationPrincipal SecurityUser principal, Model model, @RequestParam(required = false) Member memberCode) {
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }
        System.out.println("memberCode :: "+memberCode);
//        Optional<Cart> cartPrice = cartRepository.findByMemberCodeAll(memberCode);
//        System.out.println("cartPrice :: "+cartPrice.get());
        System.out.println("이건 찍히나 ??"+cartRepository.findByMemberCode(memberCode));
        Integer cart = cartRepository.updateCheckState();
        model.addAttribute("cart",cartRepository.findByMemberCode(memberCode));
        model.addAttribute("cartPrice",cartRepository.findByMemberCodeAll(memberCode));
        model.addAttribute("index",cartRepository.findByMemberCode(memberCode).size());

        return "/product/cart";
    }

    @GetMapping("/product/cartBuy") // 장바구니 상품 구매
    public String cartBuy(@AuthenticationPrincipal SecurityUser principal, Model model, @RequestParam(required = false) Member memberCode) {
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }
        System.out.println("memberCode :: "+memberCode);
        List<Cart> cart = cartRepository.findByCart(memberCode);
        Optional<Cart> CartPrice = cartRepository.findByCartPrice(memberCode);
        System.out.println("cart :: "+cart.size());

        model.addAttribute("cart", cart);
        model.addAttribute("CartPrice", CartPrice);
        model.addAttribute("index",cartRepository.findByCart(memberCode).size());

        return "/product/cartBuy";
    }

    @GetMapping("/product/review") // 장바구니 페이지
    public String review(@AuthenticationPrincipal SecurityUser principal, Model model, @RequestParam(required = false) Long productCode) {
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }

        Optional<Product> product =productRepository.findById(productCode);
        System.out.println("productRepository.findById(productCode)" + product.get());
        model.addAttribute("product", product.get());


        return "/product/review";
    }

}
