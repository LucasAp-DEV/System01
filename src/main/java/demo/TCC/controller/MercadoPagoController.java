package demo.TCC.controller;

import demo.TCC.domain.MercadoPagoNotification;
import demo.TCC.service.LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mercadopago")
public class MercadoPagoController {

    @Autowired
    private LocalService service;

    @PostMapping("/notifications")
    public ResponseEntity<String> receiveNotification(@RequestBody MercadoPagoNotification notification) {
        return service.processNotification(notification);
    }
}
