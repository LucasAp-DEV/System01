package demo.TCC.service;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.resources.payment.Payment;
import demo.TCC.domain.local.Local;
import demo.TCC.domain.mercadoPago.MercadoPagoDTO;
import demo.TCC.domain.mercadoPago.ResponseMercadoPago;
import demo.TCC.repository.LocalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class MercadoPagoService {

    @Value("${mercadopago.access.token}")
    private String accessToken;

    private final LocalRepository localRepository;

    public Payment createPayment(MercadoPagoDTO data) throws MPException, MPApiException {
        try {
            String email = data.email();
            String id = data.id();
            MercadoPagoConfig.setAccessToken(accessToken);
            PaymentClient client = new PaymentClient();
            PaymentCreateRequest createRequest = PaymentCreateRequest.builder()
                    .transactionAmount(new BigDecimal(30)) //VALOR A SER PAGO
                    .description("PAGAMENTO") //DESCRIÇÃO
                    .paymentMethodId("pix") //TIPO DE PAGAMENTO
                    .payer(PaymentPayerRequest.builder().email(email).build())//EMAIL
                    .externalReference(id)
                    .build();
            return client.create(createRequest);
        } catch (MPApiException e) {
            throw new MPException(e.getMessage());
        }
    }

    public void updateTypeLocal(ResponseMercadoPago data) {
        String externalReference = data.external_reference();
        String status = data.status();

        if (externalReference == null || status == null) {
            System.out.println("Dados invalidos");
            return;
        }

        Long id = Long.valueOf(externalReference);
        var optionalLocal = localRepository.findById(id);

        if (optionalLocal.isPresent()) {
            if ("approved".equals(status)) { //VERIFICAR SE O STATUS É IGUAL APPROVED
                Local local = optionalLocal.get();
                local.setStatus("PATROCINADO");
                localRepository.save(local);
                System.out.println("Local Patrocinado");
            } else {
                System.out.println("Status nao e aprovado: " + status);
            }
        } else {
            System.out.println("Local nao encontrado para o id: " + id);
        }
    }


}


//DOCUMENTAÇÃO
//public static void main(String[] args) {
//    MercadoPagoConfig.setAccessToken("YOUR_ACCESS_TOKEN");
//
//    PaymentClient client = new PaymentClient();
//
//    PaymentCreateRequest createRequest =
//            PaymentCreateRequest.builder()
//                    .transactionAmount(new BigDecimal(1000))
//                    .token("your_cardtoken")
//                    .description("description")
//                    .installments(1)
//                    .paymentMethodId("visa")
//                    .payer(PaymentPayerRequest.builder().email("dummy_email").build())
//                    .build();
//
//    try {
//        Payment payment = client.create(createRequest);
//        System.out.println(payment);
//    } catch (MPApiException ex) {
//        System.out.printf(
//                "MercadoPago Error. Status: %s, Content: %s%n",
//                ex.getApiResponse().getStatusCode(), ex.getApiResponse().getContent());
//    } catch (MPException ex) {
//        ex.printStackTrace();
//    }
//}
//}


