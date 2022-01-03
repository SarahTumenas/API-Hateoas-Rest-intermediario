package com.github.SarahTumenas.restintermediario.repository;

import com.github.SarahTumenas.restintermediario.entity.SoldadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoldadoRepository  extends JpaRepository<SoldadoEntity, Long> {
}
