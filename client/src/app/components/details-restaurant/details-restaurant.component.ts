import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Params} from "@angular/router";
import {RestaurantDto} from "../../dtos/restaurantDto";
import {RestaurantService} from "../../services/restaurant.service";
import {Subscription} from "rxjs";
import {CreateEvaluationFormComponent} from "./create-evaluation-form/create-evaluation-form.component";
import {ListEvaluationsComponent} from "./list-evaluations/list-evaluations.component";

@Component({
  selector: 'app-details-restaurant',
  standalone: true,
  imports: [
    CreateEvaluationFormComponent,
    ListEvaluationsComponent
  ],
  templateUrl: './details-restaurant.component.html',
  styleUrl: './details-restaurant.component.css'
})
export class DetailsRestaurantComponent implements OnInit, OnDestroy {
  constructor(private _activatedRoute: ActivatedRoute, private _restaurantService: RestaurantService) {
  }

  restaurant?: RestaurantDto;
  img: string = "";
  restoId: number = 0;

  $cover?: Subscription;
  $restaurant?: Subscription;
  $route?: Subscription;

  ngOnInit(): void {
    this.$route = this._activatedRoute.params.subscribe((params: Params) => {
      this.restoId = params['id'];
      this._restaurantService.getRestaurantById(params['id'])
        .subscribe({
          next: value => {
            this.restaurant = value;
            this.fetchIllustration();
          }
        });
    });
  }

  fetchRestaurant() {
    this.$restaurant = this._restaurantService.getRestaurantById(this.restoId)
      .subscribe({
        next: value => this.restaurant = value
      });
  }

  fetchIllustration() {
    if (!this.restaurant) return;

    this.$cover = this._restaurantService.getIllustration(this.restaurant.id)
      .subscribe(
        {
          next: res => this.img = res.url
        }
      );
  }

  ngOnDestroy(): void {
    this.$cover?.unsubscribe();
    this.$route?.unsubscribe();
    this.$restaurant?.unsubscribe();
  }
}
