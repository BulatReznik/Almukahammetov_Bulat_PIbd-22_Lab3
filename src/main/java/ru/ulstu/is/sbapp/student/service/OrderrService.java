package ru.ulstu.is.sbapp.student.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.ulstu.is.sbapp.student.model.Consignment;
import ru.ulstu.is.sbapp.student.model.Orderr;
import ru.ulstu.is.sbapp.student.model.Request;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class OrderrService {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Orderr addOrderr(String orderName, String orderDate) {
        if (!StringUtils.hasText(orderName) || !StringUtils.hasText(orderDate)) {
            throw new IllegalArgumentException("Orderr name is null or empty");
        }
        final Orderr orderr = new Orderr(orderName, orderDate);
        em.persist(orderr);
        return orderr;
    }

    @Transactional(readOnly = true)
    public Orderr findOrderr(Long id) {
        final Orderr orderr = em.find(Orderr.class, id);
        if (orderr == null) {
            throw new EntityNotFoundException(String.format("Orderr with id [%s] is not found", id));
        }
        return orderr;
    }

    @Transactional(readOnly = true)
    public List<Orderr> findAllOrderrs() {
        return em.createQuery("select s from Orderr s", Orderr.class)
                .getResultList();
    }

    @Transactional
    public Orderr updateOrderr(Long id, String orderName, String orderDate) {
        if (!StringUtils.hasText(orderName) || !StringUtils.hasText(orderDate)) {
            throw new IllegalArgumentException("Orderr name is null or empty");
        }
        final Orderr currentOrderr = findOrderr(id);
        currentOrderr.setOrderrName(orderName);
        currentOrderr.setOrderDate(orderDate);
        return em.merge(currentOrderr);
    }

    @Transactional
    public Orderr deleteOrderr(Long id) {
        final Orderr currentOrderr = findOrderr(id);
        em.remove(currentOrderr);
        return currentOrderr;
    }

    @Transactional
    public void deleteAllOrderrs() {
        em.createQuery("delete from Orderr").executeUpdate();
    }

    @Transactional
    public void addRequestToOrderr(Request request, Orderr orderr) {
        if (request.toString().isEmpty()) {
            throw new IllegalArgumentException("Request is null or empty");
        }
        orderr.addRequest(request);
        em.merge(orderr);
    }

    @Transactional
    public void addConsignmentToOrders(Consignment consignment, Orderr orderr) {
        if (consignment.toString().isEmpty()) {
            throw new IllegalArgumentException("Consignment is null or empty");
        }
        orderr.addConsignment(consignment);
        em.merge(orderr);
    }
}
