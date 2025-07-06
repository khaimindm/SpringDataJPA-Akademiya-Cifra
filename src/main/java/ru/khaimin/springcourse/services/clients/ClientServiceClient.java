package ru.khaimin.springcourse.services.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.khaimin.springcourse.dto.ClientDTO;

// Feign-клиент для микросервиса клиент

@FeignClient(
        name = "client-service",
        url = "${client.service.url}"
)
public interface ClientServiceClient {

    @GetMapping("/client/{clientId}")
    ClientDTO getClientDTOByClientId(@PathVariable String clientId);

}
