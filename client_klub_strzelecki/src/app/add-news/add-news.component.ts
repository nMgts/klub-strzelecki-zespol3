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
  constructor(
    private newsService: NewsService,
    private router: Router) {
  }

  ngOnInit(): void {
  }

  saveNews() {
    this.newsService.addNews(this.new_news).subscribe( data =>{
      console.log(data);
      this.goToNews();
    },
        error => console.log(error));
  }

  goToNews() {
    this.router.navigate(['/news']);
  }

  onSubmit() {
    console.log(this.new_news);
    this.saveNews();
  }
}
