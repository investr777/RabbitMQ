package net.its.rmq.cmn.dao.person;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonDao extends JpaRepository<PersonEntity, String> {

    List<PersonEntity> findAll();

    Optional<PersonEntity> findByIdentifier(String identifier);
}
