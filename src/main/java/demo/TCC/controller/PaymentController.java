package demo.TCC.controller;

import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import demo.TCC.service.MercadoPagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final MercadoPagoService mercadoPagoService;

    @PostMapping("/create")
    public ResponseEntity<?> createPayment(@RequestBody Map<String, Object> paymentData) {
        try {
            String email = paymentData.get("email").toString();
            Payment payment = mercadoPagoService.createPayment(email);
            return ResponseEntity.ok(payment);
        } catch (MPApiException | MPException e) {
            int statusCode = e instanceof MPApiException ? ((MPApiException) e).getApiResponse().getStatusCode() : 500;
            return ResponseEntity.status(statusCode).body(e.getMessage());
        }
    }
    }

