import { TipAtributa } from "./Tip-atributa";
import { Osiguranje } from "./Osiguranje";

export class VrednostAtributaOsiguranja{
    id: number;
    vrednost: string;
    tipAtributa: TipAtributa;
    osiguranje: Osiguranje;

    constructor(vrednost: string,tipAtributa: TipAtributa){
        this.vrednost = vrednost;
        this.tipAtributa = tipAtributa;
    }
};