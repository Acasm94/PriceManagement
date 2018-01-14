import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { ModalModule } from 'ngx-bootstrap/modal';

import { AppComponent } from './app.component';
import { CompComponent } from './Sasa/comp/comp.component';
import { TempSasaService } from './services/temp-sasa.service';
import { TipoviOsiguranjaComponent } from './tipovi-osiguranja/tipovi-osiguranja.component';

import { KeycloakHttp, KEYCLOAK_HTTP_PROVIDER } from './services/keycloak.http';
import { KeycloakService } from './services/keycloak.service';
import { CenovnikComponent } from './cenovnik/cenovnik.component';
import { CenovnikService } from './services/cenovnik.service';

@NgModule({
  declarations: [
    AppComponent,
    CompComponent,
    TipoviOsiguranjaComponent,
    CenovnikComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule,
    ModalModule.forRoot()
  ],
  providers: [TempSasaService,
              CenovnikService, 
              KEYCLOAK_HTTP_PROVIDER,
              KeycloakService],
  bootstrap: [AppComponent]
})
export class AppModule { }
