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
        MercadoPagoConfig.setAccessToken("TEST-7940158791423693-052413-0b211874dbbb841daa195e508a2c74dd-256585597");

        PaymentClient client = new PaymentClient();

        PaymentCreateRequest createRequest = PaymentCreateRequest.builder()
                .transactionAmount(new BigDecimal("1000")) // Use um valor decimal
                .description("descrição do produto")
                .installments(1)
                .paymentMethodId("pix") // Mudando para "pix" se estiver testando com PIX
                .payer(PaymentPayerRequest.builder().email("LUCAS@gmail.com").build())
                .build();

        try {
            System.out.println("Criando pagamento com a seguinte requisição: " + createRequest);

            Payment payment = client.create(createRequest);

            System.out.println("Resposta do pagamento: " + payment);

        } catch (MPApiException ex) {
            System.out.printf(
                    "MercadoPago Error. Status: %s, Content: %s%n",
                    ex.getApiResponse().getStatusCode(), ex.getApiResponse().getContent());
        } catch (MPException ex) {
            ex.printStackTrace();
        }
    }
}
