import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {RestaurantService} from "../../../services/restaurant.service";
import {CreateRestaurantDto} from "../../../dtos/createRestaurantDto";
import {FormsModule, NgForm} from "@angular/forms";
import {TagsEnum} from "../../../dtos/restaurantDto";

@Component({
  selector: 'app-create-restaurant-form',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './create-restaurant-form.component.html',
  styleUrl: './create-restaurant-form.component.css'
})
export class CreateRestaurantFormComponent {
  file?: File;
  formFields = {
    nom: "",
    adresse: "",
    gastronomique: false,
    bistrot: false,
    bistronomique: false,
    brasserie: false,
    fastfood: false
  };

  @Output("refresh") refresh: EventEmitter<void> = new EventEmitter<void>();

  constructor(private _restaurantService: RestaurantService) {
  }

  onFilesChanged(event: any) {
    this.file = event.target.files[0];
  }

  create(form: NgForm) {
    let dto: CreateRestaurantDto = {
      nom: this.formFields.nom,
      adresse: this.formFields.adresse,
      tags: []
    };

    if (this.formFields.bistrot) dto.tags.push(TagsEnum.BISTROT);
    if (this.formFields.gastronomique) dto.tags.push(TagsEnum.GASTRONOMIQUE);
    if (this.formFields.bistronomique) dto.tags.push(TagsEnum.BISTRONOMIQUE);
    if (this.formFields.brasserie) dto.tags.push(TagsEnum.BRASSERIE);
    if (this.formFields.fastfood) dto.tags.push(TagsEnum.FASTFOOD);

    this._restaurantService.createRestaurant(dto)
      .subscribe({
        next: value => {
          if (this.file) {
            this._restaurantService.putIllustration(value.id, this.file)
              .subscribe(
                {
                  next: value => this.reset()
                }
              );
          }
        },
        error: err => console.log("cant create restaurant " + err)
      });
  }

  reset() {
    this.refresh.emit();
    this.formFields = {
      nom: "",
      adresse: "",
      gastronomique: false,
      bistrot: false,
      bistronomique: false,
      brasserie: false,
      fastfood: false
    };
    this.file = undefined;
  }
}
