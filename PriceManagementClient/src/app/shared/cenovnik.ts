import { OsiguravajucaKuca } from "./osiguravajuca-kuca";
import { StavkaCenovnika } from "./stavka-cenovnika";

export class Cenovnik{
    id: number;
    datumOd: string;
    datumDo: string;
    aktuelan: boolean;
    osiguravajucaKuca: OsiguravajucaKuca;
    stavkeCenovnika: StavkaCenovnika[];
};
