import { DomenAtributa } from "./Domen-atributa";
import { KontekstAtributa } from "./Kontekst-atributa";
import { PredefinisanaVrednost } from "./Predefinisana-vrednost";
import { TipOsiguranja } from "./Tip-osiguranja";
import { VrednostAtributaOsiguranja } from "./Vrednost-atributa-osiguranja";

export class TipAtributa{
    id: number;
    naziv: string;
    domen: DomenAtributa;
    obavezan: boolean;
    uticeNaCenu: boolean;
    slobodnoPolje: boolean;
    kontekst: KontekstAtributa;
    predefinisaneVrednosti: PredefinisanaVrednost[];
    vrednostiAtributa: VrednostAtributaOsiguranja[];
    tipoviOsiguranja: TipOsiguranja[];
};