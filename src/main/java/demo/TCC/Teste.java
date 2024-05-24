package demo.TCC;

import com.mercadopago.*;

import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import java.math.BigDecimal;

public class Teste {

    public static void main(String[] args) {
        MercadoPagoConfig.setAccessToken("TEST-1120009213344655-021507-b9f77997850821590cc1b2bc68f6e964-150846749");

        PaymentClient client = new PaymentClient();

        PaymentCreateRequest createRequest =
                PaymentCreateRequest.builder()
                        .transactionAmount(new BigDecimal(1000))
                        .token("your_cardtoken")
                        .description("descrição do produto")
                        .installments(1)
                        .paymentMethodId("pix")
                        .payer(PaymentPayerRequest.builder().email("teste@gmail.com").build())
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