package com.TCC.demo;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import org.junit.Test;

import java.math.BigDecimal;

public class TESTE {
    //@Test
    public void testarApiMercadoPago() {
        MercadoPagoConfig.setAccessToken("TEST-1120009213344655-021507-b9f77997850821590cc1b2bc68f6e964-150846749");

        PaymentClient client = new PaymentClient();

        PaymentCreateRequest createRequest =
                PaymentCreateRequest.builder()
                        .transactionAmount(new BigDecimal(10))
                        .description("description do produto")
                        .paymentMethodId("pix")
                        .payer(PaymentPayerRequest.builder().email("marcos@moleniuk.com").build())
                        .build();

        try {
            Payment payment = client.create(createRequest);
            System.out.println(payment);
        } catch (MPApiException ex) {
            System.out.printf(
                    "MercadoPago Error. Status: %s, Content: %s%n",
                    ex.getApiResponse().getStatusCode(), ex.getApiResponse().getContent());
        } catch (MPException ex) {
            ex.printStackTrace();
        }
    }
}
