package com.progress.test.service.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.progress.test.dto.ItemDto;
import com.progress.test.dto.OrderDto;
import com.progress.test.dto.OrderHasItemDto;
import com.progress.test.dto.UserDto;
import com.progress.test.entity.*;
import com.progress.test.repo.ItemRepository;
import com.progress.test.repo.OrderHasItemRepository;
import com.progress.test.repo.OrderRepository;
import com.progress.test.service.process.CustomerService;
import com.progress.test.service.process.OrderService;
import com.progress.test.service.process.UserService;
import com.progress.test.util.BarcodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Writer;
import java.util.Hashtable;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderHasItemRepository orderHasItemRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BarcodeGenerator barcodeGenerator;

    @Override
    @Transactional
    public OrderDto createOrder(OrderDto orderDto, List<ItemDto> itemDtoList) {
        Order order = new Order();

        order.setOrderDate(orderDto.getOrderDate());
        order.setCost(orderDto.getCost());

        User user = userService.getUserById(orderDto.getUserId());
        Customer customer = customerService.getCustomerById(orderDto.getCustomerId());

        order.setUser(user);
        order.setCustomer(customer);

        orderRepository.save(order);

        for (ItemDto itemDto : itemDtoList) {
            Item item = new Item();

            item.setDescription(itemDto.getDescription());
            item.setQty(itemDto.getQty());
            item.setUnitPrice(itemDto.getUnitPrice());

            barcodeGenerator.generateAndSetBarCode(item);

            itemRepository.save(item);

            OrderHasItemId orderHasItemId = new OrderHasItemId();
            orderHasItemId.setOrder(order);
            orderHasItemId.setItem(item);

            OrderHasItem orderHasItem = new OrderHasItem();
            orderHasItem.setId(orderHasItemId);
            orderHasItem.setUnitPrice(item.getUnitPrice());
            orderHasItem.setQty(item.getQty());

            orderHasItemRepository.save(orderHasItem);
        }

        return orderDto;
    }


}
