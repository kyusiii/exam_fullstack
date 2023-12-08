export interface EvaluationDto {
  id: number;
  nomEvaluateur: string;
  commentaire: string;
  note: number;
  dateCreation: Date;
  dateUpdate: Date;
  photosIds: string[];
}
