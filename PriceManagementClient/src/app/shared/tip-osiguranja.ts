import { TipAtributa } from './Tip-atributa';
import { OsiguravajucaKuca } from "./Osiguravajuca-kuca";
import { Osiguranje } from "./Osiguranje";

export class TipOsiguranja{
    id: number;
    naziv: string;
    brojFormi: number;
    osiguravajucaKuca: OsiguravajucaKuca;
    osiguranja: Osiguranje[];
    tipoviAtributa: TipAtributa[];
};