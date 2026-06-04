package com.dourado.golden_transfers_api.services;

import com.dourado.golden_transfers_api.dto.TransfersRequestDTO;
import com.dourado.golden_transfers_api.dto.TransfersResponseDTO;
import com.dourado.golden_transfers_api.model.Transfers;
import com.dourado.golden_transfers_api.repository.TransfersRepository;
import com.dourado.golden_transfers_api.strategy.TaxaStrategy;
import com.dourado.golden_transfers_api.strategy.TaxaStrategyFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import com.dourado.golden_transfers_api.dto.TransfersRequestDto;

@ExtendWith(MockitoExtension.class)
class TransfersServiceTest {
//Em andamento
}