import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../../core/services/api.services';
import { Observable } from 'rxjs';
import { TransfersResponse } from '../../core/interfaces/TransfersResponse';
import { TransfersRequest } from '../../core/interfaces/TransfersRequest';

@Component({
  selector: 'app-transfers',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './transfers.html',
  styleUrl: './transfers.scss',
})
export class Transfers implements OnInit {
  transfers$!: Observable<TransfersResponse[]>;


  formModel = signal<TransfersRequest>({
    contaOrigem: '',
    contaDestino: '',
    valor: 0,
    dataTransferencia: ''
  });

  constructor(private apiService: ApiService) {}

  ngOnInit(): void {
    this.buscarTransfers();
    this.inicializarDataHoje();
  }

  buscarTransfers(): void {
    this.transfers$ = this.apiService.listar();
  }

  private obterDataHojeString(): string {
    const hoje = new Date();
    const ano = hoje.getFullYear();
    const mes = String(hoje.getMonth() + 1).padStart(2, '0');
    const dia = String(hoje.getDate()).padStart(2, '0');
    return `${ano}-${mes}-${dia}`;
  }

  inicializarDataHoje(): void {
    const hojeStr = this.obterDataHojeString();
    this.formModel.update(form => ({ ...form, dataTransferencia: hojeStr }));
  }


  atualizarCampo(campo: keyof TransfersRequest, valor: any): void {
    this.formModel.update(form => ({
      ...form,
      [campo]: valor
    }));
  }

  agendar(): void {
    const dadosEnvio = this.formModel();

    this.apiService.agendar(dadosEnvio).subscribe({
      next: () => {
        this.buscarTransfers();
        this.resetForm();
      },
      error: (err) => {
        alert(err.message || 'Erro ao realizar agendamento.');
      }
    });
  }

  private resetForm(): void {
    this.formModel.set({
      contaOrigem: '',
      contaDestino: '',
      valor: 0,
      dataTransferencia: this.obterDataHojeString()
    });
  }
}