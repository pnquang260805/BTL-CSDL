package com.ptit.csdl.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ptit.csdl.dto.request.creation.OrderCreationRequest;
import com.ptit.csdl.dto.response.OrderResponse;
import com.ptit.csdl.entity.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    /* 
     Sử dụng @Mapping để chuyển đổi
        Trong OrderMapper, chúng ta cần chuyển từ Order sang OrderResponse.
        Vì Order chứa cả đối tượng Customer, nhưng OrderResponse chỉ cần customerId, chúng ta dùng @Mapping để map customer.id sang customerId:
    Giải thích chi tiết @Mapping(source = "customer.id", target = "customerId")
    source = "customer.id":
        Lấy giá trị từ customer.id của Order (Order có một đối tượng Customer).
    target = "customerId":
        Gán giá trị customer.id vào trường customerId trong OrderResponse.
    Kết quả:
    Nếu Order có thông tin Customer, thì chỉ customerId được lấy ra.
    Không lấy toàn bộ Customer object, giúp tối ưu hiệu suất và tránh lộ thông tin không cần thiết.
    */
    @Mapping(source = "customer.id", target = "customerId") 
    public OrderResponse toResponse(Order order);
    public Order toOrder(OrderCreationRequest request);
    public List<OrderResponse> toResponse(List<Order> order);
}
