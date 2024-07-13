package com.example.projetoplanta.imagem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.projetoplanta.imagem.Module.ImagemModel;


@Repository
public interface ImagemRepository extends JpaRepository<ImagemModel, Integer> {
    @Query("SELECT i FROM ImagemModel i WHERE i.fkPlanta.id = :fkPlanta")
    List<ImagemModel> findByFkPlanta(@Param("fkPlanta") String fkPlanta);
}
