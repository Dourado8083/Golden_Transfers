package com.dourado.golden_transfers_api.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dourado.golden_transfers_api.model.Transfers;

@Repository
public interface  TransfersRepository extends JpaRepository<Transfers, Long> {}
