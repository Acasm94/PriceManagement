import { TipOsiguranja } from "./tip-osiguranja";
import { TipAtributa } from "./tip-atributa";

export class KontekstAtributa{
    id: number;
    naziv: string;
    visestrukoDodavanje: boolean;
    redniBrojForme: number;
    predstavljaGrupu: boolean;
    tipoviAtributa: TipAtributa[];
    tipoviOsiguranja: TipOsiguranja[];
};
