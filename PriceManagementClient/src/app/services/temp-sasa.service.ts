import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';

import { SasaTemp } from '../shared/tempSasa';

import 'rxjs/add/operator/toPromise';
import { Observable } from 'rxjs/Observable';
import { EventEmitter } from '@angular/core';


@Injectable()
export class TempSasaService {

  constructor(private http: Http) { }

  getString(broj: number): Promise<SasaTemp>{
    return this.http.get('/api/tipoviAtributa/zaKontekst/'+broj)
      .toPromise()
      .then(response => response.json() as SasaTemp)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any>{
    console.error("An error occured: ", error);
    return Promise.reject(error.message || error);
  }

}
