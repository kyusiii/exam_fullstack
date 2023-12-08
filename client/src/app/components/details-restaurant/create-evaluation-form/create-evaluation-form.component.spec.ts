import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateEvaluationFormComponent } from './create-evaluation-form.component';

describe('CreateEvaluationFormComponent', () => {
  let component: CreateEvaluationFormComponent;
  let fixture: ComponentFixture<CreateEvaluationFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateEvaluationFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CreateEvaluationFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
