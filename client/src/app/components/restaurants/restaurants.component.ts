import {Component, OnInit} from '@angular/core';
import {RestaurantDto} from "../../dtos/restaurantDto";
import {RestaurantService} from "../../services/restaurant.service";
import {RestaurantsListComponent} from "./restaurants-list/restaurants-list.component";
import {CreateRestaurantFormComponent} from "./create-restaurant-form/create-restaurant-form.component";

@Component({
  selector: 'app-restaurants',
  standalone: true,
  imports: [
    RestaurantsListComponent,
    CreateRestaurantFormComponent
  ],
  templateUrl: './restaurants.component.html',
  styleUrl: './restaurants.component.css'
})
export class RestaurantsComponent implements OnInit{
  restaurants: RestaurantDto[] = [];

  constructor(private _restaurantService: RestaurantService) {
  }

  ngOnInit(): void {
    this.fetchRestaurants();
  }

  fetchRestaurants() {
    this._restaurantService.getRestaurants()
      .subscribe({
        next: (res) => this.restaurants = res,
        error: err => console.log(err)
      });
  }
}
