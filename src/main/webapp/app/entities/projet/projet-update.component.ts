import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IProjet } from 'app/shared/model/projet.model';
import { ProjetService } from './projet.service';
import { ICategorieActivite } from 'app/shared/model/categorie-activite.model';
import { CategorieActiviteService } from 'app/entities/categorie-activite';
import { IClient } from 'app/shared/model/client.model';
import { ClientService } from 'app/entities/client';

@Component({
    selector: 'jhi-projet-update',
    templateUrl: './projet-update.component.html'
})
export class ProjetUpdateComponent implements OnInit {
    projet: IProjet;
    isSaving: boolean;

    categorieactivites: ICategorieActivite[];

    clients: IClient[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected projetService: ProjetService,
        protected categorieActiviteService: CategorieActiviteService,
        protected clientService: ClientService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ projet }) => {
            this.projet = projet;
        });
        this.categorieActiviteService.query().subscribe(
            (res: HttpResponse<ICategorieActivite[]>) => {
                this.categorieactivites = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.clientService.query().subscribe(
            (res: HttpResponse<IClient[]>) => {
                this.clients = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.projet.id !== undefined) {
            this.subscribeToSaveResponse(this.projetService.update(this.projet));
        } else {
            this.subscribeToSaveResponse(this.projetService.create(this.projet));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IProjet>>) {
        result.subscribe((res: HttpResponse<IProjet>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackCategorieActiviteById(index: number, item: ICategorieActivite) {
        return item.id;
    }

    trackClientById(index: number, item: IClient) {
        return item.id;
    }
}
