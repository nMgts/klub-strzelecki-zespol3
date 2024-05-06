import { AfterViewInit, ChangeDetectorRef, Component, ViewChild, OnInit } from '@angular/core';

import { NgModule } from '@angular/core';

import { NewsService } from '../services/news.service';
import { CommonModule } from '@angular/common';
import { News } from '../interfaces/news';

@Component({
  selector: 'app-news',

  templateUrl: './news.component.html',
  styleUrls: [
  ]
})

// Component that displays the news
export class NewsComponent implements AfterViewInit {

  news_list: News[] = [];

  constructor(private newsService: NewsService, private cd: ChangeDetectorRef) {}

  // After init - because we need the pagination to load first
  // Fetch the news from the database and display them
  ngAfterViewInit(): void {

    // The DOM has been changed, we need to detect the changes to prevent ExpressionChangedAfterItHasBeenCheckedError
    this.cd.detectChanges();
  }
  logHello(): void {
    console.log("Hello");
  }
  ngOnInit(): void {
    console.log("NewsComponent is initialized halo");
    this.getNews();
  }

  private getNews() {
    this.newsService.getNews().subscribe(data => {
      this.news_list = data;
    });
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

  changeNlForP(text: string): string {
    let editedText = '<p>' + text;
    editedText = editedText.replace(/\n/g, '</p><p>');
    editedText += '</p>';
    return editedText;
  }
}
