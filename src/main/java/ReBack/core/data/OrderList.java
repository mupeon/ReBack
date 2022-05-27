package ReBack.core.data;

import lombok.*;

import javax.persistence.*;

@Entity
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderList {
    @SequenceGenerator(name = "order_list_seq_generator",
            sequenceName = "order_list_seq",
            initialValue = 1, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_list_seq_generator")
    private Long orderListCode;

//    @Column(nullable = false)
//    private int orderListCount;

    @Column(nullable = false)
    private int orderListAmount;

//
//    @Column(nullable = false, name="order_list_mileage_point")
//    private int orderListPoint;
//
//    @Column(nullable = false, length=10, name="order_list_purchase_status")
//    private String orderListStatus;
//
//    @Column(nullable = false)
//    private int OrderListReviewPoint;


    @ManyToOne
    @JoinColumn(name="orders_code")
    private Orders ordersCode;

    @ManyToOne
    @JoinColumn(name="product_code")
    private Product productCode;

}
