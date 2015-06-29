package core.service;

import core.event.OnRegistrationCompleteEvent;
import core.event.OnResendEmailEvent;
import core.service.exception.EmailNotSentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * Created by Adrian on 14/05/2015.
 */
@Service
public class EmailService implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher eventPublisher;



    public void sendConfirmationEmail(OnRegistrationCompleteEvent onRegistrationCompleteEvent) throws EmailNotSentException {

        eventPublisher.publishEvent(onRegistrationCompleteEvent);
        System.out.println("Published event.");
    }

    public void resendConfirmationEmail(OnResendEmailEvent onResendEmailEvent) {
        eventPublisher.publishEvent(onResendEmailEvent);
        System.out.println("Published event.");
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        eventPublisher = applicationEventPublisher;
    }
}
