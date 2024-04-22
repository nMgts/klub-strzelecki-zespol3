import { Routes } from '@angular/router';
import { ShooterFormComponent } from './shooter-form/shooter-form.component';

export const routes: Routes = [
  {
    path: 'shooters/:id',
    component: ShooterFormComponent
  }
];
