export interface TransfersResponse {
  id: number;
  contaOrigem: string;
  contaDestino: string;
  valor: number;
  taxa: number;
  dataAgendamento: string;
  dataTransferencia: string;
}