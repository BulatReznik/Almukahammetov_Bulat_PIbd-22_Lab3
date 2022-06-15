package ru.ulstu.is.sbapp.student.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.ulstu.is.sbapp.student.model.Orderr;
import ru.ulstu.is.sbapp.student.model.Request;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class RequestService {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Request addRequest(String requestName, String requestDate) {
        if (!StringUtils.hasText(requestName) || !StringUtils.hasText(requestDate)) {
            throw new IllegalArgumentException("Request name is null or empty");
        }
        final Request request = new Request(requestName, requestDate);
        em.persist(request);
        return request;
    }

    @Transactional(readOnly = true)
    public Request findRequest(Long id) {
        final Request request = em.find(Request.class, id);
        if (request == null) {
            throw new EntityNotFoundException(String.format("Request with id [%s] is not found", id));
        }
        return request;
    }

    @Transactional(readOnly = true)
    public List<Request> findAllRequests() {
        return em.createQuery("select s from Request s", Request.class)
                .getResultList();
    }

    @Transactional
    public Request updateRequest(Long id, String requestName, String requestDate) {
        if (!StringUtils.hasText(requestName) || !StringUtils.hasText(requestDate)) {
            throw new IllegalArgumentException("Request name is null or empty");
        }
        final Request currentRequest = findRequest(id);
        currentRequest.setRequestName(requestName);
        currentRequest.setRequestDate(requestDate);
        return em.merge(currentRequest);
    }

    @Transactional
    public Request deleteRequest(Long id) {
        final Request currentRequest = findRequest(id);
        em.remove(currentRequest);
        return currentRequest;
    }
    @Transactional
    public void addOrderrToRequest(Orderr orderr, Request request) {
        if(orderr.toString().isEmpty()){
            throw  new IllegalArgumentException("Orderr is null or empty");
        }
        request.addOrderr(orderr);
        em.merge(request);
    }
    @Transactional
    public void deleteAllRequests() {
        em.createQuery("delete from Request").executeUpdate();
    }
}
