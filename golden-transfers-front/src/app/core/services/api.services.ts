import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, take } from 'rxjs/operators';
import { TransfersResponse } from '../interfaces/TransfersResponse';
import { TransfersRequest } from '../interfaces/TransfersRequest';
import { provideHttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private readonly apiUrl = 'http://localhost:8080/api/transfers/V1';

  constructor(private http: HttpClient) {}

  listar(): Observable<TransfersResponse[]> {
    return this.http.get<TransfersResponse[]>(this.apiUrl).pipe(
      take(1),
      catchError(err => this.handleError(err))
    );
  }

  agendar(transfer: TransfersRequest): Observable<TransfersResponse> {
    return this.http.post<TransfersResponse>(this.apiUrl, transfer).pipe(
      take(1),
      catchError(err => this.handleError(err))
    );
  }

  private handleError(error: any): Observable<never> {
    const errorMessage = error.error?.message || 'Ocorreu um erro inesperado na operação.';
    console.error('Erro na API Golden Transfers:', error);
    return throwError(() => new Error(errorMessage));
  }
}