package com.project.redis.dto;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("order")
public class ItemOrder{
    @Id
    private String Id;
    private String item;
    private Integer count;
    private Long totalPrice;
    private String status;
}
