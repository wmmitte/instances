import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICategorieActivite } from 'app/shared/model/categorie-activite.model';

type EntityResponseType = HttpResponse<ICategorieActivite>;
type EntityArrayResponseType = HttpResponse<ICategorieActivite[]>;

@Injectable({ providedIn: 'root' })
export class CategorieActiviteService {
    public resourceUrl = SERVER_API_URL + 'api/categorie-activites';

    constructor(protected http: HttpClient) {}

    create(categorieActivite: ICategorieActivite): Observable<EntityResponseType> {
        return this.http.post<ICategorieActivite>(this.resourceUrl, categorieActivite, { observe: 'response' });
    }

    update(categorieActivite: ICategorieActivite): Observable<EntityResponseType> {
        return this.http.put<ICategorieActivite>(this.resourceUrl, categorieActivite, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICategorieActivite>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICategorieActivite[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
