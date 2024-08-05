package com.project.redis.repository;

import com.project.redis.dto.ItemOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<ItemOrder, String> {

    Optional<List<ItemOrder>> getAllOrders();
}
