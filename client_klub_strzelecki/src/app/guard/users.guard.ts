import { CanActivateFn, Router } from '@angular/router';
import { UsersService } from '../services/users.service';
import { inject } from '@angular/core';

export const usersGuard: CanActivateFn = (route, state) => {
  if (inject(UsersService).isAuthenticated()) {
    return true;
  }
  inject(Router).navigate(['/login'])
  return false
};

export const adminGuard: CanActivateFn = (route, state) => {
  if (inject(UsersService).isAdmin()) {
    return true;
  }
  inject(Router).navigate(['/login'])
  return false
};
