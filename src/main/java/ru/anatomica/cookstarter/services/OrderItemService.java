package ru.anatomica.cookstarter.services;

import ru.anatomica.cookstarter.entities.OrderItem;
import ru.anatomica.cookstarter.entities.dtos.OrderItemDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemService {
    public List<OrderItemDto> mapEntityListToDtoList(List<OrderItem> orderItemList) {
        return orderItemList.stream().map(OrderItemDto::new).collect(Collectors.toList());
    }
}
