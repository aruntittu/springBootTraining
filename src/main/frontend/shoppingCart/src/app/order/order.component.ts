import { Component, OnInit } from '@angular/core';
import {ApiService} from "../services/api.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  userOrders: Object;

  constructor(private apiService: ApiService, private router: Router) {}

  ngOnInit(): void {
    if(sessionStorage.getItem('userId')!=null) {
      document.getElementById('signOut').style.display="block";
      document.getElementById('shoppingCart').style.display="block";
      document.getElementById('store').style.display="block";
      document.getElementById('orders').style.display="block";
      this.apiService.getOrders(sessionStorage.getItem('userId')).subscribe(
        (data) => {
          this.userOrders = data;
        }
      )
    }
    else {
      this.router.navigate(["/"]).then();
    }
  }

  getDetails(id: any) {
    sessionStorage.setItem('orderId', id);
    this.router.navigate(["orderDetails"]).then();
  }
}
