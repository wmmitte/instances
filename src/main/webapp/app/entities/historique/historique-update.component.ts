import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IHistorique } from 'app/shared/model/historique.model';
import { HistoriqueService } from './historique.service';
import { IProjet } from 'app/shared/model/projet.model';
import { ProjetService } from 'app/entities/projet';

@Component({
    selector: 'jhi-historique-update',
    templateUrl: './historique-update.component.html'
})
export class HistoriqueUpdateComponent implements OnInit {
    historique: IHistorique;
    isSaving: boolean;

    projets: IProjet[];
    dateDp: any;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected historiqueService: HistoriqueService,
        protected projetService: ProjetService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ historique }) => {
            this.historique = historique;
        });
        this.projetService.query().subscribe(
            (res: HttpResponse<IProjet[]>) => {
                this.projets = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.historique.id !== undefined) {
            this.subscribeToSaveResponse(this.historiqueService.update(this.historique));
        } else {
            this.subscribeToSaveResponse(this.historiqueService.create(this.historique));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IHistorique>>) {
        result.subscribe((res: HttpResponse<IHistorique>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackProjetById(index: number, item: IProjet) {
        return item.id;
    }
}
