package ru.ulstu.is.sbapp.student.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.ulstu.is.sbapp.student.model.Seller;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class SellerService {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Seller addSeller(String firstName, String lastName, String login) {
        if (!StringUtils.hasText(firstName) || !StringUtils.hasText(lastName)) {
            throw new IllegalArgumentException("Seller name is null or empty");
        }
        final Seller seller = new Seller(firstName, lastName, login);
        em.persist(seller);
        return seller;
    }

    @Transactional(readOnly = true)
    public Seller findSeller(Long id) {
        final Seller seller = em.find(Seller.class, id);
        if (seller == null) {
            throw new EntityNotFoundException(String.format("Seller with id [%s] is not found", id));
        }
        return seller;
    }

    @Transactional(readOnly = true)
    public List<Seller> findAllSellers() {
        return em.createQuery("select s from Seller s", Seller.class)
                .getResultList();
    }

    @Transactional
    public Seller updateSeller(Long id, String firstName, String lastName, String login) {
        if (!StringUtils.hasText(firstName) || !StringUtils.hasText(lastName)) {
            throw new IllegalArgumentException("Seller name is null or empty");
        }
        final Seller currentSeller = findSeller(id);
        currentSeller.setFirstName(firstName);
        currentSeller.setLastName(lastName);
        return em.merge(currentSeller);
    }

    @Transactional
    public Seller deleteSeller(Long id) {
        final Seller currentSeller = findSeller(id);
        em.remove(currentSeller);
        return currentSeller;
    }

    @Transactional
    public void deleteAllSellers() {
        em.createQuery("delete from Seller").executeUpdate();
    }
}
