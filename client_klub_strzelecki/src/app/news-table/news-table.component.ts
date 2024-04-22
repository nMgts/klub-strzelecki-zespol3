import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { News } from "../interfaces/news";
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
    console.log("NewsTableComponent is initialized");
    this.getNews();
  }

  private getNews() {
    this.newsService.getNews().subscribe(data => {
      this.news_list = data;
    });
  }
  logHello(): void {
    console.log("Hello");
  }
  public onDeleteNews(id: number): void {
    console.log('Attempting to delete news with id:');  // Check if ID is correct
    this.newsService.deleteNews(id).subscribe({
      next: (response) => {
        console.log('News deleted successfully', response);
        this.news_list = this.news_list.filter(news => news.id !== id);
      },
      error: (err) => {
        console.error('Error deleting news:', err);
      }
    });
  }
}
