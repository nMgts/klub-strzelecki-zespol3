import {Component, OnInit} from '@angular/core';
import {News} from "../interfaces/news";
import {NewsService} from "../services/news.service";
import {ActivatedRoute, Router} from "@angular/router";
import {resetParseTemplateAsSourceFileForTest} from "@angular/compiler-cli/src/ngtsc/typecheck/diagnostics";

@Component({
  selector: 'app-edit-news',
  templateUrl: './edit-news.component.html',
  styleUrl: './edit-news.component.css'
})
export class EditNewsComponent implements OnInit {
  id: string | null;
  new_news: News = {
    title: '',
    content: ''
  };
  errorMessage: string = '';

  constructor(
    private newsService: NewsService,
    private route: ActivatedRoute,
    private router: Router) {
    this.id = '';
  }

  async onSubmit() {
    try {
      const token = localStorage.getItem('token')
      if (!token) {
        throw new Error("Token not found")
      }
      const res = await this.newsService.updateNews(this.id, this.new_news, token);

      if (res) {
        this.goToNews();
      } else {
        this.showError(res.message)
      }
    } catch (error: any) {
      this.showError(error.message)
    }
  }

  async getNewsById() {
    this.id = this.route.snapshot.paramMap.get('id')
    const token = localStorage.getItem('token')
    if (!this.id || !token) {
      this.showError("News ID or token is required")
      return;
    }
    try {
      let newsDataResponse = await this.newsService.getNewsById(this.id, token)
      console.log(newsDataResponse)
      const {title, content} = newsDataResponse
      this.new_news = {title, content};
    } catch (error: any) {
      this.showError(error.message);
    }
  }

  goToNews() {
    this.router.navigate(['/news']);
  }

  ngOnInit(): void {
    this.getNewsById();
  }

  showError(mess: string) {
    this.errorMessage = mess;
    setTimeout(() => {
      this.errorMessage = ''
    }, 3000)
  }
}
