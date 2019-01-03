import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CategorieActivite } from 'app/shared/model/categorie-activite.model';
import { CategorieActiviteService } from './categorie-activite.service';
import { CategorieActiviteComponent } from './categorie-activite.component';
import { CategorieActiviteDetailComponent } from './categorie-activite-detail.component';
import { CategorieActiviteUpdateComponent } from './categorie-activite-update.component';
import { CategorieActiviteDeletePopupComponent } from './categorie-activite-delete-dialog.component';
import { ICategorieActivite } from 'app/shared/model/categorie-activite.model';

@Injectable({ providedIn: 'root' })
export class CategorieActiviteResolve implements Resolve<ICategorieActivite> {
    constructor(private service: CategorieActiviteService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<CategorieActivite> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<CategorieActivite>) => response.ok),
                map((categorieActivite: HttpResponse<CategorieActivite>) => categorieActivite.body)
            );
        }
        return of(new CategorieActivite());
    }
}

export const categorieActiviteRoute: Routes = [
    {
        path: 'categorie-activite',
        component: CategorieActiviteComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CategorieActivites'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'categorie-activite/:id/view',
        component: CategorieActiviteDetailComponent,
        resolve: {
            categorieActivite: CategorieActiviteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CategorieActivites'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'categorie-activite/new',
        component: CategorieActiviteUpdateComponent,
        resolve: {
            categorieActivite: CategorieActiviteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CategorieActivites'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'categorie-activite/:id/edit',
        component: CategorieActiviteUpdateComponent,
        resolve: {
            categorieActivite: CategorieActiviteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CategorieActivites'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const categorieActivitePopupRoute: Routes = [
    {
        path: 'categorie-activite/:id/delete',
        component: CategorieActiviteDeletePopupComponent,
        resolve: {
            categorieActivite: CategorieActiviteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CategorieActivites'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
