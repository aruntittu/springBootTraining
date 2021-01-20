import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { StoreComponent } from "./store/store.component";
import {GetInComponent} from "./get-in/get-in.component";
import {CartComponent} from "./cart/cart.component";
import {OrderComponent} from "./order/order.component";

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
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
