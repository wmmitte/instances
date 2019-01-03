import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Historique } from 'app/shared/model/historique.model';
import { HistoriqueService } from './historique.service';
import { HistoriqueComponent } from './historique.component';
import { HistoriqueDetailComponent } from './historique-detail.component';
import { HistoriqueUpdateComponent } from './historique-update.component';
import { HistoriqueDeletePopupComponent } from './historique-delete-dialog.component';
import { IHistorique } from 'app/shared/model/historique.model';

@Injectable({ providedIn: 'root' })
export class HistoriqueResolve implements Resolve<IHistorique> {
    constructor(private service: HistoriqueService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Historique> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Historique>) => response.ok),
                map((historique: HttpResponse<Historique>) => historique.body)
            );
        }
        return of(new Historique());
    }
}

export const historiqueRoute: Routes = [
    {
        path: 'historique',
        component: HistoriqueComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Historiques'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'historique/:id/view',
        component: HistoriqueDetailComponent,
        resolve: {
            historique: HistoriqueResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Historiques'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'historique/new',
        component: HistoriqueUpdateComponent,
        resolve: {
            historique: HistoriqueResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Historiques'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'historique/:id/edit',
        component: HistoriqueUpdateComponent,
        resolve: {
            historique: HistoriqueResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Historiques'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const historiquePopupRoute: Routes = [
    {
        path: 'historique/:id/delete',
        component: HistoriqueDeletePopupComponent,
        resolve: {
            historique: HistoriqueResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Historiques'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
