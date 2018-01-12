import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http'

import { AppComponent } from './app.component';
import { CompComponent } from './Sasa/comp/comp.component';
import { TempSasaService } from './services/temp-sasa.service';
import { TipoviOsiguranjaComponent } from './tipovi-osiguranja/tipovi-osiguranja.component'

@NgModule({
  declarations: [
    AppComponent,
    CompComponent,
    TipoviOsiguranjaComponent
  ],
  imports: [
    BrowserModule,
    HttpModule
  ],
  providers: [TempSasaService],
  bootstrap: [AppComponent]
})
export class AppModule { }
