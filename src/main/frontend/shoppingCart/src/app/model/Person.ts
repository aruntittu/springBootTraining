import {Login} from "./Login";

export class Person {

  private id: number;
  private name: string;
  private email: string;
  private phone: string;
  private login: Login;

  public getEmail() {
    return this.email;
  }

  public getName() {
    return this.name;
  }

  public getPhone() {
    return this.phone;
  }

  public getId() {
    return this.id;
  }
}
