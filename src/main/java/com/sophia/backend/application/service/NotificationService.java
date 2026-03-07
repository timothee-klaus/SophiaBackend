package com.sophia.backend.application.service;
import com.sophia.backend.domain.model.Notification;
import com.sophia.backend.domain.repository.NotificationRepository;
import com.sophia.backend.domain.enums.TypeNotification;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }
    public Optional<Notification> findById(Long id) {
        return notificationRepository.findById(id);
    }
    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }
    public List<Notification> findByDestinataireId(UUID destinataireId) {
        return notificationRepository.findByDestinataireId(destinataireId);
    }
    public List<Notification> findUnreadByDestinataireId(UUID destinataireId) {
        return notificationRepository.findUnreadByDestinataireId(destinataireId);
    }
    public Notification create(Notification notification) {
        return notificationRepository.save(notification);
    }
    public Notification update(Notification notification) {
        return notificationRepository.save(notification);
    }
    public void delete(Long id) {
        notificationRepository.deleteById(id);
    }
    public Notification notifierReçuDisponible(UUID directeurId, UUID secretaireId, String lien) {
        Notification notification = new Notification();
        notification.setType(TypeNotification.RECU_DISPONIBLE);
        notification.setExpediteurId(secretaireId);
        notification.setDestinataireId(directeurId);
        notification.setContenu("Un nouveau reçu est disponible");
        notification.setLien(lien);
        notification.setLu(false);
        return this.create(notification);
    }
    public Notification alerterImpayesCritiques(UUID directeurId, int nombreImpayés) {
        Notification notification = new Notification();
        notification.setType(TypeNotification.ALERTE_IMPAYES);
        notification.setExpediteurId(UUID.randomUUID());
        notification.setDestinataireId(directeurId);
        notification.setContenu("Alerte : " + nombreImpayés + " élèves en situation d'impayés");
        notification.setLu(false);
        return this.create(notification);
    }
    public Notification marquerCommeLue(Long notificationId) {
        return notificationRepository.findById(notificationId).map(notification -> {
            notification.setLu(true);
            return notificationRepository.save(notification);
        }).orElseThrow(() -> new IllegalArgumentException("Notification non trouvée"));
    }
    public List<Notification> obtenirNotificationsNonLues(UUID utilisateurId) {
        return this.findUnreadByDestinataireId(utilisateurId);
    }
}
