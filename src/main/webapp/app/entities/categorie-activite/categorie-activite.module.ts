import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InstancesSharedModule } from 'app/shared';
import {
    CategorieActiviteComponent,
    CategorieActiviteDetailComponent,
    CategorieActiviteUpdateComponent,
    CategorieActiviteDeletePopupComponent,
    CategorieActiviteDeleteDialogComponent,
    categorieActiviteRoute,
    categorieActivitePopupRoute
} from './';

const ENTITY_STATES = [...categorieActiviteRoute, ...categorieActivitePopupRoute];

@NgModule({
    imports: [InstancesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CategorieActiviteComponent,
        CategorieActiviteDetailComponent,
        CategorieActiviteUpdateComponent,
        CategorieActiviteDeleteDialogComponent,
        CategorieActiviteDeletePopupComponent
    ],
    entryComponents: [
        CategorieActiviteComponent,
        CategorieActiviteUpdateComponent,
        CategorieActiviteDeleteDialogComponent,
        CategorieActiviteDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InstancesCategorieActiviteModule {}
