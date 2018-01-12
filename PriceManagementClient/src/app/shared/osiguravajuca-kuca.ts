import { TipOsiguranja } from './Tip-osiguranja';
import { Cenovnik } from './Cenovnik';

export class OsiguravajucaKuca{
    id: number;
	pib: string;
	naziv: string;
	adresa: string;
	mesto: string;
	telefon: string; 
	tipoviOsiguranja: TipOsiguranja[];
	listaCenovnika: Cenovnik[];
}