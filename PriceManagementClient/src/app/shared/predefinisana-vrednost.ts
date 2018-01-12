import { StavkaCenovnika } from "./Stavka-cenovnika";
import { TipAtributa } from "./Tip-atributa";

export class PredefinisanaVrednost{
    id: number;
    konkretnaVrednost: string;
    tipAtributa: TipAtributa;
    stavkeCenovnika: StavkaCenovnika[];
};