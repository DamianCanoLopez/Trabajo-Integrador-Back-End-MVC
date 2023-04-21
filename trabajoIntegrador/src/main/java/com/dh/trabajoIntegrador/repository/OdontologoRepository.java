package com.dh.trabajoIntegrador.repository;

import com.dh.trabajoIntegrador.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.nio.file.LinkOption;

public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {
}
