package com.project.redis.service;

import com.project.redis.dto.ItemOrder;
import com.project.redis.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    public ResponseEntity<?> save(ItemOrder itemOrder) {
        if (itemOrder != null) {
            ItemOrder savedOrder = orderRepository.save(itemOrder);
            return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("주문한 아이템이 비어입니다.", HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<List<ItemOrder>> getAllOrders() {
        Optional<List<ItemOrder>> optionalOrders = orderRepository.getAllOrders();
        if (optionalOrders.isEmpty() || optionalOrders.get().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(optionalOrders.get(), HttpStatus.OK);
        }
    }

    public ResponseEntity<ItemOrder> getOrderById(String id) {
        Optional<ItemOrder> optionalOrder = orderRepository.findById(id);
        return optionalOrder.map(itemOrder ->
                new ResponseEntity<>(itemOrder, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }



    public ResponseEntity<?> updateOrder(String id, ItemOrder order) {
        Optional<ItemOrder> findOrder = orderRepository.findById(id);
        if (findOrder.isPresent()) {
            ItemOrder existingOrder = findOrder.get();

            if (order.getItem() != null) existingOrder.setItem(order.getItem());
            if (order.getCount() != null) existingOrder.setCount(order.getCount());
            if (order.getTotalPrice() != null) existingOrder.setTotalPrice(order.getTotalPrice());
            if (order.getStatus() != null) existingOrder.setStatus(order.getStatus());
            orderRepository.save(existingOrder);
            return new ResponseEntity<>(existingOrder, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("주문 정보 업데이트에 실패했습니다", HttpStatus.BAD_REQUEST);
        }
    }


    public void deleteOrder(String id) {
        Optional<ItemOrder> findOrder = orderRepository.findById(id);
        if(findOrder.isPresent()){
            orderRepository.deleteById(id);
            ResponseEntity.noContent().build();
        }
        else {
            new ResponseEntity<>("삭제할 주문 정보가 없습니다", HttpStatus.BAD_REQUEST);
        }
    }
}
