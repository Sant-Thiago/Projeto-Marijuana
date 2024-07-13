package com.example.projetoplanta.Duende.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projetoplanta.Duende.Module.DuendeModel;

@Repository
public interface DuendeRepository extends JpaRepository<DuendeModel, Long> {
}
