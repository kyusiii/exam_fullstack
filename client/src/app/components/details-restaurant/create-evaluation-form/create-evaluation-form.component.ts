import {Component, EventEmitter, Input, Output} from '@angular/core';
import {FormsModule, NgForm} from "@angular/forms";
import {RestaurantService} from "../../../services/restaurant.service";
import {CreateRestaurantDto} from "../../../dtos/createRestaurantDto";
import {TagsEnum} from "../../../dtos/restaurantDto";
import {CreateEvaluationDto} from "../../../dtos/createEvaluationDto";
import {EvaluationService} from "../../../services/evaluation.service";

@Component({
  selector: 'app-create-evaluation-form',
  standalone: true,
    imports: [
        FormsModule
    ],
  templateUrl: './create-evaluation-form.component.html',
  styleUrl: './create-evaluation-form.component.css'
})
export class CreateEvaluationFormComponent {
  @Input() restoId?: number;

  file?: File;
  formFields: CreateEvaluationDto = {
    nomEvaluateur: "",
    commentaire: "",
    note: 0,
    dateCreation: new Date()
  };

  @Output("refresh") refresh: EventEmitter<void> = new EventEmitter<void>();

  constructor(private _evaluationService: EvaluationService) {
  }

  onFilesChanged(event: any) {
    this.file = event.target.files[0];
  }

  create(form: NgForm) {
    let id = 0;
    if (this.restoId) id = this.restoId
    this._evaluationService.createEvaluation(id, this.formFields)
      .subscribe({
        next: value => {
          if (this.file) {
            this._evaluationService.putPhoto(value.id, this.file)
              .subscribe({
                next: val => this.reset()
              })
          }
        },
        error: err => console.log("cant create restaurant " + err)
      });
  }

  reset() {
    this.refresh.emit();
    this.formFields = {
      nomEvaluateur: "",
      commentaire: "",
      note: 0,
      dateCreation: new Date()
    };
    this.file = undefined;
  }
}
