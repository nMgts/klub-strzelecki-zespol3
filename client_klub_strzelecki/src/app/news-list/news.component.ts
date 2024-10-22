import { AfterViewInit, ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { NewsService } from '../services/news.service';
import { News } from '../interfaces/news';
import { Router } from "@angular/router";
import { UsersService } from "../services/users.service";

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrl: './news.component.css'
})

// Component that displays the news
export class NewsComponent implements AfterViewInit, OnInit {
  news_list: News[] = [];
  visible: boolean = false;
  newsId: number | undefined;
  errorMessage: string = '';

  isAdmin: boolean = false;

  // Zmienna do kontrolowania ilości wyświetlanych wiadomości
  displayedNewsCount: number = 6;  // Początkowo wyświetlamy 6 wiadomości
  loadMoreStep: number = 6;  // O ile zwiększamy liczbę wiadomości po kliknięciu "Pokaż więcej"

  constructor(
    private newsService: NewsService,
    private userService: UsersService,
    private cd: ChangeDetectorRef,
    private router: Router) {}

  ngAfterViewInit(): void {
    this.cd.detectChanges();
  }

  ngOnInit(): void {
    this.isAdmin = this.userService.isAdmin();
    this.getNews();
  }

  private getNews() {
    this.newsService.getNews().subscribe(data => {
      this.news_list = data;
    });
  }

  // Funkcja do ładowania kolejnych wiadomości
  loadMoreNews() {
    this.displayedNewsCount += this.loadMoreStep;  // Zwiększamy liczbę wyświetlanych wiadomości
  }

  async deleteNews(newsId: number | undefined) {
    try {
      const token: any = localStorage.getItem('token');
      await this.newsService.deleteNews(newsId, token);
      this.getNews();
      this.visible = false;
    } catch (error: any) {
      this.showError(error.message);
    }
  }

  showDialog(id?: number) {
    console.log("show dialog");
    this.visible = true;
    this.newsId = id;
  }

  showError(mess: string) {
    this.errorMessage = mess;
    setTimeout(() => {
      this.errorMessage = ''
    }, 3000);
  }
}
