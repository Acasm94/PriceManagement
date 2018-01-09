import { TestBed, inject } from '@angular/core/testing';

import { TempSasaService } from './temp-sasa.service';

describe('TempSasaService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TempSasaService]
    });
  });

  it('should be created', inject([TempSasaService], (service: TempSasaService) => {
    expect(service).toBeTruthy();
  }));
});
