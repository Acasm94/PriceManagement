import { TestBed, inject } from '@angular/core/testing';

import { KeycloakHttp } from './keycloak.http';

describe('KeycloakHttpService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [KeycloakHttp]
    });
  });

  it('should be created', inject([KeycloakHttp], (service: KeycloakHttp) => {
    expect(service).toBeTruthy();
  }));
});
