package demo.TCC;

import com.mercadopago.*;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;

import java.math.BigDecimal;

public class TESTE {

    public static void main(String[] args) {
        MercadoPagoConfig.setAccessToken("TEST-4930437431978443-051908-bf6527a261c3e663085429a75815f67b-256585597");

        PaymentClient client = new PaymentClient();

        PaymentCreateRequest createRequest =
                PaymentCreateRequest.builder()
                        .transactionAmount(new BigDecimal(1000))
                        .token("your_cardtoken")
                        .description("description do produto")
                        .installments(1)
                        .paymentMethodId("pix")
                        .payer(PaymentPayerRequest.builder().email("LUCAS@gmail.com").build())
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
