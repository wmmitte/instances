import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IHistorique } from 'app/shared/model/historique.model';

type EntityResponseType = HttpResponse<IHistorique>;
type EntityArrayResponseType = HttpResponse<IHistorique[]>;

@Injectable({ providedIn: 'root' })
export class HistoriqueService {
    public resourceUrl = SERVER_API_URL + 'api/historiques';

    constructor(protected http: HttpClient) {}

    create(historique: IHistorique): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(historique);
        return this.http
            .post<IHistorique>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(historique: IHistorique): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(historique);
        return this.http
            .put<IHistorique>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IHistorique>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IHistorique[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(historique: IHistorique): IHistorique {
        const copy: IHistorique = Object.assign({}, historique, {
            date: historique.date != null && historique.date.isValid() ? historique.date.format(DATE_FORMAT) : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.date = res.body.date != null ? moment(res.body.date) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((historique: IHistorique) => {
                historique.date = historique.date != null ? moment(historique.date) : null;
            });
        }
        return res;
    }
}
