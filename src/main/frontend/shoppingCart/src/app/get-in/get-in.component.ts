import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from "@angular/forms";
import { Login } from "../model/Login";
import { ApiService } from "../services/api.service";
import { Router } from "@angular/router";
import {Person} from "../model/Person";

@Component({
  selector: 'app-get-in',
  templateUrl: './get-in.component.html',
  styleUrls: ['./get-in.component.css']
})
export class GetInComponent implements OnInit {

  loginForm: FormGroup;
  signUpForm: FormGroup;
  login: Login;
  person: Person;
  loginResponse: any;
  signUpResponse: any;

  constructor(private apiService: ApiService,
              private router: Router) { }

  ngOnInit() {
    this.loginForm = new FormGroup({
      username: new FormControl(null),
      password: new FormControl(null),
    });
    this.signUpForm = new FormGroup({
      name: new FormControl(null),
      email: new FormControl(null),
      phone: new FormControl(null),
      userName: new FormControl(null),
      passWord: new FormControl(null),
    })
  }

  onLogin() {
    this.login={
      username: this.loginForm.get('username').value,
      password: this.loginForm.get('password').value
    };
    this.apiService.getValidated(this.login).subscribe(
      (data) => {
        this.loginResponse = data;
        if(this.loginResponse>0) {
          sessionStorage.setItem('userId', this.loginResponse);
          document.getElementById('signOut').style.display="block";
          this.router.navigate(['/store']);
        } else {
          //  add code here to notify wrong username/password
        }
      });
  }

  onSignUp() {
    // @ts-ignore
    this.person={
      id: null,
      name: this.signUpForm.get('name').value,
      email: this.signUpForm.get('email').value,
      phone: this.signUpForm.get('phone').value,
      login:{
        username: this.signUpForm.get('userName').value,
        password: this.signUpForm.get('passWord').value
      }
    };
    this.apiService.addUser(this.person).subscribe(
      (data) => {
        this.signUpResponse = data;
        if(this.signUpResponse>0) {
          sessionStorage.setItem('userId', this.signUpResponse);
          document.getElementById('signOut').style.display="block";
          document.getElementById('shoppingCart').style.display="block";
          this.router.navigate(['/store']);
        } else {
          //  add code here to notify wrong username/password
        }
      }
    )
  }
}
