package ru.rrenat358.stats.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rrenat358.api.core.OrderDto;
import ru.rrenat358.stats.integrations.CoreServiceIntegration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrdersStatsService {

    private final CoreServiceIntegration coreServiceIntegration;


    public Integer getNumberOfOrdersByCurrentUser(String username) {
        if (username == null || username.isBlank()) {
            return 0;
        }
        return coreServiceIntegration.getNumberOfOrdersByCurrentUser(username);
    }

    public List<OrderDto> getAllOrdersByCurrentUser(String username) {
//        if (username == null || username.isBlank()) {
////            return null;
//            return new ArrayList<>();
//        }
        return coreServiceIntegration.getAllOrdersByCurrentUser(username);
    }


}
