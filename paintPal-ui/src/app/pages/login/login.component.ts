import { Component } from '@angular/core';
import { AuthenticationRequest, AuthenticationResponse } from '../../services/models';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../services/services';
import { TokenService } from '../../services/token/token.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  authRequest: AuthenticationRequest = {email: '', password: ''};
  errorMsg: Array<string> = [];

  constructor(
    private router: Router,
    private authService: AuthenticationService,
    private tokenService: TokenService
  ) {

  }

  login(): void {
    this.errorMsg = [];
    this.authService.authenciate({
      body: this.authRequest
    }).subscribe({
      next: (res: AuthenticationResponse) => {
        this.tokenService.token = res.token as string;
        this.router.navigate(['paintings']);
      },
      error: (err) => {
        console.log(err);
        if(err.error.validationErrors) {
          this.errorMsg = err.error.validationErrors;
        } else {
          this.errorMsg.push(err.error.errorMsg);
        }
      }
    })
  }

  register(): void {
    this.router.navigate(['register']);
  }
}
