import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InstancesSharedModule } from 'app/shared';
import {
    HistoriqueComponent,
    HistoriqueDetailComponent,
    HistoriqueUpdateComponent,
    HistoriqueDeletePopupComponent,
    HistoriqueDeleteDialogComponent,
    historiqueRoute,
    historiquePopupRoute
} from './';

const ENTITY_STATES = [...historiqueRoute, ...historiquePopupRoute];

@NgModule({
    imports: [InstancesSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        HistoriqueComponent,
        HistoriqueDetailComponent,
        HistoriqueUpdateComponent,
        HistoriqueDeleteDialogComponent,
        HistoriqueDeletePopupComponent
    ],
    entryComponents: [HistoriqueComponent, HistoriqueUpdateComponent, HistoriqueDeleteDialogComponent, HistoriqueDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InstancesHistoriqueModule {}
