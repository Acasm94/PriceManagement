import { Cenovnik } from "./Cenovnik";
import { PredefinisanaVrednost } from "./Predefinisana-vrednost";

export class StavkaCenovnika{
    id: number;
    suma: number;
    cenovnik: Cenovnik;
    predefinisanaVrednost: PredefinisanaVrednost;

    constructor(predefinisanaVrednost: PredefinisanaVrednost){
        this.predefinisanaVrednost = predefinisanaVrednost;
    }

};
