import {Component, OnChanges, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'shoppingCart';

  constructor(private router: Router) { }

  ngOnInit() {
  }

  signOut() {
    sessionStorage.removeItem('userId');
    sessionStorage.removeItem('orderId');
    this.router.navigate(["/"]).then();
    document.getElementById('signOut').style.display="none";
    document.getElementById('shoppingCart').style.display="none";
    document.getElementById('store').style.display="none";
  }

  cart() {
    this.router.navigate(["cart"]).then();
  }

  store() {
    this.router.navigate(["store"]).then();
  }

  orders() {
    this.router.navigate(["order"]).then();
  }
}
