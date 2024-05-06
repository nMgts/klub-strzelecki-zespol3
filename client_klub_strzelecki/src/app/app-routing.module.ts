import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NewsComponent } from './news-list/news.component';
import { ShootersComponent } from './shooters/shooters.component';
import { AddNewsComponent } from './add-news/add-news.component';
import {EditNewsComponent} from "./edit-news/edit-news.component";
import {CompetitionsListComponent} from "./competitions-list/competitions-list.component";

export const routes: Routes = [
  { path: '', redirectTo: 'news', pathMatch: 'full' },
  { path: 'news', component: NewsComponent },
  { path: 'news/add', component: AddNewsComponent },
  { path: 'news/edit/:id', component: EditNewsComponent },
  { path: 'shooters', component: ShootersComponent},
  { path: 'competitions', component: CompetitionsListComponent},
  { path: 'shooters/add', component: AddNewsComponent },
  { path: 'shooters/edit/:id', component: EditNewsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
