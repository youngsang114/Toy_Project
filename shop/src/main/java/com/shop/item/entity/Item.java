package com.shop.item.entity;

import com.shop.item.dto.response.ItemFormDto;
import com.shop.item.entity.enums.ItemSellStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "item")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {

    @Id
    @Column(name="item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;       //상품 코드

    @Column(nullable = false, length = 50)
    private String itemNm; //상품명

    @Column(name="price", nullable = false)
    private int price; //가격

    @Column(nullable = false)
    private int stockNumber; //재고수량

    @Lob
    @Column(nullable = false)
    private String itemDetail; //상품 상세 설명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus; //상품 판매 상태

    public void updateItem(ItemFormDto itemFormDto){
        // 원래라면, update 부분도 전용 dto 객체를 만들어야되지만, 예제라 응답 객체와 같게 만들어서 코드를 재활용 했다
        this.id= itemFormDto.getId();
        this.itemNm = itemFormDto.getItemNm();
        this.price = itemFormDto.getPrice();
        this.stockNumber = itemFormDto.getStockNumber();
        this.itemDetail= itemFormDto.getItemDetail();
    }
}
