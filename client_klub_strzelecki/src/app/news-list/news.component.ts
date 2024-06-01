import { AfterViewInit, ChangeDetectorRef, Component, ViewChild, OnInit } from '@angular/core';

import { NgModule } from '@angular/core';

import { NewsService } from '../services/news.service';
import { CommonModule } from '@angular/common';
import { News } from '../interfaces/news';
import {Router} from "@angular/router";
import {UsersService} from "../services/users.service";

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrl: './news.component.css'
})

// Component that displays the news
export class NewsComponent implements AfterViewInit {

  news_list: News[] = [];
  visible: boolean = false;
  newsId: number| undefined;
  errorMessage: string = '';

  isAdmin:boolean = false;

  constructor(
    private newsService: NewsService,
    private userService: UsersService,
    private cd: ChangeDetectorRef,
    private router: Router) {}

  // After init - because we need the pagination to load first
  // Fetch the news from the database and display them
  ngAfterViewInit(): void {

    // The DOM has been changed, we need to detect the changes to prevent ExpressionChangedAfterItHasBeenCheckedError
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
  /*
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
*/
  changeNlForP(text: string): string {
    let editedText = '<p>' + text;
    editedText = editedText.replace(/\n/g, '</p><p>');
    editedText += '</p>';
    return editedText;
  }

  editNews(id: number) {
    this.router.navigate(['news/edit', id]);
  }

  async deleteNews(newsId: number | undefined) {
    try {
    const token: any = localStorage.getItem('token');
    await this.newsService.deleteNews(newsId, token);
    this.getNews();
  } catch (error: any) {
    this.showError(error.message);
  }
  }

  showDialog(id?: number)
  {
    console.log("show dialog");
    this.visible = true;
    this.newsId = id;
  }


  /*
  deleteNews(id: number) {
    this.newsService.deleteNews(id).subscribe( data =>{
      console.log(data);
      this.getNews();
    })
  }

   */

  showError(mess: string) {
    this.errorMessage = mess;
    setTimeout(() => {
      this.errorMessage = ''
    }, 3000)
  }
}
