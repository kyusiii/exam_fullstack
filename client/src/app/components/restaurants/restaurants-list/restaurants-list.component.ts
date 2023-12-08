import {Component, EventEmitter, Input, Output} from '@angular/core';
import {RestaurantDto} from "../../../dtos/restaurantDto";
import {NgForOf} from "@angular/common";
import {RestaurantItemComponent} from "../restaurant-item/restaurant-item.component";

@Component({
  selector: 'app-restaurants-list',
  standalone: true,
  imports: [
    NgForOf,
    RestaurantItemComponent
  ],
  templateUrl: './restaurants-list.component.html',
  styleUrl: './restaurants-list.component.css'
})
export class RestaurantsListComponent {
  @Input() restaurants: RestaurantDto[] = [];
  @Output("refresh") refresh: EventEmitter<void> = new EventEmitter<void>();
}
