import { ICategorieActivite } from 'app/shared/model//categorie-activite.model';
import { IClient } from 'app/shared/model//client.model';
import { IHistorique } from 'app/shared/model//historique.model';

export interface IProjet {
    id?: number;
    nom?: string;
    statut?: string;
    next?: string;
    commentaire?: string;
    categorieActivite?: ICategorieActivite;
    client?: IClient;
    historiques?: IHistorique[];
}

export class Projet implements IProjet {
    constructor(
        public id?: number,
        public nom?: string,
        public statut?: string,
        public next?: string,
        public commentaire?: string,
        public categorieActivite?: ICategorieActivite,
        public client?: IClient,
        public historiques?: IHistorique[]
    ) {}
}
