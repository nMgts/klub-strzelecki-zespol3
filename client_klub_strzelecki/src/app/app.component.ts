import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {ShootersTableComponent} from "./shooters-table/shooters-table.component";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, ShootersTableComponent],
  templateUrl: './app.component.html',
  standalone: true,
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Shooting Club';
}
