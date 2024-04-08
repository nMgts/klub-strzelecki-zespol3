import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { News } from "../entities/news";
import {NewsService} from "../services/news.service";

@Component({
  selector: 'app-news-table',
  imports: [CommonModule],
  templateUrl: './news-table.component.html',
  standalone: true,
  styleUrl: './news-table.component.css'
})
export class NewsTableComponent implements OnInit {

  constructor(private newsService: NewsService) {
  }

  news_list: News[] = [];

  ngOnInit(): void {
    this.getNews();
  }

  private getNews() {
    this.newsService.getNewsList().subscribe(data => {
      this.news_list = data;
    });
  }
}
