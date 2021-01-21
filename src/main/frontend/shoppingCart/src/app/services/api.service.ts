import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Login} from "../model/Login";
import {Person} from "../model/Person";

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private urlString: string;

  constructor(private httpClient: HttpClient) {
  }

  public addUser(person: Person) {
    return this.httpClient.post("http://localhost:8080/api/v1/person", person);
  }

  public getPersonById(id: String) {
    this.urlString = "http://localhost:8080/api/v1/person/";
    return this.httpClient.get(this.urlString+id);
  }

  public getValidated(login: Login) {
    return this.httpClient.post("http://localhost:8080/api/v1/login", login);
  }

  public getProducts() {
    return this.httpClient.get("http://localhost:8080/api/v1/product");
  }

  public getProduct(id: number) {
    return this.httpClient.get("http://localhost:8080/api/v1/product/"+id);
  }

  public addToCart(cartItem: Object) {
    return this.httpClient.post("http://localhost:8080/api/v1/cart", cartItem);
  }

  public getCartItems(id: String) {
    return this.httpClient.get("http://localhost:8080/api/v1/cart/"+id);
  }

  public deleteCartItem(cartItem: Object) {
    return this.httpClient.post("http://localhost:8080/api/v1/cart/delete", cartItem);
  }

  public placeOrder(id: String) {
    return this.httpClient.post("http://localhost:8080/api/v1/order/"+id, '');
  }

  public getOrders(id: String) {
    return this.httpClient.get("http://localhost:8080/api/v1/order/"+id);
  }

  public getOrderDetails(id: String) {
    return this.httpClient.get("http://localhost:8080/api/v1/order/orderDetails/"+id);
  }
}
