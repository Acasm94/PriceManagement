import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Cenovnik } from '../shared/Cenovnik';
import { StavkaCenovnika } from '../shared/Stavka-cenovnika';
import { PredefinisanaVrednost } from '../shared/Predefinisana-vrednost';

@Injectable()
export class CenovnikService {

  constructor(private http: Http) { }

  getCenovniciZaOsiguravajucuKucu(){
    return this.http.get("/api/cenovnici/zaOsiguravajucuKucu")
      .toPromise()
      .then(response => response.json() as Cenovnik)
      .catch(this.handleError);
  }

  getCenovniciZaOsiguravajucuKucuPoDatumu(){
    return this.http.get("/api/cenovnici/zaOsiguravajucuKucuPoDatumu")
      .toPromise()
      .then(response => response.json() as Cenovnik)
      .catch(this.handleError);
  }

  getAktuelniCenovnik(): Promise<Cenovnik>{
    return this.http.get("/api/cenovnici")
      .toPromise()
      .then(response => response.json() as Cenovnik)
      .catch(this.handleError);
  }

  setAktuelanCenovnikZaOsiguravajucuKucu(cenovnikId: number){
    return this.http.get("/api/cenovnici/aktuelan/" + cenovnikId)
      .toPromise()
      .then(response => response.json() as Cenovnik)
      .catch(this.handleError);
  }

  getStavkeCenovnikaZaCenovnik(cenovnikId: number): Promise<StavkaCenovnika[]>{
    return this.http.get('/api/stavkaCenovnika/zaCenovnik/' + cenovnikId)
      .toPromise()
      .then(respone => respone.json() as StavkaCenovnika[])
      .catch(this.handleError);
  }

  getPredefinisaneVrednosti(): Promise<PredefinisanaVrednost[]>{
    return this.http.get('/api/predefinisaneVrednosti')
      .toPromise()
      .then(response => response.json() as PredefinisanaVrednost[])
      .catch(this.handleError);
  }

  createCenovnik(cenovnik: Cenovnik) {
    return this.http.post('/api/cenovnici', cenovnik)
      .toPromise()
      .then(response => response.json() as Cenovnik)
      .catch(this.handleError);
  }

  updateCenovnik(cenovnik: Cenovnik) {
    return this.http.put('/api/cenovnici', cenovnik)
      .toPromise()
      .then(response => response.json() as Cenovnik)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any>{
    console.error("An error occured: ", error);
    return Promise.reject(error.message || error);
  }

}
