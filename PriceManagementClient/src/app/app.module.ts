import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http'

import { AppComponent } from './app.component';
import { CompComponent } from './Sasa/comp/comp.component';
import { TempSasaService } from './services/temp-sasa.service';
import { TipoviOsiguranjaComponent } from './tipovi-osiguranja/tipovi-osiguranja.component'

import { KeycloakHttp, KEYCLOAK_HTTP_PROVIDER } from './services/keycloak.http';
import { KeycloakService } from './services/keycloak.service';

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
  providers: [TempSasaService, 
              KEYCLOAK_HTTP_PROVIDER,
              KeycloakService],
  bootstrap: [AppComponent]
})
export class AppModule { }
