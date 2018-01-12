import { Osiguranje } from "./Osiguranje";
import { Klijent } from "./Klijent";

export class Polisa{
    id: number;
    datumSklapanja: string;
    cena: number;
    osiguranja: Osiguranje[];
    klijent: Klijent;
};