import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {CreateEvaluationDto} from "../dtos/createEvaluationDto";
import {Observable, Subject} from "rxjs";
import {RestaurantDto} from "../dtos/restaurantDto";
import {EvaluationDto} from "../dtos/evaluationDto";
import {UrlDto} from "../dtos/urlDto";

@Injectable({
  providedIn: 'root'
})
export class EvaluationService {

  constructor(private client: HttpClient) { }

  createEvaluation(idResto: number, dto: CreateEvaluationDto): Observable<EvaluationDto> {
    return this.client.post<EvaluationDto>("http://localhost:8080/evaluations/" + idResto + "/create", dto);
  }

  getPutPhotoUrl(id: number) {
    return this.client.put<UrlDto>(`http://localhost:8080/evaluations/photos/${id}`, {})
  }

  putPhoto(id: number, photo: File): Subject<void> {
    let obs: Subject<void> = new Subject<void>();

    this.getPutPhotoUrl(id)
      .subscribe(
        {
          next: value => {
            this.client.put(value.url, photo).subscribe(v => obs.next());
          },
          error: err => console.log(err)
        }
      );

    return obs;
  }
}
