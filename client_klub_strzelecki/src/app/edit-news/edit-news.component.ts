import {Component, OnInit} from '@angular/core';
import {News} from "../interfaces/news";
import {NewsService} from "../services/news.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-edit-news',
  templateUrl: './edit-news.component.html',
  styleUrl: './edit-news.component.css'
})
export class EditNewsComponent implements OnInit {
  new_news: News = {
    title: '',
    content: ''
  };

  constructor(
    private newsService: NewsService,
    private router: Router) {
  }

  onSubmit() {

  }

  ngOnInit(): void {
  }

}
