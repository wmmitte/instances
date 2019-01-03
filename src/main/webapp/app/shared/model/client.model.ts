import { IProjet } from 'app/shared/model//projet.model';

export interface IClient {
    id?: number;
    sigle?: string;
    nom?: string;
    projets?: IProjet[];
}

export class Client implements IClient {
    constructor(public id?: number, public sigle?: string, public nom?: string, public projets?: IProjet[]) {}
}
