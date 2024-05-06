import {Component, OnInit} from '@angular/core';
import {News} from "../interfaces/news";
import {NewsService} from "../services/news.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-edit-news',
  templateUrl: './edit-news.component.html',
  styleUrl: './edit-news.component.css'
})
export class EditNewsComponent implements OnInit {
  id: number;
  new_news: News = {
    title: '',
    content: ''
  };

  constructor(
    private newsService: NewsService,
    private route: ActivatedRoute,
    private router: Router) {
    this.id = 0;
  }

  onSubmit() {
    this.newsService.editNews(this.id, this.new_news).subscribe(data => {
      this.goToNews();
    }, error => console.log(error));
  }

  goToNews() {
    this.router.navigate(['/news']);
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.newsService.getNewsById(this.id).subscribe(data => {
      this.new_news = data;
    }, error => console.log(error));
  }

}
