import {TagsEnum} from "./restaurantDto";

export interface CreateRestaurantDto {
  nom: string;
  adresse: string;
  tags: TagsEnum[];
}
