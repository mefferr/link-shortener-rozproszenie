package com.bntech.googleintegrator.manager.data.repository;

import com.bntech.googleintegrator.manager.data.persistence.ShorthandLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShorthandLinkRepository extends JpaRepository<ShorthandLink, String> {
    List<ShorthandLink> findAll();
    Optional<ShorthandLink> findById(String id);
}
