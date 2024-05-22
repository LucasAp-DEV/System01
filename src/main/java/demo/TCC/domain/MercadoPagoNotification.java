package demo.TCC.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MercadoPagoNotification {
    private String id;
    private boolean liveMode;
    private String type;
    private String dateCreated;
    private String applicationId;
    private String userId;
    private String version;
    private String apiVersion;
    private String action;
    private NotificationData data;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NotificationData {
        private String id;
        private String status;
        private String externalReference;
    }
}
