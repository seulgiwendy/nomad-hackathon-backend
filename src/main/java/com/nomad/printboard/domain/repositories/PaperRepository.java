package com.nomad.printboard.domain.repositories;

import com.nomad.printboard.domain.Paper;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PaperRepository extends CrudRepository<Paper, Long> {

    Optional<Paper> findByTitleContaining(String title);

}
