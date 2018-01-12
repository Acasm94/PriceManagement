import { TipOsiguranja } from "./tip-osiguranja";
import { Polisa } from "./polisa";
import { VrednostAtributaOsiguranja } from "./vrednost-atributa-osiguranja";

export class Osiguranje{
    id: number;
    tipOsiguranja: TipOsiguranja;
    vrednostiAtributaOsiguranja: VrednostAtributaOsiguranja[];

    public constructor(){
        this.vrednostiAtributaOsiguranja = new Array();
    }
};