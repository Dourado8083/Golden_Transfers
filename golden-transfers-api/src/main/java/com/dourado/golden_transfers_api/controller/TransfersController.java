package com.dourado.golden_transfers_api.controller;

import com.dourado.golden_transfers_api.dto.TransfersRequestDto;
import com.dourado.golden_transfers_api.dto.TransfersResponseDTO;
import com.dourado.golden_transfers_api.services.TransfersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/transfers/V1")
public class TransfersController {

    @Autowired
    private TransfersService service;

    @PostMapping
    public ResponseEntity<TransfersResponseDTO> agendar(
            @Valid @RequestBody TransfersRequestDto dto) {
        TransfersResponseDTO response = service.agendar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<TransfersResponseDTO>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }
}