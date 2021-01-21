import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {ApiService} from "../services/api.service";

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.css']
})
export class OrderDetailsComponent implements OnInit {

  orderId = sessionStorage.getItem('orderId');
  orderDetails: Object;
  intermediateTotal: any;
  total = 0;
  constructor(private router: Router, private apiService: ApiService) { }

  ngOnInit(): void {
    if(sessionStorage.getItem('userId')!=null) {
      document.getElementById('signOut').style.display = "block";
      document.getElementById('shoppingCart').style.display = "block";
      document.getElementById('store').style.display = "block";
      document.getElementById('orders').style.display = "block";
      this.apiService.getOrderDetails(this.orderId).subscribe(
        (data) => {
          this.orderDetails = data;
          // @ts-ignore
          for (let orderDetail of this.orderDetails) {
            this.intermediateTotal = orderDetail.product.price * orderDetail.quantity;
            this.total = this.total + this.intermediateTotal;
          }
        }
      );
    } else {
      this.router.navigate(["/"]).then();
    }
  }

}
