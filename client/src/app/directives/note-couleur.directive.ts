import {Directive, ElementRef, Input, Renderer2} from '@angular/core';

@Directive({
  selector: '[appNoteCouleur]',
  standalone: true
})
export class NoteCouleurDirective {

  constructor(private _el: ElementRef, private _renderer: Renderer2) { }

  @Input("appNoteCouleur")
  set noteCouleur(value: string) {
    if (value == undefined) return;

    let note = parseInt(value);

    if (note > 2) {
      this._renderer.setStyle(this._el.nativeElement, "color", "yellow");
      return;
    }

    if (note < 1) {
      this._renderer.setStyle(this._el.nativeElement, "color", "red");
    }
  }
}
