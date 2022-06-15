package ru.ulstu.is.sbapp.student.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.ulstu.is.sbapp.student.model.Consignment;
import ru.ulstu.is.sbapp.student.model.Orderr;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ConsignmentService {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Consignment addConsignment(String consignmentName) {
        if (!StringUtils.hasText(consignmentName)) {
            throw new IllegalArgumentException("Consignment name is null or empty");
        }
        final Consignment consignment = new Consignment(consignmentName);
        em.persist(consignment);
        return consignment;
    }

    @Transactional(readOnly = true)
    public Consignment findConsignment(Long id) {
        final Consignment consignment = em.find(Consignment.class, id);
        if (consignment == null) {
            throw new EntityNotFoundException(String.format("Consignment with id [%s] is not found", id));
        }
        return consignment;
    }

    @Transactional(readOnly = true)
    public List<Consignment> findAllConsignments() {
        return em.createQuery("select s from Consignment s", Consignment.class)
                .getResultList();
    }

    @Transactional
    public Consignment updateConsignment(Long id, String consignmentName) {
        if (!StringUtils.hasText(consignmentName)) {
            throw new IllegalArgumentException("Consignment name is null or empty");
        }
        final Consignment currentConsignment = findConsignment(id);
        currentConsignment.setConsignmentName(consignmentName);
        return em.merge(currentConsignment);
    }

    @Transactional
    public Consignment deleteConsignment(Long id) {
        final Consignment currentConsignment = findConsignment(id);
        em.remove(currentConsignment);
        return currentConsignment;
    }

    @Transactional
    public void deleteAllConsignments() {
        em.createQuery("delete from Consignment").executeUpdate();
    }
    @Transactional
    public void addOrderrToConsignment(Consignment consignment, Orderr orderr) {
        if (consignment.toString().isEmpty()) {
            throw new IllegalArgumentException("Consignment is null or empty");
        }
        consignment.addOrderrs(orderr);
        em.merge(consignment);
    }
}
