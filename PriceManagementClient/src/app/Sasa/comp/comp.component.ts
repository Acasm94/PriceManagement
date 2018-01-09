import { Component, OnInit } from '@angular/core';
import { TempSasaService } from '../../services/temp-sasa.service';

import { SasaTemp } from '../../shared/tempSasa';

@Component({
  selector: 'app-comp',
  templateUrl: './comp.component.html',
  styleUrls: ['./comp.component.css']
})
export class CompComponent implements OnInit {

  broj: number = 1;
  tempSasa: SasaTemp;

  constructor(private tempSasaService: TempSasaService) { }

  ngOnInit() {
    this.incrementBroj();
  }

  incrementBroj(){
    this.tempSasaService.getString(++this.broj).then(response => this.tempSasa = response);
  }

}
