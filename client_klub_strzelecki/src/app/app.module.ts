import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NewsComponent } from './news-list/news.component';
import { ShootersComponent } from './shooters/shooters.component';
import { RouterModule } from '@angular/router';
import { AddNewsComponent } from './add-news/add-news.component';
import { FormsModule } from "@angular/forms";
import { EditNewsComponent } from './edit-news/edit-news.component';
import { CompetitionsListComponent } from './competitions-list/competitions-list.component';
import { AddShooterComponent } from './shooters-add/add-shooters.component';
import { EditShooterComponent } from './shooters-edit/edit-shooters.component';
import { StatuteComponent } from './statute/statute.component';
import { CompetitionAddComponent } from './competition-add/competition-add.component';

@NgModule({
  declarations: [
    AppComponent,
    NewsComponent,
    ShootersComponent,
    AddNewsComponent,
    EditNewsComponent,
    AddShooterComponent,
    EditShooterComponent,
    CompetitionsListComponent,
    StatuteComponent,
    CompetitionAddComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
