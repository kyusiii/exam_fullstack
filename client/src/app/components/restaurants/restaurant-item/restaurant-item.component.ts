import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {RestaurantDto} from "../../../dtos/restaurantDto";
import {RestaurantService} from "../../../services/restaurant.service";
import {Subscription} from "rxjs";
import {NoteCouleurDirective} from "../../../directives/note-couleur.directive";
import {NgIf} from "@angular/common";
import {Router, RouterLink} from "@angular/router";

@Component({
  selector: 'app-restaurant-item',
  standalone: true,
  imports: [
    NoteCouleurDirective,
    NgIf,
    RouterLink
  ],
  templateUrl: './restaurant-item.component.html',
  styleUrl: './restaurant-item.component.css'
})
export class RestaurantItemComponent implements OnInit {
  @Input() restaurant?: RestaurantDto;
  $cover?: Subscription;
  img: string = "";

  @Output("refresh") refresh: EventEmitter<void> = new EventEmitter<void>();

  constructor(private _restaurantService: RestaurantService, private _router: Router) {
  }

  ngOnInit(): void {
    if (!this.restaurant) return;

    this.$cover = this._restaurantService.getIllustration(this.restaurant.id)
      .subscribe(
        {
          next: res => this.img = res.url,
        }
      );
  }

  routeToDetails() {
    this._router.navigateByUrl("/restaurants/" + this.restaurant?.id).then();
  }
}
