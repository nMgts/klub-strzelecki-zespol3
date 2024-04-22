import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {ShootersTableComponent} from "./shooters-table/shooters-table.component";
import { NewsComponent } from './components/news.component';
import { NewsTableComponent } from './news-table/news-table.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, ShootersTableComponent, NewsComponent, NewsTableComponent],
  templateUrl: './app.component.html',
  standalone: true,

  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Shooting Club';
  title_news = 'Bieżące informacje'
}
