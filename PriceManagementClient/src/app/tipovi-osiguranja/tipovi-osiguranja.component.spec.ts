import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TipoviOsiguranjaComponent } from './tipovi-osiguranja.component';

describe('TipoviOsiguranjaComponent', () => {
  let component: TipoviOsiguranjaComponent;
  let fixture: ComponentFixture<TipoviOsiguranjaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TipoviOsiguranjaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TipoviOsiguranjaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
