import {Component, OnInit} from '@angular/core';
import { NewsService } from '../services/news.service';
import { News } from '../interfaces/news';
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-news',
  templateUrl: './add-news.component.html',
  styleUrls: ['./add-news.component.css']
})
export class AddNewsComponent implements OnInit {
  new_news: News = {
    title: '',
    content: ''
  };
  errorMessage: string = '';

  constructor(
    private newsService: NewsService,
    private router: Router) {
  }

  ngOnInit(): void {
  }

  async saveNews() {
    try {
      const token: any = localStorage.getItem('token');
      const response = await this.newsService.addNews(this.new_news, token);
      if (response) {
        this.new_news = response;
      } else {
        this.showError('No news found.');
      }
      this.goToNews();
    } catch (error: any) {
      this.showError(error.message);
      this.goToNews();
    }
  }

  goToNews() {
    this.router.navigate(['/news']);
  }

  onSubmit() {
    console.log(this.new_news);
    this.saveNews();
  }

  showError(messaga: string) {
    this.errorMessage = messaga;
    setTimeout(() => {
      this.errorMessage = '';
    }, 3000);
  }
}
