package demo.TCC.controller;

import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import demo.TCC.service.MercadoPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private MercadoPagoService mercadoPagoService;

    @PostMapping("/create")
    public ResponseEntity<?> createPayment(@RequestBody Map<String, Object> paymentData) {
        try {
            BigDecimal amount = new BigDecimal(paymentData.get("amount").toString());
            String token = paymentData.get("token").toString();
            String description = paymentData.get("description").toString();
            int installments = Integer.parseInt(paymentData.get("installments").toString());
            String paymentMethodId = paymentData.get("paymentMethodId").toString();
            String email = paymentData.get("email").toString();

            Payment payment = mercadoPagoService.createPayment(amount, token, description, installments, paymentMethodId, email);
            return ResponseEntity.ok(payment);
        } catch (MPApiException | MPException e) {
            return ResponseEntity.status(e instanceof MPApiException ? ((MPApiException) e).getApiResponse().getStatusCode() : 500).body(e.getMessage());
        }
    }
}

