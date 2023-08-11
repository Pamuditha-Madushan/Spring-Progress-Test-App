package com.progress.test.repo;

import com.progress.test.entity.Order;
import com.progress.test.entity.OrderHasItem;

import com.progress.test.entity.OrderHasItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHasItemRepository extends JpaRepository<OrderHasItem, OrderHasItemId> {
}
