import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { StoreComponent } from "./store/store.component";
import {GetInComponent} from "./get-in/get-in.component";
import {CartComponent} from "./cart/cart.component";
import {OrderComponent} from "./order/order.component";
import {OrderDetailsComponent} from "./order-details/order-details.component";
import {ProductComponent} from "./store/product/product.component";

const routes: Routes = [
  {
    path: 'store',
    component: StoreComponent
  },
  {
    path: '',
    component: GetInComponent
  },
  {
    path: 'cart',
    component: CartComponent
  },
  {
    path: 'order',
    component: OrderComponent
  },
  {
    path: 'orderDetails',
    component: OrderDetailsComponent
  },
  {
    path: 'product/:id',
    component: ProductComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
