import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NewsComponent } from './news-list/news.component';
import { ShootersComponent } from './shooters/shooters.component';
import { DialogModule } from 'primeng/dialog';
import { RouterModule } from '@angular/router';
import { AddNewsComponent } from './add-news/add-news.component';
import { FormsModule } from "@angular/forms";
import { EditNewsComponent } from './edit-news/edit-news.component';
import { CompetitionsListComponent } from './competitions-list/competitions-list.component';
import { AddShooterComponent } from './shooters-add/add-shooters.component';
import { EditShooterComponent } from './shooters-edit/edit-shooters.component';
import { StatuteComponent } from './statute/statute.component';
import { UsersComponent } from './users-list/users.component';
import { CompetitionAddComponent } from './competition-add/competition-add.component';
import { Button } from "primeng/button";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './login/login.component';
import { ProfileComponent } from './profile/profile.component';
import { RegisterComponent } from './register/register.component';
import { provideHttpClient } from '@angular/common/http';
import { UpdateProfileComponent } from './update-profile/update-profile.component';

@NgModule({
  declarations: [
    AppComponent,
    NewsComponent,
    ShootersComponent,
    UsersComponent,
    AddNewsComponent,
    EditNewsComponent,
    AddShooterComponent,
    EditShooterComponent,
    CompetitionsListComponent,
    StatuteComponent,
    CompetitionAddComponent,
    LoginComponent,
    ProfileComponent,
    RegisterComponent,
    UpdateProfileComponent
  ],
  imports: [
    DialogModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule,
    FormsModule,
    BrowserAnimationsModule,
    Button
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
