import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NewsComponent } from './news-list/news.component';
import { ShootersComponent } from './shooters/shooters.component';
import { UsersComponent } from './users-list/users.component';

import { AddShooterComponent } from './shooters-add/add-shooters.component';
import { AddNewsComponent } from './add-news/add-news.component';
import { EditShooterComponent } from './shooters-edit/edit-shooters.component';
import { EditNewsComponent } from "./edit-news/edit-news.component";
import { CompetitionsListComponent } from "./competitions-list/competitions-list.component";
import { StatuteComponent } from './statute/statute.component';
import { CompetitionAddComponent } from "./competition-add/competition-add.component";
import { ProfileComponent } from "./profile/profile.component";
import { LoginComponent } from "./login/login.component";
import { RegisterComponent } from "./register/register.component";
import {UpdateProfileComponent} from "./update-profile/update-profile.component";
import {AssignShootersComponent} from "./assign-shooters/assign-shooters.component";

export const routes: Routes = [
  { path: '', redirectTo: 'news', pathMatch: 'full' },
  { path: 'news', component: NewsComponent },
  { path: 'news/add', component: AddNewsComponent },
  { path: 'news/edit/:id', component: EditNewsComponent },
  { path: 'shooters', component: ShootersComponent},
  { path: 'shooters/add', component: AddShooterComponent },
  { path: 'shooters/edit/:id', component: EditShooterComponent },
  { path: 'users', component: UsersComponent},
  { path: 'competitions', component: CompetitionsListComponent},
  { path: 'competitions/add', component: CompetitionAddComponent},
  { path: 'competitions/assign/:id', component: AssignShootersComponent },
  { path: 'statute', component: StatuteComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'profile/update/:id', component: UpdateProfileComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
