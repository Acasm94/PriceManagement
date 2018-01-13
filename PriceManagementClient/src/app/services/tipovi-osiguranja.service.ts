import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';

import { TipOsiguranja } from '../shared/tip-osiguranja';
import { TipAtributa } from '../shared/tip-atributa';
import { PredefinisanaVrednost } from '../shared/predefinisana-vrednost';
import { Cenovnik } from '../shared/cenovnik';
import { StavkaCenovnika } from '../shared/stavka-cenovnika';

import 'rxjs/add/operator/toPromise';
import { Observable } from 'rxjs/Observable';
import { EventEmitter } from '@angular/core';

@Injectable()
export class TipoviOsiguranjaService {
  osiguravajucaKucaId: number = 1;
  constructor(private http:Http) { }

  getTipoviOsiguranja(osiguravajucaKucaId: number): Promise<TipOsiguranja[]>
  {
    return this.http.get('/api/tipOsiguranja/zaOsiguravajucuKucu/'+osiguravajucaKucaId).toPromise()
    .then(response => response.json() as TipOsiguranja[])
    .catch(this.handleError);
  }

  getCenovnikZaOsiguravajucuKucu(osiguravajucaKucaId: number): Promise<Cenovnik> {
    return this.http.get('/api/cenovnik/zaOsiguravajucuKucu/' + osiguravajucaKucaId)
      .toPromise()
      .then(response => response.json() as Cenovnik)
      .catch(this.handleError);
  }

  getTipoviAtributaZaTipOsiguranja(tipOsiguranjaId: number): Promise<TipAtributa[]> {
    return this.http.get('/api/tipoviAtributa/uticuNaCenu/' + tipOsiguranjaId)
      .toPromise()
      .then(response => response.json() as TipAtributa[])
      .catch(this.handleError);
  }

  getPredefinisaneVrednostiZaTipAtributa(tipAtributaId: number): Promise<PredefinisanaVrednost[]> {
    return this.http.get('/api/predefinisaneVrednosti/zaTipAtributa/' + tipAtributaId)
      .toPromise()
      .then(response => response.json() as PredefinisanaVrednost[])
      .catch(this.handleError);
  }

  //OVO MOZDA NE MORA! Ako moze iz predefinisanie vrednosti da se uzme i stavka (njena vrednost)
  getStavkeZaCenovnikIPredefinisanuVrednost(cenovnikId: number, predVrednostId: number): Promise<StavkaCenovnika[]> {
    return this.http.get('/api/stavkaCenovnika/' + cenovnikId+'/'+predVrednostId)
      .toPromise()
      .then(response => response.json() as StavkaCenovnika[])
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any>{
    console.error("An error occured: ", error);
    return Promise.reject(error.message || error);
  }
}
