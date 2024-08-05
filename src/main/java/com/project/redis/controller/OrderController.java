package com.project.redis.controller;

import com.project.redis.dto.ItemOrder;
import com.project.redis.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/saveOrder")
    public ResponseEntity<?> createOrder(@RequestBody ItemOrder itemOrder) {
        return orderService.save(itemOrder);
    }

    @GetMapping("/getAllOrders")
    public ResponseEntity<List<ItemOrder>> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}/getOneOrder")
    public ResponseEntity<ItemOrder> getOneOrder(@PathVariable("id") String id) {
        return orderService.getOrderById(id);
    }

    @PutMapping("/{id}/updateOrder")
    public ResponseEntity<?> updateOrder(@PathVariable("id") String id, @RequestBody ItemOrder order) {
        return orderService.updateOrder(id, order);
    }

    @DeleteMapping("/{id}/deleteOrder")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") String id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
