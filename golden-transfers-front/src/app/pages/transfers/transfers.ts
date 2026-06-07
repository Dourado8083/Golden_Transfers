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

somenteNumero(event: KeyboardEvent): boolean {
    return /[0-9]/.test(event.key);
  }

  atualizarCampo(campo: keyof TransfersRequest, valor: any): void {
    if (campo === 'contaOrigem' || campo === 'contaDestino') {
      valor = valor.replace(/\D/g, '').slice(0, 5);
    }
    this.formModel.update(form => ({
      ...form,
      [campo]: valor
    }));
  }

  atualizarValor(valor: string): void {
    const apenasNumeros = valor.replace(/\D/g, '');
    const numero = Number(apenasNumeros) / 100;
    const formatado = numero.toLocaleString('pt-BR', {
      style: 'currency',
      currency: 'BRL'
    });

    this.formModel.update(form => ({
      ...form,
      valor: formatado as any
    }));
  }

  agendar(): void {
    const form = this.formModel();

    const valorNumerico = Number(
      String(form.valor)
        .replace(/[R$\s.]/g, '')
        .replace(',', '.')
    );

    const dadosEnvio = { ...form, valor: valorNumerico };

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
      valor: '' as any,
      dataTransferencia: this.obterDataHojeString()
    });
  }
}