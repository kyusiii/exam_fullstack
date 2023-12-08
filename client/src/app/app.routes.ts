import { Routes } from '@angular/router';
import {RestaurantsComponent} from "./components/restaurants/restaurants.component";
import {HomeComponent} from "./components/home/home.component";
import {DetailsRestaurantComponent} from "./components/details-restaurant/details-restaurant.component";

export const routes: Routes = [
  {
    path: "restaurants", component: RestaurantsComponent
  },
  {
    path: "", component: HomeComponent
  },
  {
    path: "restaurants/:id", component: DetailsRestaurantComponent
  }
];
