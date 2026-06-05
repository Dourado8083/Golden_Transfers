import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Transfers } from "./pages/transfers/transfers";

@Component({
  selector: 'app-root',
  imports: [Transfers],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  protected readonly title = signal('golden-transfers-front');
}
