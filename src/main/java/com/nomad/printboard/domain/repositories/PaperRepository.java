package com.nomad.printboard.domain.repositories;

import com.nomad.printboard.domain.Paper;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PaperRepository extends CrudRepository<Paper, Long> {

    List<Paper> findByTitleContaining(String title);
    Optional<Paper> findByTitle(String title);

}
