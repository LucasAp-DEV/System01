package demo.TCC.controller;

import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import demo.TCC.service.MercadoPagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class MercadoLivreController {

    private final MercadoPagoService service;

    @PostMapping("/create")
    public ResponseEntity<?> createPayment(@RequestBody Map<String, Object> paymentData) {
        try {
            String email = paymentData.get("email").toString();
            Payment payment = service.createPayment(email);
            return ResponseEntity.ok(payment);
        } catch (MPApiException | MPException e) {
            int statusCode = e instanceof MPApiException ? ((MPApiException) e).getApiResponse().getStatusCode() : 500;
            return ResponseEntity.status(statusCode).body(e.getMessage());
        }
    }
}

