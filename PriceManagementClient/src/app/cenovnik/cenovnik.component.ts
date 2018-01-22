import { Component, OnInit } from '@angular/core';
import { CenovnikService } from '../services/cenovnik.service';
import { Cenovnik } from '../shared/Cenovnik';
import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { TemplateRef } from '@angular/core/src/linker/template_ref';
import { PredefinisanaVrednost } from '../shared/Predefinisana-vrednost';
import { StavkaCenovnika } from '../shared/Stavka-cenovnika';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-cenovnik',
  templateUrl: './cenovnik.component.html',
  styleUrls: ['./cenovnik.component.css']
})
export class CenovnikComponent implements OnInit {

  action: String;
  modalRef: BsModalRef;
  aktuelniCenovnik: Cenovnik;
  predefinisaneVrednosti: PredefinisanaVrednost[];
  noviCenovnik: Cenovnik;
  stavkeCenovnika: Map<number, StavkaCenovnika>;
  listaCenovnika: Cenovnik[];
  pravila: string;
  files: string[];
  jBossStatus: string;
  drlFileName: string;


  constructor(private cenovnikService: CenovnikService, private modalService: BsModalService) { 
    this.noviCenovnik = new Cenovnik();
    this.stavkeCenovnika = new Map();
  }

  ngOnInit() {
    this.cenovnikService.getAktuelniCenovnik()
      .then(cenovnik => this.getStavkeCenovnikaZaCenovnik(cenovnik));
    this.cenovnikService.getPredefinisaneVrednosti()
      .then(predefinisaneVrednosti => this.predefinisaneVrednosti = predefinisaneVrednosti);
    this.cenovnikService.getFajlove()
      .then(files => {this.files = files;});
  }

  getStavkeCenovnikaZaCenovnik(cenovnik: Cenovnik){
    cenovnik.datumOd = this.prepareDate(cenovnik.datumOd);
    cenovnik.datumDo = this.prepareDate(cenovnik.datumDo);
    this.aktuelniCenovnik = cenovnik;
    this.cenovnikService.getStavkeCenovnikaZaCenovnik(cenovnik.id)
      .then(stavkeCenovnika => this.aktuelniCenovnik.stavkeCenovnika = stavkeCenovnika);
  }

  public openCreateCenovnik(template: TemplateRef<any>) {
    this.action = "ADD";
    this.stavkeCenovnika.clear();
    for(var predefinisanaVrednost of this.predefinisaneVrednosti){
      let stavkaCenovnika = new StavkaCenovnika(predefinisanaVrednost);
      this.stavkeCenovnika.set(predefinisanaVrednost.id, stavkaCenovnika);
    }
    this.modalRef = this.modalService.show(template);
  }

  public openUpdateCenovnik(template: TemplateRef<any>) {
    this.action = "UPDATE";
    this.stavkeCenovnika.clear();
    this.noviCenovnik.id = this.aktuelniCenovnik.id;
    this.noviCenovnik.datumOd = this.aktuelniCenovnik.datumOd;
    this.noviCenovnik.datumDo = this.aktuelniCenovnik.datumDo;
    this.aktuelniCenovnik.stavkeCenovnika.forEach(value =>{
      this.stavkeCenovnika.set(value.predefinisanaVrednost.id, Object.assign({}, value));
    });
    this.modalRef = this.modalService.show(template);
  }

  public prikaziSveCenovnike(template: TemplateRef<any>) {
    this.cenovnikService.getCenovniciZaOsiguravajucuKucu().then(response => this.listaCenovnika = response);
    this.modalRef = this.modalService.show(template);
  }

  public prikaziCenovnikePoDatumu(template: TemplateRef<any>) {
    this.cenovnikService.getCenovniciZaOsiguravajucuKucuPoDatumu().then(response => this.listaCenovnika = response);
    this.modalRef = this.modalService.show(template);
  }

  public novaPravila(template: TemplateRef<any>) {
    this.pravila = "package drools.rules\n\n" +
    "import org.kie.api.runtime.KieRuntime;\n" +
    "import com.sep.pricemanagement.model.*;";
    this.drlFileName = "";
    this.jBossStatus = "CREATE";
    this.modalRef = this.modalService.show(template);
  }

  public izmeniPostojecaPravila(template: TemplateRef<any>) {
    this.jBossStatus = "UPDATE";
    this.cenovnikService.getFajlove()
      .then(files => {this.files = files;});
    this.getSadrzajPravila(this.files[0]);
    this.drlFileName = this.files[0];
    this.modalRef = this.modalService.show(template);
  }

  otvoriCenovnik(cenovnik: Cenovnik){
    this.getStavkeCenovnikaZaCenovnik(cenovnik);
    this.modalRef.hide();
  }

  aktuelan(){
    this.cenovnikService.setAktuelanCenovnikZaOsiguravajucuKucu(this.aktuelniCenovnik).then((response)=>{
      alert('POSTAVLJENO');
    })
  }

  enteredValue($event, predefinisanaVrednost){
    this.stavkeCenovnika.get(predefinisanaVrednost.id).suma = Number.parseFloat($event.target.value);
  }

  prepareDate(date: string): string{
    let dateObject: Date = new Date(date);
    let month: string, day:string;
    if(dateObject.getMonth() < 10){
      month = '0' + (dateObject.getMonth() + 1);
    }else{
      month = '' + dateObject.getMonth();
    }
    if(dateObject.getDate() < 10){
      day = '0' + dateObject.getDate();
    }
    else{
      day = '' + dateObject.getDate();
    }
    return dateObject.getFullYear() + '-' + month + '-' + day;
  }

  prepareData(){
    this.noviCenovnik.stavkeCenovnika = new Array();
    this.stavkeCenovnika.forEach(value => this.noviCenovnik.stavkeCenovnika.push(value));
  }

  createCenovnik(){
    this.prepareData();
    this.cenovnikService.createCenovnik(this.noviCenovnik).then(cenovnik=> {
      this.getStavkeCenovnikaZaCenovnik(cenovnik)
      this.modalRef.hide();
    });
  }

  updateCenovnik(){
    this.prepareData();
    this.cenovnikService.updateCenovnik(this.noviCenovnik).then(cenovnik=> {
      this.getStavkeCenovnikaZaCenovnik(cenovnik)
      this.modalRef.hide();
    });
  }

  sacuvajPravila(){
    this.cenovnikService.sacuvajIzmenjenoPravilo(this.pravila, this.drlFileName);
    console.log(this.pravila);
    console.log(this.drlFileName);
    this.modalRef.hide();
  }

  getSadrzajPravila(drlFileName){
    this.cenovnikService.getSadrzajPravila(drlFileName)
      .then(pravila => {this.pravila = pravila.text()});
  }

  chooseFile(event){
    this.drlFileName = event.target.value;
    this.getSadrzajPravila(this.drlFileName);
  }

}
