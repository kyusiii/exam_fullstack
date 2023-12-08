import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, Subject} from "rxjs";
import {RestaurantDto} from "../dtos/restaurantDto";
import {CreateRestaurantDto} from "../dtos/createRestaurantDto";
import {UrlDto} from "../dtos/urlDto";

@Injectable({
  providedIn: 'root'
})
export class RestaurantService {

  constructor(private client: HttpClient) { }

  getRestaurants(): Observable<RestaurantDto[]> {
    return this.client.get<RestaurantDto[]>("http://localhost:8080/restaurants");
  }

  getRestaurantById(id: number): Observable<RestaurantDto> {
    return this.client.get<RestaurantDto>(`http://localhost:8080/restaurants/${id}`);
  }

  createRestaurant(dto: CreateRestaurantDto): Observable<RestaurantDto> {
    return this.client.post<RestaurantDto>("http://localhost:8080/restaurants/create", dto);
  }

  getIllustration(id: number): Observable<UrlDto> {
    return this.client.get<UrlDto>(`http://localhost:8080/restaurants/illustration/${id}`)
  }

  getPutIllustrationUrl(id: number) {
    return this.client.put<UrlDto>(`http://localhost:8080/restaurants/illustration/${id}`, {})
  }

  putIllustration(id: number, illu: File): Subject<void> {
    let obs: Subject<void> = new Subject<void>();

    this.getPutIllustrationUrl(id)
      .subscribe(
        {
          next: value => {
            this.client.put(value.url, illu).subscribe(v => obs.next());
          },
          error: err => console.log(err)
        }
      );

    return obs;
  }
}
