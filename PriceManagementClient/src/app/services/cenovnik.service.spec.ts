import { TestBed, inject } from '@angular/core/testing';

import { CenovnikService } from './cenovnik.service';

describe('CenovnikService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CenovnikService]
    });
  });

  it('should be created', inject([CenovnikService], (service: CenovnikService) => {
    expect(service).toBeTruthy();
  }));
});
