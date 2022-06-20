package ReBack.core.data;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
//@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @SequenceGenerator(name = "orders_seq_generator",
            sequenceName = "orders_seq",
            initialValue = 1, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_seq_generator")
    private Long ordersCode; //주문서 코드

//    @Column(name="order_pay_total_amount", nullable = false, length=7)
//    private int ordersTotal; //총 가격

    @Column(name="order_pay_total_stock", nullable = false, length=7)
    private int ordersStock; //총 수량

    @Column(length=100)
    private String ordersRequest; // 요청 사항

    @Column(name="orders_receiver_address", nullable = false)
    private String ordersAddress; // 수신자 주소

    @Column(nullable = false, length=10)
    private String ordersReceiverName; //수진사 이름

    @Column(nullable = false, length=13, name="orders_receiver_phone_number")
    private String ordersPhoneNum; // 수신자 번호

    @Column(nullable = false)
    private OrdersState ordersState; //주문 상태

    @Column(nullable = false, name="orders_delivery_status", length=1)
    private int deliveryStatus; // 배송상태

    @Column(nullable = true)
    private RefundState refundStatus;

    @Column(nullable = false, name="orders_delivery_company_information", length = 20)
    private String ordersDeliveryInfo; //배송회사 정보

    @Column(nullable = false, name="pay_time")
    private LocalDateTime payTime; // 결제시간

    @Column(nullable = false, name="pay_amount", length = 20)
    private int payAmount; //결제 금액

//    @OneToMany(mappedBy = "ordersCode",
//            fetch = FetchType.LAZY,
//            cascade = CascadeType.PERSIST,
//            orphanRemoval = true)
//    private Set<OrderList> orderList = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_code")
    private Member memberCode; //


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_design_code")
    private MemberDesign memberDesignCode; // 회원 디자인 코드

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_code")
    private Product productCode;



//    @ManyToOne
//    @JoinColumn(name="pay_code")
//    private Pay payCode; // 결제 고유 코드

}
