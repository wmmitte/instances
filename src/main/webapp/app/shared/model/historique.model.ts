import { Moment } from 'moment';
import { IProjet } from 'app/shared/model//projet.model';

export interface IHistorique {
    id?: number;
    date?: Moment;
    objet?: string;
    projet?: IProjet;
}

export class Historique implements IHistorique {
    constructor(public id?: number, public date?: Moment, public objet?: string, public projet?: IProjet) {}
}
