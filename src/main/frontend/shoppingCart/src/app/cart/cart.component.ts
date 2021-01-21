import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {ApiService} from "../services/api.service";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  products: Object;
  intermediateTotal = 0;
  total = 0;
  cartItem: Object;

  constructor(private router: Router, private apiService: ApiService) { }

  ngOnInit(): void {
    if(sessionStorage.getItem('userId')!=null) {
      document.getElementById('signOut').style.display="block";
      document.getElementById('shoppingCart').style.display="block";
      document.getElementById('store').style.display="block";
      document.getElementById('orders').style.display="block";
      this.apiService.getCartItems(sessionStorage.getItem('userId')).subscribe(
        (data) => {
          this.products = data;
          // @ts-ignore
          for (let product of this.products) {
            this.intermediateTotal = product[0].price * product[1];
            this.total = this.total + this.intermediateTotal;
          }
          if(this.total>0) {
            //document.getElementById('orderButton').style.display="block";
          } else {
            document.getElementById('orderButton').style.display="none";
          }
        }
      )
    }
    else {
      this.router.navigate(["/"]).then();
    }
  }

  removeItem(productId: any) {
    this.cartItem = {
      "person": {
        "id": sessionStorage.getItem('userId')
      },
      "product": {
        "id": productId
      }
    };
    this.apiService.deleteCartItem(this.cartItem).subscribe(
      (data) => {
        let currentUrl = this.router.url;
        this.router.navigateByUrl('/', {skipLocationChange: true}).then(() => {
          this.router.navigate([currentUrl]).then();
        });
      }
    )
  }

  placeOrder() {
    this.apiService.placeOrder(sessionStorage.getItem('userId')).subscribe(
      (data) => {
        let currentUrl = this.router.url;
        this.router.navigateByUrl('/', {skipLocationChange: true}).then(() => {
          this.router.navigate([currentUrl]).then();
        });
      }
    )
  }
}
