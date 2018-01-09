import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http'

import { AppComponent } from './app.component';
import { CompComponent } from './Sasa/comp/comp.component';
import { TempSasaService } from './services/temp-sasa.service'

@NgModule({
  declarations: [
    AppComponent,
    CompComponent
  ],
  imports: [
    BrowserModule,
    HttpModule
  ],
  providers: [TempSasaService],
  bootstrap: [AppComponent]
})
export class AppModule { }
