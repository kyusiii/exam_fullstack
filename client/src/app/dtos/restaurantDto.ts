import {EvaluationFinaleDto} from "./evaluationFinaleDto";
import {EvaluationDto} from "./evaluationDto";

export interface RestaurantDto {
  id: number;
  nom: string;
  moyenneEvaluation: number;
  adresse: string;
  evaluations: EvaluationDto[];
  evaluationFinale: EvaluationFinaleDto;
  imageId: string;
  tags: TagsEnum[];
}

export enum TagsEnum {
  GASTRONOMIQUE, BISTROT, BISTRONOMIQUE, BRASSERIE, FASTFOOD
}
