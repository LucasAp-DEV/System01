package demo.TCC.service;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.resources.payment.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;

import java.math.BigDecimal;

@Service
public class MercadoPagoService {

    @Value("${mercadopago.access.token}")
    private String accessToken;

    public Payment createPayment(BigDecimal amount, String token, String description, int installments, String paymentMethodId, String email) throws MPException, MPApiException {
        MercadoPagoConfig.setAccessToken(accessToken);

        PaymentClient client = new PaymentClient();

        PaymentCreateRequest createRequest = PaymentCreateRequest.builder()
                .transactionAmount(amount)
                .token(token)
                .description(description)
                .installments(installments)
                .paymentMethodId(paymentMethodId)
                .payer(PaymentPayerRequest.builder().email(email).build())
                .build();

        return client.create(createRequest);
    }
}
