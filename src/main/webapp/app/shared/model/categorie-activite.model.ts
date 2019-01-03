import { IProjet } from 'app/shared/model//projet.model';

export interface ICategorieActivite {
    id?: number;
    categorie?: string;
    projets?: IProjet[];
}

export class CategorieActivite implements ICategorieActivite {
    constructor(public id?: number, public categorie?: string, public projets?: IProjet[]) {}
}
