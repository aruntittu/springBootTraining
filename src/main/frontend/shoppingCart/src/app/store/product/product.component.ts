import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ApiService} from "../../services/api.service";

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  product: Object;

  constructor(private router: Router, private route: ActivatedRoute, private apiService: ApiService) { }

  ngOnInit(): void {
    if(sessionStorage.getItem('userId')!=null) {
      document.getElementById('signOut').style.display = "block";
      document.getElementById('shoppingCart').style.display = "block";
      document.getElementById('store').style.display = "block";
      document.getElementById('orders').style.display = "block";
      this.route.params.subscribe(
        params => {
          const id = Number.parseInt(params['id']);
          this.apiService.getProduct(id).subscribe(
            data => {
              this.product = data;
            }
          )
        }
      );
    } else {
      this.router.navigate(["/"]).then();
    }
  }

}
