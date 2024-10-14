package org.example.notification;

import lombok.Getter;
import org.example.users.dto.UsuarioDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class UserNotifications implements Notifications<UsuarioDto> {
    private final Logger logger = LoggerFactory.getLogger(UserNotifications.class);

    private final Sinks.Many<Notification<UsuarioDto>> notificationsSink = Sinks.many().replay().limit(1);
    @Getter
    private final Flux<Notification<UsuarioDto>> notifications = notificationsSink.asFlux().onBackpressureDrop();
    /**
     * Envia una notificacion de un usuario.
     * @param notification la notificaci n a enviar
     * @author Alvaro Herrero, Javier Ruiz, Javier Hernandez, Raul Fernandez, Yahya El Hadri, Samuel Cortes.
     * @version 1.0
     */
    @Override
    public void send(Notification<UsuarioDto> notification) {
        logger.debug("Send notification: {}", notification);
        notificationsSink.tryEmitNext(notification);
    }
}
