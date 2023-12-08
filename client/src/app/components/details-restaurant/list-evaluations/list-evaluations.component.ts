import {Component, Input} from '@angular/core';
import {EvaluationDto} from "../../../dtos/evaluationDto";
import {NgForOf} from "@angular/common";
import {ActivatedRoute} from "@angular/router";
import {RestaurantService} from "../../../services/restaurant.service";
import {EvaluationService} from "../../../services/evaluation.service";

@Component({
  selector: 'app-list-evaluations',
  standalone: true,
  imports: [
    NgForOf
  ],
  templateUrl: './list-evaluations.component.html',
  styleUrl: './list-evaluations.component.css'
})
export class ListEvaluationsComponent {
  @Input() evaluations?: EvaluationDto[] = [];

  constructor (private _evaluationService: EvaluationService) {}

  getPhotoUrl(id: string): string {
    return "";
  }
}
