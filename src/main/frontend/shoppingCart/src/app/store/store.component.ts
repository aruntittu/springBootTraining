import {Component, OnInit, ViewChild} from '@angular/core';
import { ApiService } from "../services/api.service";
import {Router} from "@angular/router";
import {FormControl} from "@angular/forms";

@Component({
  selector: 'app-store',
  templateUrl: './store.component.html',
  styleUrls: ['./store.component.css']
})
export class StoreComponent implements OnInit {

  products : Object;
  cartItem: Object;
  quantity: FormControl;


  constructor(private apiService: ApiService, private router: Router) {}

  ngOnInit() {
    if(sessionStorage.getItem('userId')!=null) {
      this.apiService.getProducts().subscribe(
        (data) => {
          this.products = data;
        }
      );
      document.getElementById('signOut').style.display="block";
      document.getElementById('shoppingCart').style.display="block";
      document.getElementById('store').style.display="block";
      document.getElementById('orders').style.display="block";
      this.apiService.getPersonById(sessionStorage.getItem('userId')).subscribe(
        (person) => {
          document.getElementById('name').innerHTML=person['name'];
        }
      );
    } else {
      this.router.navigate(['/']);
    }
  }

  addToCart(productId: string) {
    const inputElement = "quantity"+productId;
    let quantity = (<HTMLInputElement>document.getElementById(inputElement)).value;
    if(parseInt(quantity)>0) {
      this.cartItem = {
        "person": {
          "id": sessionStorage.getItem('userId')
        },
        "product": {
          "id": productId
        },
        "quantity": quantity
      };
      this.apiService.addToCart(this.cartItem).subscribe(
        (data) => {
          (<HTMLInputElement>document.getElementById(inputElement)).value = '';
          document.getElementById("successModal").click();
        });

    } else {
      document.getElementById("warningModal").click();
    }
  }

}
