import { TestBed, inject } from '@angular/core/testing';

import { TipoviOsiguranjaService } from './tipovi-osiguranja.service';

describe('TipoviOsiguranjaService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TipoviOsiguranjaService]
    });
  });

  it('should be created', inject([TipoviOsiguranjaService], (service: TipoviOsiguranjaService) => {
    expect(service).toBeTruthy();
  }));
});
